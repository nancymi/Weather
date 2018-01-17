package nanchen.weather.data.remote.model

data class City(val id: Long, val name: String, val coord: Coordinates,
                val country: String, val population: Int)