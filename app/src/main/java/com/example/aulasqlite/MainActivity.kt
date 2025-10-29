package com.example.aulasqlite

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aulasqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)

        setButtonsListeners()
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

    private fun btIncluirOnClick(){}
    private fun btAlterarOnClick(){}
    private fun btExcluirOnClick(){}
    private fun btPesquisarOnClick(){}
    private fun btListarOnClick(){}
}