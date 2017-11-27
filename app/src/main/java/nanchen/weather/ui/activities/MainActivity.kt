package nanchen.weather.ui.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import android.widget.Toast
import nanchen.weather.R
import others.Person
import nanchen.weather.domain.model.Forecast
import nanchen.weather.domain.commands.RequestForecastCommand
import nanchen.weather.ui.adapters.ForecastListAdapter
import nanchen.weather.data.remote.Request
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val forecastList: RecyclerView = find(R.id.forecast_list)
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

            fun async(url: String) {
                Request(url).run()
                runOnUiThread {
                    longToast("Request performed")
                }
            }

            fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
                Toast.makeText(this, message, length).show()
            }
        }
    }
}
