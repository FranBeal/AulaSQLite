package com.example.aulasqlite

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aulasqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var banco: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)

        setButtonsListeners()

        banco = SQLiteDatabase.openOrCreateDatabase(
            this.getDatabasePath("dbFile.sqlite"),
            null
        )

        banco.execSQL("CREATE TABLE IF NOT EXISTS cadastro (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "telefone TEXT)"
        )
    }

    private fun setButtonsListeners() {
        binding.btIncluir.setOnClickListener(){
            btIncluirOnClick()
        }
        binding.btAlterar.setOnClickListener(){
            btAlterarOnClick()
        }
        binding.btExcluir.setOnClickListener(){
            btExcluirOnClick()
        }
        binding.btPesquisar.setOnClickListener(){
            btPesquisarOnClick()
        }
        binding.btListar.setOnClickListener(){
            btListarOnClick()
        }
    }

    private fun btIncluirOnClick(){
        val registro = ContentValues()
        registro.put("nome", binding.etNome.text.toString())
        registro.put("telefone", binding.etTelefone.text.toString())

        banco.insert("cadastro", null, registro)
        Toast.makeText(
            this, "Registro incluído!", Toast.LENGTH_LONG
        ).show()
    }

    private fun btAlterarOnClick(){
        val registro = ContentValues()
        registro.put("nome", binding.etNome.text.toString())
        registro.put("telefone", binding.etTelefone.text.toString())

        banco.update("cadastro", registro,
            "_id=${binding.etCodigo.text.toString()}",
            null)
        Toast.makeText(
            this, "Registro alterado!", Toast.LENGTH_LONG
        ).show()
    }

    private fun btExcluirOnClick(){
        banco.delete("cadastro",
            "_id=${binding.etCodigo.text.toString()}",
            null)
        Toast.makeText(
            this, "Registro excluido!", Toast.LENGTH_LONG
        ).show()
    }

    private fun btListarOnClick(){
        val registros = banco.query("cadastro",null,null,
            null,null,null,null)
        val saida = StringBuilder()
        while(registros.moveToNext()){
            saida.append(registros.getString(1))
            saida.append(" - ")
            saida.append(registros.getString(2))
            saida.append("\n")
        }
        Toast.makeText(
            this, saida.toString(), Toast.LENGTH_LONG
        ).show()
    }

    private fun btPesquisarOnClick(){
        val registros = banco.query("cadastro",null,
            "_id=?",arrayOf(binding.etCodigo.text.toString()),
            null,null,null)

        if(registros.moveToNext()){
            binding.etNome.setText(registros.getString(1))
            binding.etTelefone.setText(registros.getString(2))
        }else{
            Toast.makeText(
                this, "Registro não encontrado!", Toast.LENGTH_LONG
            ).show()
        }

    }

}