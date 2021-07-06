package com.example.usoapi

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.usoapi.model.Personaje
import com.squareup.picasso.Picasso

class ActivityDetalle : AppCompatActivity() {
    lateinit var ivPersonaje: ImageView
    lateinit var tvNombreDetalle: TextView
    lateinit var tvEspecie:TextView
    lateinit var tvStatus:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle2)

        ivPersonaje = findViewById(R.id.ivPersonaje)
        tvNombreDetalle = findViewById(R.id.tvNombreDetallle)
        tvEspecie = findViewById(R.id.tvEspecie)
        tvStatus = findViewById(R.id.tvStatus)

        val data = intent.getParcelableExtra<Personaje>("personaje")

        Picasso.get().load(data!!.imagen).into(ivPersonaje)
        tvNombreDetalle.text = data.nombre
        tvEspecie.text = data.especie
        when(data.status){
            "Alive"-> tvStatus.setTextColor(Color.GREEN)
            "unknown"-> tvStatus.setTextColor(Color.YELLOW)
            "Dead"->tvStatus.setTextColor(Color.RED)
        }
        tvStatus.text = data.status


    }
}