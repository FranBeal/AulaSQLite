package com.example.aulasqlite

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aulasqlite.database.DatabaseHandler
import com.example.aulasqlite.databinding.ActivityMainBinding
import com.example.aulasqlite.entity.Cadastro

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var banco: DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)

        setButtonsListeners()

        banco = DatabaseHandler(this)
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
        val cadastro = Cadastro(
            0,
            binding.etNome.text.toString(),
            binding.etTelefone.text.toString()
        )

        banco.incluir(cadastro)
        Toast.makeText(
            this, "Registro incluído!", Toast.LENGTH_LONG
        ).show()
    }

    private fun btAlterarOnClick(){
        val cadastro = Cadastro(
            binding.etCodigo.text.toString().toInt(),
            binding.etNome.text.toString(),
            binding.etTelefone.text.toString()
        )

        banco.alterar(cadastro)
        Toast.makeText(
            this, "Registro alterado!", Toast.LENGTH_LONG
        ).show()
    }

    private fun btExcluirOnClick(){
        banco.excluir(binding.etCodigo.text.toString().toInt())
        Toast.makeText(
            this, "Registro excluido!", Toast.LENGTH_LONG
        ).show()
    }

    private fun btListarOnClick(){
        val registros = banco.listar()

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
        val registro = banco.pesquisar(binding.etCodigo.text.toString().toInt())

        if(registro != null){
            binding.etNome.setText(registro.nome)
            binding.etTelefone.setText(registro.telefone)
        }else{
            Toast.makeText(
                this, "Registro não encontrado!", Toast.LENGTH_LONG
            ).show()
        }

    }

}