package com.example.actividadampliablemagarre.peliculas.activities

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.actividadampliablemagarre.R
import com.example.actividadampliablemagarre.peliculas.database.DataRepository
import com.example.actividadampliablemagarre.peliculas.database.Pelicula
import com.example.actividadampliablemagarre.peliculas.database.Tipo
import com.example.actividadampliablemagarre.peliculas.database.TipoPelicula
import com.example.actividadampliablemagarre.peliculas.fragments.FragmentFichaPelicula
import com.example.actividadampliablemagarre.peliculas.fragments.FragmentListaPelicula

class MainPeliculas : AppCompatActivity() {

    var frameLayoutFragmentPelicula: FrameLayout? = null
    var frameLayoutFragmentFicha: FrameLayout? = null
    var frameLayoutLista: FrameLayout? = null
    //  var frameLayoutFicha: FrameLayout? = null

    var listaFragmentAlumno: FragmentListaPelicula? = null

    var fichaFragment: FragmentFichaPelicula? = null

    var segundoFragmentActivo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_peliculas)
        rellenoBBDD()

    }
    private fun rellenoBBDD(){

        var tipoComedia = Tipo(1,"Comedia")
        var tipoBiopic = Tipo(2,"Biopic")

        var Pelicula1 = Pelicula(1,"Star Wars")
        var Pelicula2 = Pelicula(2,"Terminator")
        var Pelicula3 = Pelicula(3,"Good Morning Vietman")
        var Pelicula4 = Pelicula(4,"Bohemian Rhapsody")


        var listaPeliculaComedia = ArrayList<Pelicula>()
        listaPeliculaComedia.add(Pelicula1)
        listaPeliculaComedia.add(Pelicula2)

        var listaPeliculaBiopic = ArrayList<Pelicula>()
        listaPeliculaBiopic.add(Pelicula3)
        listaPeliculaBiopic.add(Pelicula4)


        var tipoPelicula1 = TipoPelicula(tipoComedia, listaPeliculaComedia)

        var tipoPelicula2 = TipoPelicula(tipoBiopic, listaPeliculaBiopic)

        var dataRepository = DataRepository(this)


        dataRepository.insertTipoPeliculas(tipoPelicula1)
        dataRepository.insertTipoPeliculas(tipoPelicula2)

    }
}