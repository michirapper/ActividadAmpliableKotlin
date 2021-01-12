package com.example.actividadampliablemagarre.peliculas.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

data class TipoPelicula(
    @Embedded var tipo: Tipo,

    @Relation(
        parentColumn = "tipoId",
        entity = Pelicula::class,
        entityColumn = "peliculaId",
        associateBy = Junction(value = TipoPeliculaCrossRef::class,
            parentColumn = "tipoId",
            entityColumn = "peliculaId")
    )

    var pelicula: List<Pelicula>
)

@Entity(primaryKeys = ["tipoId", "peliculaId"])
data class TipoPeliculaCrossRef(
    val tipoId: Int,
    val peliculaId: Int
)