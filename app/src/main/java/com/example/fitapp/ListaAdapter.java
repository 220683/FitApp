package com.example.fitapp

import  android.view.LayoutInflater
import  android.view.View
import  android.view.ViewGroup
import  android.widget.BaseAdapter

class  ListaAdapter ( private  val  items :  List < Atividade >): BaseAdapter (){

        override divertido getView(posição:Int,convertView:Ver?,pai:ViewGroup?):Ver{

        val item=itens[posição]
        val inflater=LayoutInflater.de(pai?.context)
        val view=inflater.inflate(R.layout.item_lista,pai,falso)

        view.tipo_atividade.text=item.tipo
        view.titulo_atividade.text=item.titulo
        view.desc_atividade.text=item.descricao
        view.data_atividade.text=item.dados

        vista de retorno
        }

        substituir getItem divertido(posição:Int):Qualquer{
        itens de retorno[posição]
        }

        substituir getItemId divertido(position:Int):Long{
        retornar itens[posição].id.toLong()
        }

        override fun getCount():Int{
        return itens.size
        }
        }