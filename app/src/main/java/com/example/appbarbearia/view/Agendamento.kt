package com.example.appbarbearia.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.appbarbearia.R
import com.example.appbarbearia.databinding.ActivityAgendamentoBinding
import java.util.*

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String = ""
    private var hora: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun recoveringData(){
        val nome = intent.extras?.getString("nome").toString()

        val datePicker = binding.idDatePicker
        datePicker.setOnDateChangedListener{ _, year, monthOfYear, dayOfMonth ->

            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        }
    }
}