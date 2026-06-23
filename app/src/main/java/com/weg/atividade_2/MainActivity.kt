package com.weg.atividade_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtMaquina = findViewById<EditText>(R.id.edtMaquina)
        val edtProduzidas = findViewById<EditText>(R.id.edtProduzidas)
        val edtRejeitadas = findViewById<EditText>(R.id.edtRejeitadas)
        val spnTurno = findViewById<Spinner>(R.id.spnTurno)
        val btnGerarRelatorio = findViewById<Button>(R.id.btnGerarRelatorio)

        btnGerarRelatorio.setOnClickListener {
            val maquina = edtMaquina.text.toString()
            val produzidasStr = edtProduzidas.text.toString()
            val rejeitadasStr = edtRejeitadas.text.toString()
            val turno = spnTurno.selectedItem.toString()

            if (maquina.isEmpty() || produzidasStr.isEmpty() || rejeitadasStr.isEmpty()) {
                android.widget.Toast.makeText(this, "Preencha todos os campos!", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val produzidas = produzidasStr.toInt()
            val rejeitadas = rejeitadasStr.toInt()

            if (produzidas <= 0) {
                android.widget.Toast.makeText(this, "A produção deve ser maior que zero!", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val aprovadas = produzidas - rejeitadas
            val percentual = (rejeitadas.toDouble() * 100) / produzidas.toDouble()

            val situacao = when{
                percentual <= 3.0 -> "Excelente"
                percentual <= 7.0 -> "Atenção"
                else -> "Critica"
            }

            val intent = Intent(this, RelatorioActivity::class.java).apply {
                putExtra("MAQUINA", maquina)
                putExtra("TURNO", turno)
                putExtra("PRODUZIDAS", produzidas)
                putExtra("REJEITADAS", rejeitadas)
                putExtra("APROVADAS", aprovadas)
                putExtra("PERCENTUAL", percentual)
                putExtra("SITUACAO", situacao)

            }

            startActivity(intent)

        }
    }
}