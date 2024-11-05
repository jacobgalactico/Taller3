package com.example.taller3

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class InicioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        //Guardar el color de fondo
        val prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        val colorGuardado = prefs.getInt("COLOR_FONDO", Color.WHITE)
        window.decorView.setBackgroundColor(colorGuardado)

        val saludoTextView = findViewById<TextView>(R.id.saludoTextView)
        val botonContinuar = findViewById<Button>(R.id.botonContinuar)

        // Determinar el saludo según la hora del día
        val hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        saludoTextView.text = when {
            hora in 6..12 -> "Buenos días"
            hora in 13..18 -> "Buenas tardes"
            else -> "Buenas noches"
        }

        // Botón para ir a MainActivity
        botonContinuar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
