package nanchen.weather

import android.app.Application
import nanchen.weather.extensions.DelegateExt

class App: Application() {
    companion object {
        var instance: App by DelegateExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
