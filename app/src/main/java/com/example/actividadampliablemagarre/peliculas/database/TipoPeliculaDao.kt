package com.example.actividadampliablemagarre.peliculas.database

import androidx.room.*


@Dao
interface TipoPeliculaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: TipoPeliculaCrossRef)

    @Transaction
    @Query("SELECT * FROM tipo WHERE tipoId = :tipoId")
    fun getPelicula(tipoId: Int): Array<TipoPelicula>
}