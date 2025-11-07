package com.example.aulasqlite.adapter

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.aulasqlite.R
import com.example.aulasqlite.entity.Cadastro

class MeuAdapter(var context: Context, var cursor: Cursor): BaseAdapter() {
    override fun getCount(): Int {
        return cursor.count
    }

    override fun getItem(position: Int): Any {
        cursor.moveToPosition(position)
        val cadastro = Cadastro(
            cursor.getInt(cursor.getColumnIndex("_id").toInt()).toInt(),
            cursor.getInt(cursor.getColumnIndex("nome").toInt()).toString(),
            cursor.getInt(cursor.getColumnIndex("telefone").toInt()).toString()
        )
        return cadastro
    }

    override fun getItemId(position: Int): Long {
        cursor.moveToPosition(position)
        return cursor.getInt(0).toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                as LayoutInflater
        val v = inflater.inflate(R.layout.elemento_lista, null)
        val tvNomeElementoLista = v.findViewById<TextView>(R.id.tvNomeElemento_lista)
        val tvTelefoneElementoLista = v.findViewById<TextView>(R.id.tvTelefoneElemento_lista)

        cursor.moveToPosition(position)
        tvNomeElementoLista.text = cursor.getString(
            cursor.getColumnIndex("nome").toInt()
        ).toString()

        tvTelefoneElementoLista.text = cursor.getString(
            cursor.getColumnIndex("telefone").toInt()
        ).toString()

        if(position % 2 == 0){
            v.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        }else{
            v.setBackgroundColor(ContextCompat.getColor(context, R.color.light_gray))
        }

        return v
    }
}