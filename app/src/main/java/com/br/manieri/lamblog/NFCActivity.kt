package com.br.manieri.lamblog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.util.Log
import android.widget.TextView
import android.widget.Toast

class NFCActivity : AppCompatActivity() {

    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC não está disponível neste dispositivo", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        pendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    override fun onResume() {
        super.onResume()
        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, null, null)
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleNfcIntent(intent)
    }

    private fun handleNfcIntent(intent: Intent) {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
            val rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
            if (rawMessages != null) {
                val messages = getMessages(rawMessages)
                processNdefMessages(messages)
            }
        }
    }

    private fun getMessages(rawMessages: Array<*>): List<NdefMessage> {
        val messages = mutableListOf<NdefMessage>()
        for (raw in rawMessages) {
            if (raw is NdefMessage) {
                messages.add(raw)
            }
        }
        return messages
    }

    private fun processNdefMessages(messages: List<NdefMessage>) {

        val nfc = findViewById<TextView>(R.id.tvNFCContent)

        for (message in messages) {
            for (record in message.records) {
                val payload = String(record.payload)

                // Log do conteúdo lido no LogCat
                Log.d("NFCReader", "Dados NFC: $payload")

                // Ou você pode usar Toast para exibir na interface do usuário
                runOnUiThread {
                    Log.e("TAG", "processNdefMessages: ${record.id}", )
                    nfc.text = "payload"
                    Toast.makeText(this, "Dados NFC: $payload", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}