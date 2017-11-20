package nanchen.weather.data

open class Animal(name: String)

class Person(name: String = "Nameless", surname: String = "Surnameless") : Animal(name) {
    var nickName: String = ""
        get() = field.toUpperCase()
        set(value) {
            field = "Nick Name: $value"
        }
    init {
    }
}

fun add(x: Int, y: Int) : Int = x + y