package nanchen.weather.extensions

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Delegate<T> : ReadWriteProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

private class NotNullSingleValueVar<T>() : ReadWriteProperty<Any?, T> {
    private var value: T? = null
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${desc.name} already initialized")
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("${desc.name} " +
            "not initialized")
    }
}

object DelegateExt {
    fun <T> notNullSingleValue(): ReadWriteProperty<Any?, T> = NotNullSingleValueVar()
}