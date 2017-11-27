package nanchen.weather.forecast

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Toast
import nanchen.weather.R
import nanchen.weather.data.Person
import nanchen.weather.domain.Forecast
import nanchen.weather.domain.RequestForecastCommand

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val forecastList = findViewById<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        async(forecastList)
    }

    fun toast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }

    fun niceToast(message: String,
                  tag: String = javaClass.getSimpleName(),
                  length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, "[$tag] $message", length).show()
    }

    fun defineVariable() {
        val s = "Example"
        val i = 23
        val actionBar = supportActionBar

        val a: Any = 23
        val c: Context = baseContext
    }

    fun defineAttribute() {
        val person = Person()
        person.nickName = "nickName"
        val nickName = person.nickName

    }

    fun async(forecastList: RecyclerView) {
        val result = RequestForecastCommand("94043").execute();
        runOnUiThread {
            forecastList.adapter = ForecastListAdapter(result, object : ForecastListAdapter.OnItemClickListener {
                override fun invoke(forecast: Forecast) {
                    toast(forecast.date)
                }

            })
        }
    }
}
