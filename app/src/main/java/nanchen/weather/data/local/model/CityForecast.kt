package nanchen.weather.data.local.model

class CityForecast(val map: MutableMap<String, Any?>,
                   val dailyForecast: List<DayForecast>) {
    var _id: Long by map
    var city: String by map
    var country: String by map

    constructor(id: Long, city: String, country: String, dailyForecast: List<DayForecast>):
            this(HashMap(), dailyForecast) {
        this._id = id
        this.city = city
        this.country = country
    }
}
