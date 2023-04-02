package com.example.appbarbearia.view

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.appbarbearia.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String = ""
    private var hora: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val nome = intent.extras?.getString("nome").toString()

        val datePicker = binding.idDatePicker
        datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->

            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            var dia = dayOfMonth.toString()
            val mes: String

            if (dayOfMonth < 10) {
                dia = "0$dayOfMonth"
            }
            if (monthOfYear < 10) {
                mes = "" + (monthOfYear + 1)
            } else {
                mes = (monthOfYear + 1).toString()
            }

            data = "$dia / $mes / $year"
        }

        binding.idTimePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            val minuto: String

            if (minute < 10) {
                minuto = "0$minute"
            } else {
                minuto = minute.toString()
            }

            hora = "$hourOfDay:$minuto"
        }

        binding.idTimePicker.setIs24HourView(true)

        binding.idButtonAgendar.setOnClickListener {
            val barbeiro1 = binding.idBarbeiro1
            val barbeiro2 = binding.idBarbeiro2
            val barbeiro3 = binding.idBarbeiro3
            val barbeiro4 = binding.idBarbeiro4

            when {
                hora.isEmpty() -> {
                    mensagem(it, "Defina um Horário", "#FF0000")
                }
                hora < "8:00" && hora > "19:00" -> {
                    mensagem(it, "Barbearia FECHADA", "#FF0000")
                }
                data.isEmpty() -> {
                    mensagem(it, "Defina uma Data", "#FF0000")
                }
                barbeiro1.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it, nome, "Christian Bessa", data,hora)
                }
                barbeiro2.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it, nome, "Marllon Silibri", data,hora)
                }
                barbeiro3.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it, nome, "Fabricio da Silva", data,hora)
                }
                barbeiro4.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it, nome, "Cleber Gomes", data,hora)
                }
                else -> {
                    mensagem(it, "Escolha um profissional", "#FF0000")
                }
            }
        }


    }

    private fun mensagem(view: View, mensagem: String, cor: String) {

        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()

    }

    private fun salvarAgendamento(
        view: View,
        cliente: String,
        barbeiro: String,
        data: String,
        hora: String
    ) {
        val db = FirebaseFirestore.getInstance()

        val dadosUsuario = hashMapOf(

            "cliente" to cliente,
            "barbeiro" to barbeiro,
            "data" to data,
            "hora" to hora

        )

        db.collection("agendamento").document(cliente).set(dadosUsuario).addOnCompleteListener {
            mensagem(view, "agendamento realizado com sucesso", "#FF03DAC5")
        }.addOnFailureListener{
            mensagem(view, "erro no servidor", "#FF0000")
        }
    }
}
























