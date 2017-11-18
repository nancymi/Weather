package nanchen.weather.data

open class Animal(name: String)

class Person(name: String, surname: String) : Animal(name) {
    init {
    }
}

fun add(x: Int, y: Int) : Int = x + y