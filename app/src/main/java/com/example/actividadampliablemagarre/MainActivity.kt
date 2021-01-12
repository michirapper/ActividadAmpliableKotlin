package com.example.actividadampliablemagarre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.actividadampliablemagarre.peliculas.activities.MainPeliculas

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goPeliculas(view: View) {
        val goPeliculas = Intent(this, MainPeliculas::class.java)
        startActivity(goPeliculas)
    }
}