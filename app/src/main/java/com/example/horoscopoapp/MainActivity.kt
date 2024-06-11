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
        Horoscopo("Aries", "Aries", 0),
        Horoscopo("Tauro", "Tauro", 0),
        Horoscopo("Gemini", "Gemini", 0),
        Horoscopo("Cancer", "Cancer", 0),
        Horoscopo("Leo", "Leo", 0),
        Horoscopo("Virgo", "Virgo", 0),
        Horoscopo("Libra", "Libra", 0),
        Horoscopo("Scorpio", "Scorpio", 0),
        Horoscopo("Sagitario", "Sagitario", 0),
        Horoscopo("Capricornio", "Capricornio", 0),
        Horoscopo("Aquario", "Aquario", 0),
        Horoscopo("Pisces", "Pisces", 0)

    )

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val adapter = HoroscopoAdapter(horoscopoList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}