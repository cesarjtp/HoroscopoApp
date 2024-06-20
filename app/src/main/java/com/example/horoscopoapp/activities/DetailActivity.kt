package com.example.horoscopoapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.example.horoscopoapp.data.Horoscopo
import com.example.horoscopoapp.data.HoroscopoProvider
import com.example.horoscopoapp.R
import com.example.horoscopoapp.utilidades.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_HOROSCOPE_ID = "HOROSCOPE_ID"
    }

    lateinit var horoscopo: Horoscopo
    var isFavorite = false

    lateinit var imageView: ImageView
    lateinit var textView: TextView
    lateinit var favoriteMenuItem: MenuItem
    lateinit var session: SessionManager
    lateinit var dailyHoroscopeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        session = SessionManager(this)

        val id: String = intent.getStringExtra(EXTRA_HOROSCOPE_ID)!!

        horoscopo = HoroscopoProvider.findById(id)!!

        isFavorite = session.getFavoriteHoroscope()?.equals(horoscopo.id) ?: false
        isFavorite = session.isFavorite(horoscopo.id)

        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.imageView)
        dailyHoroscopeTextView = findViewById((R.id.textView))
        textView.setText(horoscopo.name)
        imageView.setImageResource(horoscopo.logo)

        supportActionBar?.setTitle(horoscopo.name)
        supportActionBar?.setSubtitle(horoscopo.descrip)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getDailyHoroscope()
    }

    fun setFavoriteIcon () {
        if (isFavorite) {
            favoriteMenuItem.setIcon(android.R.drawable.btn_star_big_on)

        } else {
            favoriteMenuItem.setIcon(android.R.drawable.btn_star_big_off)
        }
    }
    fun getDailyHoroscope() {
        // Llamada en hilo secundario
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Declaramos la url
                val url = URL("https://horoscope-app-api.vercel.app/api/v1/get-horoscope/daily?sign=${horoscopo.id}&day=TODAY")
                val con = url.openConnection() as HttpsURLConnection
                con.requestMethod = "GET"
                val responseCode = con.responseCode
                Log.i("HTTP", "Response Code :: $responseCode")

                // Preguntamos si hubo error o no
                if (responseCode == HttpsURLConnection.HTTP_OK) { // Ha ido bien
                    // Metemos el cuerpo de la respuesta en un BurfferedReader
                    val bufferedReader = BufferedReader(InputStreamReader(con.inputStream))
                    var inputLine: String?
                    val response = StringBuffer()
                    while (bufferedReader.readLine().also { inputLine = it } != null) {
                        response.append(inputLine)
                    }
                    bufferedReader.close()

                    // Parsear JSON
                    val json = JSONObject(response.toString())
                    val result =  json.getJSONObject("data").getString("horoscope_data")

                    // Ejecutamos en el hilo principal
                    /*CoroutineScope(Dispatchers.Main).launch {
                    }*/
                    runOnUiThread {
                        dailyHoroscopeTextView.text = result
                    }

                } else { // Hubo un error
                    Log.w("HTTP", "Response :: Hubo un error")
                }
            } catch (e: Exception) {
                Log.e("HTTP", "Response Error :: ${e.stackTraceToString()}")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.manu_activity_detail, menu)
        favoriteMenuItem = menu.findItem(R.id.menu_favorite)
        setFavoriteIcon()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.menu_favorite -> {
                if (isFavorite) {
                    session.setFavoriteHoroscope("")
                } else {
                    session.setFavoriteHoroscope(horoscopo.id)
                }
                isFavorite = !isFavorite
                setFavoriteIcon()
                true
            }
            R.id.menu_buscar -> {
                val sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_SEND)
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Este es mi texto enviado.")
                sendIntent.setType("text/plain")

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}