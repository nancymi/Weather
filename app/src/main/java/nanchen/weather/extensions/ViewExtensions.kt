package nanchen.weather.extensions

import android.content.Context
import android.view.View
import android.widget.TextView

val View.ctx: Context
    get() = context

var TextView.text: CharSequence
    get() = getText()
    set(v) = setText(v)