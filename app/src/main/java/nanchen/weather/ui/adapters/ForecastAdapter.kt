package nanchen.weather.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
import nanchen.weather.R
import nanchen.weather.extensions.ctx
import nanchen.weather.domain.model.Forecast
import nanchen.weather.domain.model.ForecastList

class ForecastListAdapter(val weekForecast: ForecastList,
                          val itemClick: OnItemClickListener) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx)
                .inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }
    override fun getItemCount(): Int = weekForecast.size()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    operator fun ViewGroup.get(position: Int): View = getChildAt(position)

    class ViewHolder(view: View, val itemClick: OnItemClickListener) : RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date
                itemView.description.text = description
                itemView.max_temperature.text = "${high}"
                itemView.min_temperature.text = "${low}"
                itemView.setOnClickListener { itemClick(forecast) }
            }
        }
    }
}

interface OnItemClickListener {
    operator fun invoke(forecast: Forecast)
}
