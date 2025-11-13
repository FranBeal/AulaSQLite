package com.example.aulasqlite

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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

        initData()

        banco = DatabaseHandler(this)
    }

    private fun initData() {
        if (intent.getIntExtra("cod", 0) != 0) {
            binding.etCodigo.setText(
                intent.getIntExtra("cod", 0).toString()
            )
            binding.etNome.setText(
                intent.getStringExtra("nome")
            )
            binding.etTelefone.setText(
                intent.getStringExtra("telefone")
            )
        } else {
            binding.btExcluir.visibility = View.GONE
            binding.btPesquisar.visibility = View.GONE
        }
    }

    private fun setButtonsListeners() {

        binding.btAlterar.setOnClickListener() {
            btAlterarOnClick()
        }
        binding.btExcluir.setOnClickListener() {
            btExcluirOnClick()
        }
        binding.btPesquisar.setOnClickListener() {
            btPesquisarOnClick()
        }

    }

    private fun btAlterarOnClick() {
        if (binding.etCodigo.text.toString().isEmpty()) {
            val cadastro = Cadastro(
                0,
                binding.etNome.text.toString(),
                binding.etTelefone.text.toString()
            )

            banco.incluir(cadastro)
            Toast.makeText(
                this, "Registro incluído!", Toast.LENGTH_LONG
            ).show()

        } else {
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
        finish()
    }

    private fun btExcluirOnClick() {
        banco.excluir(binding.etCodigo.text.toString().toInt())
        Toast.makeText(
            this, "Registro excluido!", Toast.LENGTH_LONG
        ).show()
        finish()
    }

    private fun btPesquisarOnClick() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Codigo da Pessoa")

        val etCodPesquisa = EditText(this)
        builder.setView(etCodPesquisa)
        builder.setCancelable(false)
        builder.setNegativeButton("Fechar", null)
        builder.setPositiveButton("Pesquisar", { dialogInterface, i ->
            val registro = banco.pesquisar(etCodPesquisa.text.toString().toInt())

            if (registro != null) {
                binding.etCodigo.setText(etCodPesquisa.text.toString())
                binding.etNome.setText(registro.nome)
                binding.etTelefone.setText(registro.telefone)
            } else {
                Toast.makeText(this,
                    "Registro não encontrado",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
        builder.show()
    }

}