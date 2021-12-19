package com.example.sharedpreferences

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sharedpreferences.Constants.ARQUIVO_PREFERENCIA
import com.example.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var nome: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {

            //Passando o nome do arquivo que irá armazenar os textos e o modo ( 0 - privado)
            preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0)

            editor = preferences.edit()

            if (binding.editName.text.toString() == "") {
                Toast.makeText(
                    applicationContext,
                    "Preencha o nome",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                nome = binding.editName.text.toString()
                editor.putString("nome", nome)
                editor.commit()
                binding.txtResultado.text = getString(R.string.ola) + " $nome"
            }
        }
        //Recuperar dados salvos
        preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0)

        //Valida se temos o nome em preferencias
        if (preferences.contains("nome")) {
            nome = preferences.getString("nome", "usuário não definido").toString()
            binding.txtResultado.text = getString(R.string.ola) + " $nome"
        } else {
            binding.txtResultado.text = getString(R.string.ola_sem_nome)

        }
    }
}