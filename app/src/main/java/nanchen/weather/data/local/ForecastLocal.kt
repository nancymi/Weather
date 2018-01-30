package nanchen.weather.data.local

import android.database.sqlite.SQLiteDatabase
import nanchen.weather.data.local.model.CityForecast
import nanchen.weather.data.local.model.DayForecast
import nanchen.weather.domain.datasource.ForecastDataSource
import nanchen.weather.domain.model.Forecast
import nanchen.weather.domain.model.ForecastList
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder
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
        //TODO
    }

    fun <T : Any> SelectQueryBuilder.parseList(
            parser: (Map<String, Any?>) -> T): List<T> =
            parseList(object : MapRowParser<T> {
                override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
            })

    fun <T : Any> SelectQueryBuilder.parseOpt(
            parser: (Map<String, Any?>) -> T): T? =
            parseOpt(object : MapRowParser<T> {
                override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
            })

    fun SelectQueryBuilder.byId(id: Long) = whereSimple("_id = ?", id.toString())

    fun SQLiteDatabase.clear(tableName: String) {
        execSQL("delete from $tableName")
    }
}
