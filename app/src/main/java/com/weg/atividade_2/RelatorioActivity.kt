package com.weg.atividade_2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RelatorioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relatorio)

        val txtResumo = findViewById<TextView>(R.id.txtResumo)
        val btnNovoRegistro = findViewById<Button>(R.id.btnNovoRegistro)

        val maquina = intent.getStringExtra("MAQUINA")
        val turno = intent.getStringExtra("TURNO")
        val produzidas = intent.getIntExtra("PRODUZIDAS", 0)
        val rejeitadas = intent.getIntExtra("REJEITADAS", 0)
        val aprovadas = intent.getIntExtra("APROVADAS", 0)
        val percentual = intent.getDoubleExtra("PERCENTUAL", 0.0)
        val situacao = intent.getStringExtra("SITUACAO")

        val percentualFormatado = String.format("%.2f", percentual)

        val relatorio = """
            Máquina: $maquina
            Turno: $turno
            -------------------------
            Qtd. Produzida: $produzidas
            Qtd. Rejeitada: $rejeitadas
            Qtd. Aprovada: $aprovadas
            -------------------------
            Rejeição: $percentualFormatado%
            Situação: $situacao
        """.trimIndent()

        txtResumo.text = relatorio

        btnNovoRegistro.setOnClickListener {
            finish()
        }
    }
}