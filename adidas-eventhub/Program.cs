using System;
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
    public struct TrainingData {
        public int idActivity {get; set;}
        public SensorData[] sensorDatas {get; set;}

        
    }

    public struct SensorData {
        public Single axisx {get; set;}
        public Single axisy {get; set;}
        public Single axisz {get; set;}
        public int idSensorType {get; set;}
        public int idActivity {get; set;}
        public string updateTime {get; set;}
    }

    class Program
    {
        static void Main(string[] args)
        {
            DataContractJsonSerializerSettings settings = new DataContractJsonSerializerSettings();
            settings.EmitTypeInformation = System.Runtime.Serialization.EmitTypeInformation.Never;

            var http = new HttpClient();
            var puller = new NetMQ.Sockets.PullSocket();

            //I DONT EVEN USE THIS PUSHER SOCKET BUT WHO CARES IT'S HACKATON MY FRENDS!
            var pusher = new NetMQ.Sockets.PushSocket();

            var requester = new NetMQ.Sockets.RequestSocket();
            var historical = new List<SensorData>();
            var partyHard = false;
            
            var trainingDataSerializer = new DataContractJsonSerializer(typeof(List<TrainingData>));
            trainingDataSerializer.KnownTypes.Append(typeof(SensorData));

            var sensorDataSerializer = new DataContractJsonSerializer(typeof(SensorData[]));


            var tempSensor = new List<SensorData>();
            
            puller.Bind("tcp://*:58588");
            pusher.Connect("tcp://172.16.30.142:5557");
            requester.Connect("tcp://172.16.30.142");

            Task.Run(()=>{
                while (true){
                    var message = puller.ReceiveFrameString().Split('|');
                    var sensorDatas = new SensorData(){
                        idSensorType = Convert.ToInt16(message[0]),
                        axisx = Convert.ToSingle(message[1]),
                        axisy = Convert.ToSingle(message[2]),
                        axisz = Convert.ToSingle(message[3]),
                        updateTime = DateTime.UtcNow.ToString("yyyy-MM-ddTHH:mm:ssZ")
                    };
                    tempSensor.Add(sensorDatas);

                    if (partyHard)
                        Console.WriteLine($"foot {sensorDatas.idSensorType} x:{sensorDatas.axisx} y:{sensorDatas.axisy} z:{sensorDatas.axisz}");
                }
            });

            // EXCUSE MY SPAGHETTI
            Task.Run(()=> {
                while (true) {
                    if (tempSensor.Count > 50){
                        using (var stream = new MemoryStream()) {
                            sensorDataSerializer.WriteObject(stream, tempSensor.ToArray());
                            stream.Position = 0;
                            StreamReader sr = new StreamReader(stream);  
                            var finalString = sr.ReadToEnd();
                            requester.SendFrame(finalString);
                            Console.WriteLine("Ladiess and Gentleman we proudly have! A probably wrong prediction! APPLAUSSE");
                            Console.WriteLine(requester.ReceiveFrameString());
                        } 
                    }
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
                Console.WriteLine("Press p for party hard");
                Console.WriteLine("=========================");
                Console.WriteLine("AND DONT FORGET PUSH ENTER AFTER!");

                var command = Console.ReadLine();

                if (command.ToString() == "c"){
                    tempSensor.Clear();
                    continue;
                }

                if (command.ToString() == "p"){
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


                    pusher.SendFrame(finalString);

                    var content = new StringContent(finalString.ToString(), Encoding.UTF8, "application/json");

                     
                    var result = http.PostAsync("http://172.16.30.140:8080/userActivityData", content ).Result;
                    Console.WriteLine(result);
                };

                tempSensor.Clear();
            }
        }
    }
}
