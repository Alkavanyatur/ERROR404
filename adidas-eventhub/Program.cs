﻿using System;
using NetMQ;
using NetMQ.Sockets;
using System.Linq;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Runtime.Serialization.Json;
using System.IO;
using System.Text;


namespace adidas_puller
{
    //No time to separate structs in files! Run run run, time is gold.
    public struct TrainingData {
        public int idActivity {get; set;}
        public SensorData[] sensorDatas {get; set;}
    }

    public struct SensorData {
        public Single axisx {get; set;}
        public Single axisy {get; set;}
        public Single axisz {get; set;}
        public int idSensorType {get; set;}
        public string updateTime {get; set;}
    }

    class Program
    {
        static void Main(string[] args)
        {
            DataContractJsonSerializerSettings settings = new DataContractJsonSerializerSettings();
            settings.EmitTypeInformation = System.Runtime.Serialization.EmitTypeInformation.Never;

            //Lets go with the party. We gonna use http to post training data through a Spring Boot service.
            var http = new HttpClient();

            //We pull data from sensor and pull data from the predictor model
            var sensorDataPullSocket = new NetMQ.Sockets.PullSocket();
            var predictionDataPullSocket = new NetMQ.Sockets.PullSocket();
            

            //I DONT EVEN USE THIS PUSHER SOCKET BUT WHO CARES IT'S HACKATON MY FRENDS!
            var trainingDataPushSocket = new NetMQ.Sockets.PushSocket();

            //Bind bind connect! Do stuff!
            sensorDataPullSocket.Bind("tcp://*:58588");
            predictionDataPullSocket.Bind("tcp://*:5558");
            
            //Python can get the training data through spring boot also, but.. I love sockets and I use it everywhere
            trainingDataPushSocket.Connect("tcp://172.16.30.142:5557");

            //Some funny variables
            var partyHard = false;
            
            //lets go create a bunch of serializers, ok only 2.
            var trainingDataSerializer = new DataContractJsonSerializer(typeof(List<TrainingData>));
            trainingDataSerializer.KnownTypes.Append(typeof(SensorData));

            var sensorDataSerializer = new DataContractJsonSerializer(typeof(SensorData[]));
            var tempSensor = new List<SensorData>();
            
            //The multithread hell starts here. Ok no only 2 threads for some async stuff.
            Task.Run(()=>{
                while (true){
                    var message = sensorDataPullSocket.ReceiveFrameString().Split('|');
                    var sensorDatas = new SensorData(){
                        idSensorType = Convert.ToInt16(message[0]),
                        axisx = Convert.ToSingle(message[1]),
                        axisy = Convert.ToSingle(message[2]),
                        axisz = Convert.ToSingle(message[3]),
                        updateTime = DateTime.UtcNow.ToString("yyyy-MM-ddTHH:mm:ss.fffZ")
                    };
                    tempSensor.Add(sensorDatas);

                    //this shows sensor data from devices
                    if (partyHard)
                        Console.WriteLine($"foot {sensorDatas.idSensorType} x:{sensorDatas.axisx} y:{sensorDatas.axisy} z:{sensorDatas.axisz}");
                }
            });

            // EXCUSE MY SPAGHETTI
            Task.Run(()=> {
                //This thread send background data to the prediction stream. Maybe a request/response could be more
                //appropiated but.. so tired.
                while (true) {
                    var samples = 20;
                    if (tempSensor.Count > samples){
                        using (var stream = new MemoryStream()) {
                            var tempSensorLast = tempSensor.Skip(Math.Max(0, tempSensor.Count - samples)).ToArray();
                            sensorDataSerializer.WriteObject(stream, tempSensorLast);
                            stream.Position = 0;
                            StreamReader sr = new StreamReader(stream);  
                            trainingDataPushSocket.SendFrame(sr.ReadToEnd());
                            //Should I flood the python service... Be good, be good..
                            Thread.Sleep(10000);
                        } 
                    }
                }
            });

            Task.Run(()=>{
                while(true){
                    var prediction = predictionDataPullSocket.ReceiveFrameString();
                    Console.WriteLine("Ladiess and Gentleman we proudly have! A probably wrong prediction! APPLAUSSE");
                    switch(prediction){
                        case "0":
                            prediction = "Tennis";
                            break;
                        case "1":
                            prediction = "Running";
                            break;
                        case "2":
                            prediction = "Football";
                            break;
                        case "3":
                            prediction = "Cycling";
                            break;
                        case "4":
                            prediction = "Basket";
                            break;
                    }
                    Console.WriteLine($"Are you playing {prediction}?");
                }
            });
            
            // EXCUSE MY SPAGHETTI
            // EXCUSE MY SPAGHETTI
            // EXCUSE MY SPAGHETTI
            while (true){
                Console.WriteLine("Enter the label activity");
                Console.WriteLine("Press 1 for save Tennis");
                Console.WriteLine("Press 2 for save Running");
                Console.WriteLine("Press 3 for save Football");
                Console.WriteLine("Press 4 for save Cycling");
                Console.WriteLine("Press 5 for save Basket");
                Console.WriteLine("=========================");
                Console.WriteLine("Press c for clean data");
                Console.WriteLine("Press h for party hard");
                Console.WriteLine("=========================");
                Console.WriteLine("AND DONT FORGET PUSH ENTER AFTER!");

                var command = Console.ReadLine();

                if (command.ToString() == "c"){
                    tempSensor.Clear();
                    continue;
                }

                if (command.ToString() == "h"){
                    if (!partyHard)
                        partyHard = true;
                    else
                        partyHard = false;
                    
                    continue;
                }
                    

                try {
                    Convert.ToInt16(command.ToString());
                } catch {
                    Console.WriteLine("Please sir dont troll our application");
                    continue;
                }
                if (Convert.ToInt16(command.ToString()) > 5){
                    Console.WriteLine("Please sir dont troll our application");
                    continue;
                }
                    
                
                var TrainingData = new TrainingData() {
                    idActivity = Convert.ToInt16(command.ToString()),
                    sensorDatas = tempSensor.ToArray()
                };

                using (var stream = new MemoryStream()) {
                    trainingDataSerializer.WriteObject(stream, TrainingData);
                    stream.Position = 0;
                    StreamReader sr = new StreamReader(stream);  
                    var finalString = sr.ReadToEnd();


                    trainingDataPushSocket.SendFrame(finalString);

                    var content = new StringContent(finalString.ToString(), Encoding.UTF8, "application/json");

                     
                    var result = http.PostAsync("http://172.16.30.140:8080/userActivityData", content ).Result;
                    Console.WriteLine(result);
                };

                tempSensor.Clear();
            }
        }
    }
}
