package com.example.actividadampliablemagarre.peliculas.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividadampliablemagarre.R
import com.example.actividadampliablemagarre.peliculas.adapters.ItemAdapterPelicula
import com.example.actividadampliablemagarre.peliculas.database.DataRepository
import com.example.actividadampliablemagarre.peliculas.model.pelicula


class FragmentListaPelicula : Fragment() {
    var activityListener: View.OnClickListener? = null
    var itemSeleccionado: pelicula? = null

    var thiscontext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_lista_pelicula, container, false)

        val asignatura = arguments!!.getString("asignatura")

        val recyclerViewLista: RecyclerView =
            v.findViewById<View>(R.id.recyclerviewlista) as RecyclerView
        thiscontext = container?.getContext();
        var dataRepository = DataRepository(thiscontext!!)
        var numeroAsignatura: Int
        if (asignatura.equals("BBDD")) {
            numeroAsignatura = 2
        } else {
            numeroAsignatura = 1
        }


        var alumnosGuardados = dataRepository.getPelicula(numeroAsignatura)
        var alumno = alumnosGuardados.component1().pelicula

        var items = ArrayList<pelicula>()
        for (i in 0..alumno.size-1) {
            items.add(pelicula(alumno.get(i).peliculaId?.toInt(),alumno.get(i).nombre.toString()))
        }


        val adapter = ItemAdapterPelicula(items) { item ->
            itemSeleccionado = item
            if (activityListener != null) {
                activityListener!!.onClick(view)
            }
        }

        recyclerViewLista.setAdapter(adapter)
        recyclerViewLista.setLayoutManager(LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false))

        return v
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentListaPelicula().apply {}
    }
}