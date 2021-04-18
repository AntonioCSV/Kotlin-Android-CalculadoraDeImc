package me.acsv.calculadoradeimc

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import me.acsv.calculadoradeimc.databinding.ActivityMainBinding
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btCalcular = binding.btCalcular
        val txtMensagem = binding.mensagem

        btCalcular.setOnClickListener {
            val pesoString = binding.editPeso.text.toString()
            val alturaString = binding.editAltura.text.toString()

            if (pesoString.isNullOrEmpty()) {
                txtMensagem.text = "Informe o seu Peso"
            } else if (alturaString.isNullOrEmpty()) {
                txtMensagem.text = "Informe a sua Altura"
            } else {
                calcularImc()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.reset -> {
                binding.mensagem.text = ""
                binding.editAltura.setText("")
                binding.editPeso.setText("")
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun calcularImc() {
        val peso = binding.editPeso.text.toString().toIntOrNull()
        val altura = binding.editAltura.text.toString().toFloatOrNull()

        if(peso != null && altura != null) {
            val imc = peso.div(altura.pow(2))

             val classificacao= when {
                imc <= 18.5 -> "Peso Baixo"
                imc <= 24.9 -> "Peso Normal"
                imc <= 29.9 -> "Sobrepeso"
                imc <= 34.9 -> "Obesidade (Grau 1)"
                imc <= 39.9 -> "Obesidade (Grau 2)"
                else -> "Obesidade Mórbida (Grau 3)"
            }
            binding.mensagem.text = "IMC: $imc\n$classificacao"
        }
    }
}