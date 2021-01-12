package com.example.actividadampliablemagarre.peliculas.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pelicula (
    @PrimaryKey val peliculaId: Int,
    val nombre: String?
)
