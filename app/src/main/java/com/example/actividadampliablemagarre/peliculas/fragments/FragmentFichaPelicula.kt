package com.example.actividadampliablemagarre.peliculas.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.actividadampliablemagarre.R
import com.example.actividadampliablemagarre.peliculas.database.DataRepository
import com.example.actividadampliablemagarre.peliculas.model.pelicula


class FragmentFichaPelicula : Fragment(){
    var textViewNombre: TextView? = null
    var thiscontext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_ficha_pelicula, container, false)
        val idAlumno = arguments?.getString("idAlumno")?.toInt()

        thiscontext = container?.getContext();
        var dataRepository = thiscontext?.let { DataRepository(it) }

        var alumnosGuardados = idAlumno?.let { dataRepository?.getPeliculaOne(it.toInt()) }

        textViewNombre = v.findViewById<View>(R.id.textViewFichaNombre) as TextView

        if (alumnosGuardados != null) {
            textViewNombre!!.text = alumnosGuardados.get(0).nombre
        }

        return v
    }
    fun updateData(item: pelicula?) {
        if (item!=null) {
            textViewNombre!!.text = item.nombre
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentFichaPelicula().apply {
            }
    }
}