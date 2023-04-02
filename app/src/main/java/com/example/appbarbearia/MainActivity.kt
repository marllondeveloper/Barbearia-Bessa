package com.example.appbarbearia

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.ContentLoadingProgressBar
import com.example.appbarbearia.databinding.ActivityHomeBinding
import com.example.appbarbearia.databinding.ActivityMainBinding
import com.example.appbarbearia.view.Home
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupFunctions()
    }

    private fun setupFunctions(){
        actionClicked()
    }

    private fun actionClicked(){
        binding.idButtonLogin.setOnClickListener {

            val nome = binding.idEditTextName.text.toString()
            val senha = binding.idEditTextSenha.text.toString()

            when{
                nome.isEmpty() -> {
                    mensagem(it, "Digite seu nome!")
                }senha.isEmpty() -> {
                    mensagem(it, "Digite a senha!")
                }senha.length <= 5 -> {
                    mensagem(it, "A senha deve conter no mínimo 6 caracteres")
                }else -> {navigateToHome(nome)

                }
            }
        }
    }

    private fun mensagem(view: View, mensagem: String){

        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor("#FF0000"))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()

    }

    private fun navigateToHome(nome: String){
        val intent = Intent(this, Home::class.java)
        intent.putExtra("nome", nome)
        startActivity(intent)
    }
}