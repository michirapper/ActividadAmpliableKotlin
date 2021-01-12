package com.example.actividadampliablemagarre.peliculas.activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.actividadampliablemagarre.R
import com.example.actividadampliablemagarre.peliculas.database.DataRepository
import com.example.actividadampliablemagarre.peliculas.database.Pelicula
import com.example.actividadampliablemagarre.peliculas.database.Tipo
import com.example.actividadampliablemagarre.peliculas.database.TipoPelicula
import com.example.actividadampliablemagarre.peliculas.fragments.FragmentFichaPelicula
import com.example.actividadampliablemagarre.peliculas.fragments.FragmentListaPelicula
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class MainPeliculas : AppCompatActivity() {

    var frameLayoutFragmentPelicula: FrameLayout? = null
    var frameLayoutFragmentFicha: FrameLayout? = null
    //var frameLayoutLista: FrameLayout? = null
    //  var frameLayoutFicha: FrameLayout? = null

    var listaFragmentAlumno: FragmentListaPelicula? = null

    var fichaFragment: FragmentFichaPelicula? = null

    var segundoFragmentActivo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_peliculas)
        rellenoBBDD()

        var dataRepository = DataRepository(this)

        //Rellenamos el array con al array que hemos recibido de la Query
        val spinner = findViewById<Spinner>(R.id.spinner)
        var pedidosGuardados = dataRepository.getTipo()
        var ArraySpinner = ArrayList<String>()
        ArraySpinner.add("Seleccione uno:")
        for (items in pedidosGuardados) {
            ArraySpinner.add(items.tipoId.toString() + "-" + items.nombre.toString())
        }
        //Rellenamos el spinner con el array que acabamos de hacer
        spinner.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ArraySpinner)
        if (spinner != null) {

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {

                    Toast.makeText(this@MainPeliculas,spinner.selectedItem.toString(),Toast.LENGTH_SHORT).show()
                    verProfesores(spinner.selectedItem.toString())
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

    }

    private fun rellenoBBDD() {
//
//        var tipoComedia = Tipo(1, "Comedia")
//        var tipoBiopic = Tipo(2, "Biopic")
//
//        var Pelicula1 = Pelicula(1, "Star Wars")
//        var Pelicula2 = Pelicula(2, "Terminator")
//        var Pelicula3 = Pelicula(3, "Good Morning Vietman")
//        var Pelicula4 = Pelicula(4, "Bohemian Rhapsody")
//
//
//        var listaPeliculaComedia = ArrayList<Pelicula>()
//        listaPeliculaComedia.add(Pelicula1)
//        listaPeliculaComedia.add(Pelicula2)
//
//        var listaPeliculaBiopic = ArrayList<Pelicula>()
//        listaPeliculaBiopic.add(Pelicula3)
//        listaPeliculaBiopic.add(Pelicula4)
//
//
//        var tipoPelicula1 = TipoPelicula(tipoComedia, listaPeliculaComedia)
//
//        var tipoPelicula2 = TipoPelicula(tipoBiopic, listaPeliculaBiopic)
//
//        var dataRepository = DataRepository(this)
//
//
//        dataRepository.insertTipoPeliculas(tipoPelicula1)
//        dataRepository.insertTipoPeliculas(tipoPelicula2)







        var dataRepository = DataRepository(this)

        var bufferedReaderRecurso = BufferedReader(InputStreamReader(resources.openRawResource(R.raw.peliculas)))
        var textoLeido = bufferedReaderRecurso.readLine()
        val todo = StringBuilder()
        while (textoLeido != null) {
            todo.append(textoLeido + "\n")
            textoLeido = bufferedReaderRecurso.readLine()
        }
        textoLeido = todo.toString()
        bufferedReaderRecurso.close()

        val jsonObject = JSONObject(textoLeido)
        val jsonArray = jsonObject.optJSONArray("tipos")
        var listaPelicula = ArrayList<Pelicula>()

        for (i in 0 until jsonArray.length()) {

            listaPelicula.clear()

            var tipo = Tipo(i + 1, jsonArray.get(i).toString())

            val asignaturaTex = jsonArray.get(i).toString()

            val jsonArrayProf = jsonObject.optJSONArray("peliculas")

            for (i in 0 until jsonArrayProf.length()) {

                val objeto = jsonArrayProf.getJSONObject(i)

                if (objeto.optString("tipo").equals(asignaturaTex)) {

                    var codigoPelicula = objeto.optString("codigo")
                    var tipoPelicula = objeto.optString("titulo")
                    var pelicula = Pelicula(codigoPelicula.toString().toInt(), tipoPelicula.toString())
                    listaPelicula.add(pelicula)
                }

            }


            var asignaturasAlumnos = TipoPelicula(tipo, listaPelicula)


            dataRepository.insertTipoPeliculas(asignaturasAlumnos)

    }
    }
    private fun verProfesores(asignatura: String){
        if (!asignatura.equals("Seleccione uno:")) {

            val asignaturaNombre = Bundle()
            asignaturaNombre.putString("asignatura", asignatura)

            frameLayoutFragmentPelicula = findViewById(R.id.frameLayoutPelicula)

            frameLayoutFragmentFicha = findViewById(R.id.frameLayoutFicha)

            frameLayoutFragmentPelicula = findViewById(R.id.frameLayoutPelicula)

            frameLayoutFragmentPelicula?.removeAllViewsInLayout()
            frameLayoutFragmentFicha?.removeAllViewsInLayout()


            listaFragmentAlumno = FragmentListaPelicula.newInstance()
            listaFragmentAlumno!!.activityListener = activityListener

            listaFragmentAlumno!!.setArguments(asignaturaNombre)

            fichaFragment = FragmentFichaPelicula()

            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            if (frameLayoutFragmentFicha !=null){
                // HORIZONTAL
                fragmentTransaction.add(R.id.frameLayoutPelicula, listaFragmentAlumno!!)
                fragmentTransaction.add(R.id.frameLayoutFicha, fichaFragment!!)
            }
            else {
                fragmentTransaction.add(R.id.frameLayoutPelicula, listaFragmentAlumno!!)
            }

            fragmentTransaction.commit()
        }
    }
    var activityListener = View.OnClickListener {
       // if (frameLayoutFragmentPelicula!=null) {
            if (frameLayoutFragmentFicha == null) {
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.frameLayoutPelicula, fichaFragment!!)
                fragmentTransaction.commit()
                fragmentManager.executePendingTransactions()
                segundoFragmentActivo = true
            }
                Toast.makeText(this@MainPeliculas,listaFragmentAlumno!!.itemSeleccionado.toString(), Toast.LENGTH_SHORT).show()
                fichaFragment!!.updateData(listaFragmentAlumno!!.itemSeleccionado)
                //fichaFragment!!.updateData(listaFragmentAlumno!!.itemSeleccionado)


    }
    override fun onBackPressed() {
        if (segundoFragmentActivo && frameLayoutFragmentPelicula != null){
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayoutPelicula, listaFragmentAlumno!!)
            fragmentTransaction.commit()
            fragmentManager.executePendingTransactions()
            segundoFragmentActivo = false
        }
        else{
            super.onBackPressed()
        }
    }
}