package com.example.taller3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var nombreEditText: EditText
    private lateinit var nombreTextView: TextView
    private lateinit var dbHelper: DatabaseHelper
    private val prefs by lazy { getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)

        nombreEditText = findViewById(R.id.nombreEditText)
        nombreTextView = findViewById(R.id.nombreTextView)
        val guardarButton = findViewById<Button>(R.id.guardarButton)
        val cargarButton = findViewById<Button>(R.id.cargarButton)
        val configuracionButton = findViewById<Button>(R.id.configuracionButton)

        // Guardar en SharedPreferences
        guardarButton.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            prefs.edit().putString("NOMBRE", nombre).apply()
            dbHelper.guardarUsuario(nombre)
            mostrarNombre()
        }

        // Cargar desde SQLite
        cargarButton.setOnClickListener {
            val nombre = dbHelper.obtenerUsuario()
            nombreTextView.text = "Hola, $nombre"
        }

        // Botón para ir a SettingsActivity
        configuracionButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        mostrarNombre()
    }

    private fun mostrarNombre() {
        val nombre = prefs.getString("NOMBRE", "Invitado")
        nombreTextView.text = "Hola, $nombre"
    }
}
