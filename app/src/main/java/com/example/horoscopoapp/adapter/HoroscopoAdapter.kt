package com.example.horoscopoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopoapp.R
import com.example.horoscopoapp.data.Horoscopo
import com.example.horoscopoapp.utilidades.SessionManager


class HoroscopoAdapter(
    private var dataSet: List<Horoscopo>,
    private val onItemClickListener: (Int) -> Unit
) :
    RecyclerView.Adapter<HoroscopoViewHolder>() {
    /*
        fun updateData(list :List<Horoscopo>) {
            this.items = list
            notifyDataSetChanged()
        }
    */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopoViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscopo, parent, false)
        return HoroscopoViewHolder(view)
    }

    override fun onBindViewHolder(holder: HoroscopoViewHolder, position: Int) {
        val horoscope = dataSet[position]
        holder.render(horoscope)
        holder.itemView.setOnClickListener {
            onItemClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    // Este método sirve para actualizar los datos
    fun updateData(newDataSet: List<Horoscopo>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }
}

class HoroscopoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val nameTextView: TextView
    private val descTextView: TextView
    private val logoImageView: ImageView
    private val favoriteImageView: ImageView

    init {
        nameTextView = view.findViewById(R.id.nameTextView)
        descTextView = view.findViewById(R.id.descTextView)
        logoImageView = view.findViewById(R.id.logoImageView)
        favoriteImageView = view.findViewById(R.id.imageView)
    }

    fun render(horoscopo: Horoscopo) {
        nameTextView.setText(horoscopo.name)
        descTextView.setText(horoscopo.descrip)
        logoImageView.setImageResource(horoscopo.logo)

        val context = itemView.context
        var isFavorite = SessionManager(context).isFavorite(horoscopo.id)
        if (isFavorite) {
            favoriteImageView.visibility = View.VISIBLE
        } else {
            favoriteImageView.visibility = View.GONE
        }
    }
}



