package adidas.com.adidassensor

import android.content.Context
import android.hardware.Sensor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.android.synthetic.main.activity_main.*
import org.zeromq.ZMQ


class MainActivity : AppCompatActivity(), SensorEventListener {

    private val ctx = ZMQ.context(1)
    private val pusher = ctx.socket(ZMQ.PUSH)


    private var mSensorManager : SensorManager ?= null
    private var mAccelerometer : Sensor ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        Thread({
            pusher.connect("tcp://172.16.30.143:58588")
        }).start()
        mSensorManager!!.registerListener(this,mAccelerometer,100000)

    }

    override fun onPause() {
        super.onPause()
        mSensorManager!!.unregisterListener(this)
    }
    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            var foot = 2

            var xAxys = event.values[0]
            var yAxys = event.values[1]
            var zAxys = event.values[2]



            var text = foot.toString() + "|" + "%.2f".format(xAxys) +"|"+ "%.2f".format(yAxys)+"|"+ "%.2f".format(zAxys)

            runOnUiThread({
                sensor_text.text = text
            })

            pusher.send(text)

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}
