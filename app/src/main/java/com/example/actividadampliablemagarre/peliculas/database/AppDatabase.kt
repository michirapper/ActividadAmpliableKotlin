package com.example.actividadampliablemagarre.peliculas.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tipo::class, Pelicula::class, TipoPeliculaCrossRef::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tipoDao(): TipoDao
    abstract fun peliculaDao(): PeliculaDao
    abstract fun tipoPeliculaDao(): TipoPeliculaDao

    companion object {
        private const val DATABASE_NAME = "peliculas_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )./*addMigrations(MIGRATION_1_2).*/build()
            }
            return INSTANCE
        }

       /* val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE 'Product' ('productId' INTEGER NOT NULL, 'nombre' TEXT, 'precio' INTEGER, PRIMARY KEY('productId'))")
            }
        }*/

    }
}