package com.br.manieri.lamblog

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nfc = findViewById<Button>(R.id.btnNFC)

        nfc.setOnClickListener {
            val intent = Intent(this, NFCActivity::class.java)
            startActivity(intent)
        }
    }

//    fun goToNFC() {
//        val intent = Intent(this, OutraActivity::class.java)
//        startActivity(intent)
//    }
}