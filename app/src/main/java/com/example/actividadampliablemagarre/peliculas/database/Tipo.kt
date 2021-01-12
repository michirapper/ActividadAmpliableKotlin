package com.example.actividadampliablemagarre.peliculas.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tipo(
    @PrimaryKey(autoGenerate = true) val tipoId: Int,
    val nombre: String?
)