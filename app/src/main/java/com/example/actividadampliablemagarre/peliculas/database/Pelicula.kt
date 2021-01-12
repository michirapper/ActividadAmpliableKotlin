package com.example.actividadampliablemagarre.peliculas.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pelicula (
    @PrimaryKey(autoGenerate = true) val peliculaId: Int,
    val nombre: String?
)
