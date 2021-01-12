package com.example.actividadampliablemagarre.peliculas.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TipoDao {

    @Query("Select * from tipo")
    fun getTipo(): Array<Tipo>

    @Insert
    fun insertAll(tipo: List<Tipo>)

    @Insert
    fun insertAll(vararg tipo: Tipo)
}