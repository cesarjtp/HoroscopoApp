package com.example.horoscopoapp.data

import com.example.horoscopoapp.R

class HoroscopoProvider {
    companion object {
        private val horoscopoList: List<Horoscopo> = listOf(
            Horoscopo("aries", R.string.horoscopo_name_aries, R.string.horoscopo_date_aries, R.drawable.aries),
            Horoscopo("taurus", R.string.horoscopo_name_taurus, R.string.horoscopo_date_taurus, R.drawable.taurus),
            Horoscopo("gemini", R.string.horoscopo_name_gemini, R.string.horoscopo_date_gemini, R.drawable.gemini),
            Horoscopo("cancer", R.string.horoscopo_name_cancer, R.string.horoscopo_date_cancer, R.drawable.cancer),
            Horoscopo("leo", R.string.horoscopo_name_leo, R.string.horoscopo_date_leo, R.drawable.leo),
            Horoscopo("virgo", R.string.horoscopo_name_virgo, R.string.horoscopo_date_virgo, R.drawable.virgo),
            Horoscopo("libra", R.string.horoscopo_name_libra, R.string.horoscopo_date_libra, R.drawable.libra),
            Horoscopo("scorpio", R.string.horoscopo_name_scorpio, R.string.horoscopo_date_scorpio, R.drawable.scorpio),
            Horoscopo("sagittarius", R.string.horoscopo_name_sagittarius, R.string.horoscopo_date_sagittarius, R.drawable.sagittarius),
            Horoscopo("capricorn", R.string.horoscopo_name_capricorn, R.string.horoscopo_date_capricorn, R.drawable.capricorn),
            Horoscopo("aquarius", R.string.horoscopo_name_aquarius, R.string.horoscopo_date_aquarius, R.drawable.aquarius),
            Horoscopo("pisces", R.string.horoscopo_name_pisces, R.string.horoscopo_date_pisces, R.drawable.pisces)
        )

        fun findAll() : List<Horoscopo> {
            return horoscopoList
        }

        fun findById(id: String) : Horoscopo? {
            return horoscopoList.find { it.id == id }
        }
    }
}