package com.example.aulasqlite

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aulasqlite.adapter.MeuAdapter
import com.example.aulasqlite.database.DatabaseHandler
import com.example.aulasqlite.databinding.ActivityListarBinding
import com.example.aulasqlite.databinding.ActivityMainBinding

class ListarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListarBinding
    private lateinit var banco: DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        banco = DatabaseHandler(this)

        binding.fabIncluir.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        initAdapter()
    }

    private fun initAdapter() {
        val registros = banco.listar()
        val adapter = MeuAdapter(this, registros)
        binding.lvPrincial.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        initAdapter()
    }
}