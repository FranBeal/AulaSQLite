package com.example.aulasqlite.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.aulasqlite.entity.Cadastro

class DatabaseHandler(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(banco: SQLiteDatabase?) {
        banco?.execSQL("CREATE TABLE IF NOT EXISTS ${TABLE_NAME} (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "telefone TEXT)"
        )
    }

    override fun onUpgrade(banco: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        banco?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    fun incluir(cadastro: Cadastro){
        val banco: SQLiteDatabase = this.writableDatabase
        val registro = ContentValues()
        registro.put("nome", cadastro.nome)
        registro.put("telefone", cadastro.telefone)

        banco.insert(TABLE_NAME, null, registro)
    }

    fun alterar(cadastro: Cadastro){
        val banco: SQLiteDatabase = this.writableDatabase
        val registro = ContentValues()
        registro.put("_id", cadastro._id)
        registro.put("nome", cadastro.nome)
        registro.put("telefone", cadastro.telefone)

        banco.update(TABLE_NAME, registro,
            "_id= ${cadastro._id}", null)
    }

    fun excluir(id: Int){
        val banco: SQLiteDatabase = this.writableDatabase
        banco.delete(TABLE_NAME, "_id= ${id}", null)
    }

    fun pesquisar(id: Int): Cadastro?{
        val banco: SQLiteDatabase = this.writableDatabase
        val registros = banco.query(TABLE_NAME,null,
            "_id=${id}",null, null,
            null,null, null)

        if(registros.moveToNext()){
            val cadastro = Cadastro(
                id,
                registros.getString(registros.getColumnIndex("nome").toInt()),
                registros.getString(registros.getColumnIndex("telefone").toInt())
            )
            return cadastro
        }else{
            return null
        }
    }

    fun listar(): Cursor {
        val banco: SQLiteDatabase = this.writableDatabase
        val registros = banco.query(TABLE_NAME,null,null,
            null,null,null,null)
        return registros

    }

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "dbFile.sqlite"
        private const val TABLE_NAME = "cadastro"
    }
}