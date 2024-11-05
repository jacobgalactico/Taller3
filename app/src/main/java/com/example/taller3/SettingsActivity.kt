package com.example.taller3

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private val prefs by lazy { getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val botonColor = findViewById<Button>(R.id.botonColor)
        val botonVolver = findViewById<Button>(R.id.botonVolver)

        // Aplicar el color guardado al iniciar SettingsActivity
        val colorGuardado = prefs.getInt("COLOR_FONDO", Color.WHITE)
        window.decorView.setBackgroundColor(colorGuardado)

        // Cambiar color y guardar en SharedPreferences
        botonColor.setOnClickListener {
            val color = Color.YELLOW // Cambia a cualquier color que prefieras
            prefs.edit().putInt("COLOR_FONDO", color).apply()
            window.decorView.setBackgroundColor(color)
        }

        // Botón para volver a la pantalla de inicio
        botonVolver.setOnClickListener {
            val intent = Intent(this, InicioActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}