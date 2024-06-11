package com.example.horoscopeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopoapp.Horoscopo
import com.example.horoscopoapp.R

class HoroscopoAdapter(private val dataSet: List<Horoscopo>) :
    RecyclerView.Adapter<HoroscopoViewHolder>() {

    // Este método se llama para crear nuevas celdas,
    // y se crear las justas para mostrar en pantalla,
    // Ya que intentará reciclar las que no se vean
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_horoscopo, parent, false)

        return HoroscopoViewHolder(view)
    }

    // Este método simplemente es para decir cuantos elementos queremos mostrar
    override fun getItemCount(): Int {
        return dataSet.size
    }

    // Este método se llama cada vez que se va a visualizar una celda,
    // y lo utilizaremos para mostrar los datos de esa celda
    override fun onBindViewHolder(holder: HoroscopoViewHolder, position: Int) {
        val horoscopo = dataSet[position]
        holder.textView.text = horoscopo.name

    }

}

// Esta clase se encarga de guardarnos la vista y no tener que inflarla de nuevo
class HoroscopoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView
    val descrip: TextView
    val logoView : ImageView

    init {
        textView = view.findViewById(R.id.nameTextView)
        descrip = view.findViewById(R.id.descrip)
        logoView = view.findViewById(R.id.image)
    }
}