package nanchen.weather.ui.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import nanchen.weather.R
import others.Person
import nanchen.weather.domain.model.Forecast
import nanchen.weather.domain.commands.RequestForecastCommand
import nanchen.weather.ui.adapters.ForecastListAdapter
import nanchen.weather.domain.model.ForecastList
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {
    lateinit var forecastList : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        forecastList = find(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        loadForecast()
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

    private fun loadForecast() = async(UI) {
        val result = bg { RequestForecastCommand("Xian,cn").execute() }
        updateUI(result.await(), forecastList)
    }

    private fun updateUI(weekForecast: ForecastList, forecastListView: RecyclerView) {
        val adapter = ForecastListAdapter(weekForecast, object : ForecastListAdapter.OnItemClickListener {
            override fun invoke(forecast: Forecast) {
                toast(forecast.date)
            }
        })
        forecastListView.adapter = adapter
    }
}
