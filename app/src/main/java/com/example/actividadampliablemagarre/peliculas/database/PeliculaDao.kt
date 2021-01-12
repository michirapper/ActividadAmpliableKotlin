package com.example.actividadampliablemagarre.peliculas.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PeliculaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg pelicula: Pelicula)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(pelicula: List<Pelicula>)

    @Query("SELECT * FROM pelicula WHERE peliculaId = :peliculaId")
    fun getPeliculaOne(peliculaId: Int): Array<Pelicula>
}