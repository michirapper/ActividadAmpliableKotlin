package com.example.actividadampliablemagarre.peliculas.database

import android.content.Context
import android.os.AsyncTask

class DataRepository(context: Context) {
    private val tipoDao: TipoDao? = AppDatabase.getInstance(context)?.tipoDao()
    private val peliculaDao: PeliculaDao? = AppDatabase.getInstance(context)?.peliculaDao()
    private val tipoPeliculaDao: TipoPeliculaDao?= AppDatabase.getInstance(context)?.tipoPeliculaDao()

    fun insertTipoPeliculas(tipoPelicula: TipoPelicula):Int{
        if (tipoDao != null && peliculaDao !=null && tipoPeliculaDao!= null) {
            return InsertTipoPeliculasAsyncTask(tipoDao, peliculaDao, tipoPeliculaDao).execute(tipoPelicula).get()
        }
        return -1
    }

    fun getTipo(): Array<Tipo>{
        return GetTipo(tipoDao!!).execute().get()
    }

    fun getPelicula(idTipo: Int): Array<TipoPelicula>{
        return GetPelicula(tipoPeliculaDao!!, idTipo).execute().get()
    }

    fun getPeliculaOne(peliculaId: Int): Array<Pelicula>{
        return GetPeliculaOne(peliculaDao!!, peliculaId).execute().get()
    }

    private class InsertTipoPeliculasAsyncTask(private val tipoDao: TipoDao, private val peliculaDao: PeliculaDao, private val tipoPeliculaDao: TipoPeliculaDao): AsyncTask<TipoPelicula, Void, Int>(){
        override fun doInBackground(vararg tipoPelicula: TipoPelicula?): Int {
            try{
                for (tipoPelicula in tipoPelicula){
                    if (tipoPelicula !=null){
                        tipoDao.insertAll(tipoPelicula.tipo)
                        peliculaDao.insertAll(tipoPelicula.pelicula)
                        for (pelicula in tipoPelicula.pelicula){
                            tipoPeliculaDao.insert(TipoPeliculaCrossRef(tipoPelicula.tipo.tipoId, pelicula.peliculaId))
                        }
                    }
                }

                return 0
            }
            catch (exception: Exception){
                return -1
            }
        }
    }

    private class GetTipo(private val tipoDao: TipoDao) :AsyncTask<Void, Void, Array<Tipo>>(){
        override fun doInBackground(vararg params: Void?): Array<Tipo> {
            return tipoDao.getTipo()
        }
    }

    private class GetPelicula(private val tipoPeliculaDao: TipoPeliculaDao, private val idAsignatura: Int) :AsyncTask<Void, Void, Array<TipoPelicula>>(){
        override fun doInBackground(vararg params: Void?): Array<TipoPelicula> {
            return tipoPeliculaDao.getPelicula(idAsignatura)
        }
    }
    private class GetPeliculaOne(private val peliculaDao: PeliculaDao, private val idPelicula: Int) :AsyncTask<Void, Void, Array<Pelicula>>(){
        override fun doInBackground(vararg params: Void?): Array<Pelicula> {
            return peliculaDao.getPeliculaOne(idPelicula)
        }
    }
}