package com.example.horoscopoapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopoapp.data.Horoscopo
import com.example.horoscopoapp.adapter.HoroscopoAdapter
import com.example.horoscopoapp.data.HoroscopoProvider
import com.example.horoscopoapp.R


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    lateinit var horoscopoList: List<Horoscopo>

    lateinit var adapter: HoroscopoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        horoscopoList = HoroscopoProvider.findAll()


        adapter = HoroscopoAdapter(horoscopoList) { position ->
            navigateToDetail(horoscopoList[position])
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
    override fun onResume() {
        super.onResume()
        adapter.updateData(horoscopoList)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.manu_activity_main, menu)

        val searchViewItem = menu.findItem(R.id.menu_buscar)
        val searchView = searchViewItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                horoscopoList = HoroscopoProvider.findAll()
                if (newText != null) {
                    horoscopoList = HoroscopoProvider.findAll()
                        .filter { getString(it.name).contains(newText, true) }
                    adapter.updateData(horoscopoList)
                }
                return true
            }
        })

        return true
    }

    fun navigateToDetail(horoscopo: Horoscopo) {
        val intent: Intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_HOROSCOPE_ID, horoscopo.id)
        startActivity(intent)
    }
}
