package com.example.fitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Programação do botão
        btnAdd.setOnClickListener {
            //Ao clicar no botão vai para a tela AddActivity
            startActivity(Intent(this, AddActivity::class.java))
        }

        //Programar o clique na lista , se clicar em um item especifico na lista

        lstAtividades.setOnItemClickListener { parent, view, position, id ->

            //Pegar o item na posição que foi clicada
            val atividadeClicada = lstAtividades.getItemAtPosition(position) as Atividade

            //Mandando para uma outra tela
            val i = Intent(this, DadosActivity::class.java)

            i.putExtra("atividade", atividadeClicada)

            startActivity(i)

        }

        //Filtro

        var tipos = resources.getStringArray(R.array.tipos)

        var tp = arrayListOf<String>()

        tp.addAll(tipos)
        tp.add("todos")

        val adp = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tp)
        spnFiltro.adapter = adp

        //Programar filtro
        spnFiltro.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View,position: Int, id: Long) {

                filtrarlista(spnFiltro.selectedItem.toString())

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

        }
    }


    }

    //Metodos para colocar o Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Prograr a ação quando clicar no menu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menuNormal) {
            carregarLista("normal")
        }

        if (item.itemId == R.id.menuData) {
            carregarLista("data"    )
        }


        return super.onOptionsItemSelected(item)
    }

    //Preencher lista  - criar um metodo
    override fun onResume(){
        super.onResume()

        carregarLista("normal")
    }


    //programar filtro
    private fun filtrarlista(tipo:String) {
        val atividadeDAO = FitAppDatabase.getInstance(this)?.atividadeDao()
        val listaAtividades: List<Atividade>

        if (tipo.equals("Todos")){
            listaAtividades = atividadeDAO!!.listar()
        }else {
            listaAtividades = atividadeDAO!!.listarporTipo(tipo)
        }

        val adp = ArrayAdapter<Atividade>(this, android.R.layout.simple_list_item_1, listaAtividades)

        lstAtividades.adapter = adp
    }

    private fun carregarLista(ordem: String) {
        val atividadeDAO = FitAppDatabase.getInstance(this)?.atividadeDao()
        val listaAtividades: List<Atividade>

        if (ordem.equals("normal")) {
            listaAtividades = atividadeDAO!!.listar()
        } else if(ordem.equals("data")) {
            listaAtividades = atividadeDAO!!.listarData()
        } else {
            listaAtividades = atividadeDAO!!.listar()
        }

        val adp = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaAtividades)

        lstAtividades.adapter = adp
    }
}