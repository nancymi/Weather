package nanchen.weather.forecast

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import nanchen.weather.R
import nanchen.weather.data.Person
import nanchen.weather.domain.RequestForecastCommand

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val forecastList = findViewById<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        forecastList.adapter = ForecastListAdapter(items)
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
            forecastList.adapter = ForecastListAdapter(result)
        }
    }
}

private val items = listOf(
        "Mon 6/23 - Sunny - 31/17",
        "Tue 6/24 - Foggy - 21/8",
        "Wed 6/25 - Cloudy - 22/17",
        "Thurs 6/26 - Rainy - 18/11",
        "Fri 6/27 - Foggy - 21/10",
        "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
        "Sun 6/29 - Sunny - 20/7"
)
