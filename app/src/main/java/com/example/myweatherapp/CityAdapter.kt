package com.example.myweatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class CityAdapter(private val list: List<City>) :
    RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    class CityViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return CityViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = list[position]
        holder.button.text = city.location

        holder.button.setOnClickListener {
            val action = CityListFragmentDirections.actionCityListFragmentToDetailInfoFragment(city)
            holder.view.findNavController().navigate(action)
        }
    }
}