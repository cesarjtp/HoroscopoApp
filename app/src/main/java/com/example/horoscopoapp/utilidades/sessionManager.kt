package com.example.horoscopoapp.utilidades

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    companion object {
        const val FAVORITE_HOROSCOPE = "FAVORITE_HOROSCOPE"
    }

    private val sharedPref: SharedPreferences

    init {
        sharedPref = context.getSharedPreferences("horoscope_session", Context.MODE_PRIVATE)
    }
    fun isFavorite(horoscopeId: String) : Boolean {
        return getFavoriteHoroscope()?.equals(horoscopeId) ?: false
    }

    fun setFavoriteHoroscope(id: String) {
        val editor = sharedPref.edit()
        editor.putString(FAVORITE_HOROSCOPE, id)
        editor.apply()
    }

    fun getFavoriteHoroscope() : String? {
        return sharedPref.getString(FAVORITE_HOROSCOPE, null)
    }
}
