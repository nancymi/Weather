package nanchen.weather.data.local

import nanchen.weather.data.local.model.CityForecast
import nanchen.weather.data.local.model.DayForecast
import nanchen.weather.domain.datasource.ForecastDataSource
import nanchen.weather.domain.model.ForecastList
import nanchen.weather.extensions.byId
import nanchen.weather.extensions.clear
import nanchen.weather.extensions.parseList
import nanchen.weather.extensions.parseOpt
import nanchen.weather.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class ForecastLocal(private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                    private val dataMapper: LocalDataMapper = LocalDataMapper()): ForecastDataSource {
    override fun requestDayForecast(id: Long) = forecastDbHelper.use {
        val forecast = select(DayForecastTable.NAME).byId(id)
                .parseOpt { DayForecast(HashMap(it)) }

        forecast?.let { dataMapper.convertDayToDomain(it) }
    }

    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }

        val cityRequest = "${CityForecastTable.ID} = ?"
        val city = select(CityForecastTable.NAME)
                .whereSimple(cityRequest, zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }
        city?.let { dataMapper.convertToDomain(it) }
    }

    fun saveForecast(converted: ForecastList) = forecastDbHelper.use {
        clear(DayForecastTable.NAME)
        clear(CityForecastTable.NAME)

        with(dataMapper.convertFromDomain(converted)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }
}
