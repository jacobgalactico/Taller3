package com.example.taller3

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "UsuarioDB"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Usuario")
        onCreate(db)
    }

    fun guardarUsuario(nombre: String) {
        val db = writableDatabase
        db.execSQL("DELETE FROM Usuario") // Borra el nombre anterior
        val contentValues = ContentValues().apply { put("nombre", nombre) }
        db.insert("Usuario", null, contentValues)
    }

    fun obtenerUsuario(): String {
        val db = readableDatabase
        val cursor = db.query("Usuario", arrayOf("nombre"), null, null, null, null, null)
        return if (cursor.moveToFirst()) cursor.getString(0) else "No encontrado"
    }
}