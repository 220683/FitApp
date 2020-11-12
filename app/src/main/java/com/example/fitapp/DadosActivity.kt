package com.example.fitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_dados.*

class DadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados)

        //Buscar os dados clicados , no caso atividade que vem da outra tela

        val atividade = intent.getSerializableExtra("atividade") as Atividade

        txtTitulo.text = atividade.titulo
        txtDescricao.text = atividade.descricao
        txtData.text = atividade.data
        txtTempo.text = atividade.tempo
        txtTipo.text = atividade.tipo
        txtDistancia.text = atividade.distancia.toString()

        //Não mostrar a academia
         if (atividade.tipo.equals("Academia")){
            txtDistancia.visibility = View.INVISIBLE
        }

    }
}