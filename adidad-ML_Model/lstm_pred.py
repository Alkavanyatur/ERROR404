
import keras
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import os
import warnings
warnings.filterwarnings('ignore')

np.random.seed(1234)  
PYTHONHASHSEED = 0

from sklearn import preprocessing
#from sklearn.cross_validation import train_test_split
#from sklearn.metrics import confusion_matrix, recall_score, precision_score
from keras.models import Sequential,load_model
from keras.layers import Dense, Dropout, LSTM

# function to reshape features into (samples, time steps, features) 
def gen_sequence(id_df, seq_length, seq_cols):
	data_matrix = id_df[seq_cols].values
	nrows = data_matrix.shape[0]
	for start, stop in zip(range(0, nrows-seq_length), range(seq_length, nrows)):
		yield data_matrix[start:stop, :]

def make_pred(data):
    # MinMax normalization (from 0 to 1)
    cols_normalize = ['axisx', 'axisy', 'axisz']
    min_max_scaler = preprocessing.MinMaxScaler()
    norm_data = pd.DataFrame(min_max_scaler.fit_transform(data[cols_normalize]), 
                             columns=cols_normalize, 
                             index=data.index)
    join_data = data[data.columns.difference(cols_normalize)].join(norm_data)
    data = join_data.reindex(columns = data.columns)
    data['idSensorType'] = data['idSensorType'] - 1

    # pick a large window size of 50 cycles
    sequence_length = 6

    # generator for the sequences
    sequence_cols = ['idSensorType','axisx', 'axisy', 'axisz']
    seq_gen = (list(gen_sequence(data[data['id']==id], sequence_length, sequence_cols))
    	for id in data['id'].unique())

    # generate sequences and convert to numpy array
    seq_array = np.concatenate(list(seq_gen)).astype(np.float32)

    # if best iteration's model was saved then load and use it
    model_path = 'categorical_model.h5'
    if os.path.isfile(model_path):
        model = load_model(model_path)

    y_pred = model.predict_classes(seq_array,verbose=1, batch_size=20)
    return(y_pred)


## Get data
import time
import zmq
import random
import json
import pandas as pd

def consumer():

    context = zmq.Context()
    
    # recieve work
    consumer_receiver = context.socket(zmq.PULL)
    consumer_receiver.bind("tcp://*:5557")
    
    # recieve work
    consumer_sender = context.socket(zmq.PUSH)
    consumer_sender.connect("tcp://172.16.30.143:5558")
    
    while True:
        work = str(consumer_receiver.recv_string())
        data = json.loads(work)
        df = pd.DataFrame(data)
        
        if df.shape[0] > 0:
        	df['id'] = 1
        	print(df.tail(1))
        	label = make_pred(df)
        	print(label)
        	consumer_sender.send_string(str(label[0]))


consumer()

