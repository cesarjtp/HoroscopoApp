package com.example.horoscopoapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopeapp.HoroscopoAdapter

class MainActivity : AppCompatActivity() {

    val horoscopoList: List<Horoscopo> = listOf(
        Horoscopo("Aries", "Aries", R.drawable.aries, 0),
        Horoscopo("Tauro", "Tauro", R.drawable.taurus, 0),
        Horoscopo("Gemini", "Gemini", R.drawable.gemini, 0),
        Horoscopo("Cancer", "Cancer", R.drawable.cancer, 0),
        Horoscopo("Leo", "Leo", R.drawable.leo, 0),
        Horoscopo("Virgo", "Virgo",R.drawable.virgo, 0),
        Horoscopo("Libra", "Libra", R.drawable.libra, 0),
        Horoscopo("Scorpio", "Scorpio", R.drawable.scorpio, 0),
        Horoscopo("Sagitario", "Sagitario", R.drawable.sagittarius,0),
        Horoscopo("Capricornio", "Capricornio", R.drawable.capricorn,0),
        Horoscopo("Aquario", "Aquario", R.drawable.aquarius,0),
        Horoscopo("Pisces", "Pisces", R.drawable.pisces,0)

    )

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val adapter = HoroscopoAdapter(horoscopoList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        getDrawable(R.drawable.aries)
    }
}