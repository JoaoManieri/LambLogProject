package com.br.manieri.lamblog

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AnimalActivity :  AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal)

        Log.e("TAG", "onCreate: foi criado", )

        val i = findViewById<TextView>(R.id.tvRFID)
        i.text = NFCActivity.idDiscovery

        val timeSpinner: Spinner = findViewById(R.id.spinnerr_time_selector)
        val timeAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.times_string_array, // Seu array de strings aqui
            R.layout.spinner_item
        )
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timeSpinner.adapter = timeAdapter

        val sizeSpinner: Spinner = findViewById(R.id.spinner_size_selector)
        val sizeAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.size_string_array, // Seu array de strings aqui
            R.layout.spinner_item
        )
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sizeSpinner.adapter = sizeAdapter

    }
}