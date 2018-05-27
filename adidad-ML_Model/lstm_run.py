
# coding: utf-8

# In[2]:


import keras
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import os
import warnings
warnings.filterwarnings('ignore')


# In[26]:


# Setting seed for reproducibility
np.random.seed(1234)  
PYTHONHASHSEED = 0

from sklearn import preprocessing
from sklearn.cross_validation import train_test_split
from sklearn.metrics import confusion_matrix, recall_score, precision_score
from keras.models import Sequential,load_model
from keras.layers import Dense, Dropout, LSTM


# In[16]:


# MySQL-connector-python
from sqlalchemy import create_engine

# mysql+mysqlconnector://<user>:<password>@<host>[:<port>]/<dbname>
engine = create_engine("mysql+mysqlconnector://euler:root@172.16.30.140:3306/test")

# Read data from database
myQuery = '''
select 
    s.ID_USER_ACTIVITY_DATA as ID,
    #s.UPDATE_TIME as UPDATE_TIME,
    s.ID_SENSOR_TYPE as SENSOR_TYPE,
    s.AXISX as X, 
    s.AXISY as Y, 
    s.AXISZ as Z,
    u.ID_ACTIVITY as ACTIVITY
from sensor_data s
    left join user_activity_data u
    on s.ID_USER_ACTIVITY_DATA = u.ID_USER_ACTIVITY_DATA
'''
data = pd.read_sql_query(myQuery, engine)
sensor_data = pd.read_sql_query('SELECT * FROM sensor_data limit 10', engine)
user_activity_data = pd.read_sql_query('SELECT * FROM user_activity_data limit 10', engine)

#data.head()


# In[17]:


#data.groupby('ID').size()
#data.columns


# In[31]:


#data.groupby('ACTIVITY').size()


# In[18]:


# MinMax normalization (from 0 to 1)
cols_normalize = ['X', 'Y', 'Z']
min_max_scaler = preprocessing.MinMaxScaler()
norm_data = pd.DataFrame(min_max_scaler.fit_transform(data[cols_normalize]), 
                             columns=cols_normalize, 
                             index=data.index)
join_data = data[data.columns.difference(cols_normalize)].join(norm_data)
data = join_data.reindex(columns = data.columns)
data['SENSOR_TYPE'] = data['SENSOR_TYPE'] - 1


# LSTM

# pick a large window size of 50 cycles
sequence_length = 6

# function to reshape features into (samples, time steps, features) 
def gen_sequence(id_df, seq_length, seq_cols):
    """ Only sequences that meet the window-length are considered, no padding is used. This means for testing
    we need to drop those which are below the window-length. An alternative would be to pad sequences so that
    we can use shorter ones """
    # for one id I put all the rows in a single matrix
    data_matrix = id_df[seq_cols].values
    nrows = data_matrix.shape[0]
    # Iterate over two lists in parallel.
    # For example id1 have 192 rows and sequence_length is equal to 50
    # so zip iterate over two following list of numbers (0,112),(50,192)
    # 0 50 -> from row 0 to row 50
    # 1 51 -> from row 1 to row 51
    # 2 52 -> from row 2 to row 52
    # ...
    # 111 191 -> from row 111 to 191
    for start, stop in zip(range(0, nrows-seq_length), range(seq_length, nrows)):
        yield data_matrix[start:stop, :]

# generator for the sequences
sequence_cols = ['SENSOR_TYPE', 'X', 'Y', 'Z']
seq_gen = (list(gen_sequence(data[data['ID']==id], sequence_length, sequence_cols)) 
           for id in data['ID'].unique())

# generate sequences and convert to numpy array
seq_array = np.concatenate(list(seq_gen)).astype(np.float32)
seq_array.shape


# In[40]:


from keras.utils import to_categorical
#from sklearn.preprocessing import LabelEncoder

#y_binary = to_categorical(y_int)

# encode class values as integers
#encoder = LabelEncoder()
#encoder.fit(Y_train)
#encoded_Y_train = encoder.transform(Y_train)

# convert integers to dummy variables (i.e. one hot encoded)
#dummy_y_train = np_utils.to_categorical(encoded_Y_train)


# function to generate labels
def gen_labels(id_df, seq_length, label):
    # For one id I put all the labels in a single matrix.
    # For example:
    # [[1]
    # [4]
    # [1]
    # [5]
    # [9]
    # ...
    # [200]] 
    
    data_matrix = id_df[label].values
    nrows = data_matrix.shape[0]
    # I have to remove the first seq_length labels
    # because for one id the first sequence of seq_length size have as target
    # the last label (the previus ones are discarded).
    # All the next id's sequences will have associated step by step one label as target. 
    return data_matrix[seq_length:nrows, :]

# generate labels
label_gen = [gen_labels(data[data['ID']==id], sequence_length, ['ACTIVITY']) 
             for id in data['ID'].unique()]

label_array = np.concatenate(label_gen).astype(np.int32)
label_array.shape


# In[42]:

from sklearn.preprocessing import LabelEncoder

# encode class values as integers
encoder = LabelEncoder()
encoder.fit(label_array)
encoded_label_array = encoder.transform(label_array)
y_dummy = to_categorical(encoded_label_array)
label_array = y_dummy

# In[45]:


# Next, we build a deep network. 
# The first layer is an LSTM layer with 100 units followed by another LSTM layer with 50 units. 
# Dropout is also applied after each LSTM layer to control overfitting. 
# Final layer is a Dense output layer with single unit and sigmoid activation since this is a binary classification problem.
# build the network
nb_features = seq_array.shape[2]
nb_out = label_array.shape[1]

model = Sequential()
model.add(LSTM(input_shape=(sequence_length, nb_features), units=100, return_sequences=True))
model.add(Dropout(0.2))
model.add(LSTM(units=50, return_sequences=False))
model.add(Dropout(0.2))

model.add(Dense(units=nb_out, activation='sigmoid'))
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])

print(model.summary())

# fit the network
model_path = 'categorical_model.h5' # define path to save model
history = model.fit(seq_array, label_array, epochs=1000, batch_size=20, validation_split=0.10, verbose=2,
          callbacks = [#keras.callbacks.EarlyStopping(monitor='val_loss', min_delta=0, patience=10, verbose=0, mode='min'),
                       keras.callbacks.ModelCheckpoint(model_path,monitor='val_loss', save_best_only=True, mode='min', verbose=0)]
          )


# In[48]:


# list all data in history
print(history.history.keys())

# summarize history for Accuracy
fig_acc = plt.figure(figsize=(10, 10))
plt.plot(history.history['acc'])
plt.plot(history.history['val_acc'])
plt.title('model accuracy')
plt.ylabel('accuracy')
plt.xlabel('epoch')
plt.legend(['train', 'test'], loc='upper left')
plt.show()
fig_acc.savefig("output/model_accuracy.png")

# summarize history for Loss
fig_acc = plt.figure(figsize=(10, 10))
plt.plot(history.history['loss'])
plt.plot(history.history['val_loss'])
plt.title('model loss')
plt.ylabel('loss')
plt.xlabel('epoch')
plt.legend(['train', 'test'], loc='upper left')
plt.show()
fig_acc.savefig("output/model_loss.png")


# training metrics
scores = model.evaluate(seq_array, label_array, verbose=1, batch_size=20)
print('Accurracy: {}'.format(scores[1]))

'''
# make predictions and compute confusion matrix
y_pred = model.predict_classes(seq_array,verbose=1, batch_size=200)
y_true = label_array

test_set = pd.DataFrame(y_pred)
test_set.to_csv('output/binary_submit_train.csv', index = None)

print('Confusion matrix\n- x-axis is true labels.\n- y-axis is predicted labels')
cm = confusion_matrix(y_true, y_pred)
print(cm)

# compute precision and recall
precision = precision_score(y_true, y_pred)
recall = recall_score(y_true, y_pred)
print( 'precision = ', precision, '\n', 'recall = ', recall)
'''





