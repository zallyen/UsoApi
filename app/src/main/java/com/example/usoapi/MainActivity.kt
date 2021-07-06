package com.example.usoapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.usoapi.adaptador.AdaptadorPersonaje
import com.example.usoapi.model.Personaje
import org.json.JSONArray
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    lateinit var personajesAdapter:AdaptadorPersonaje
    lateinit var personajes:ArrayList<Personaje>
    lateinit var recyclerPersonajes:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerPersonajes = findViewById(R.id.recyclerPersonajes)

        personajes = ArrayList<Personaje>()

        personajesAdapter = AdaptadorPersonaje(personajes,this)

        recyclerPersonajes.layoutManager = GridLayoutManager(this,1)
        recyclerPersonajes.adapter = personajesAdapter

        obtenerDatos()



    }


    fun obtenerDatos(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://rickandmortyapi.com/api/character/"

        val responseDatosPersonajes = JsonObjectRequest(Request.Method.GET,
                url,
                null,
            { response ->
                val listaPersonajes = JSONArray(response.getJSONArray("results").toString())

                for (index in 0..listaPersonajes.length()-1){
                    try {
                        val personajeJSON = listaPersonajes.getJSONObject(index)
                        val nombre = personajeJSON.getString("name")
                        val status = personajeJSON.getString("status")
                        val especie = personajeJSON.getString("species")
                        val imagen = personajeJSON.getString("image")
                        val personaje = Personaje(nombre,status,especie,imagen)
                        personajes.add(personaje)

                    }catch (ex:JSONException){
                        Log.d("respuesta",ex.toString())
                    }
                }

                Log.d("respuesta",response.toString())

            },
            { error->
                Log.e("respuesta",error.toString())
            })

        queue.add(responseDatosPersonajes)

        personajesAdapter.notifyDataSetChanged()
    }
}