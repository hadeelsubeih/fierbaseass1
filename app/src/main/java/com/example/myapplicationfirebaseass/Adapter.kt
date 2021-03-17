package com.example.myapplicationfirebaseass

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main2.view.*

class Adapter(val context: Context, val list: List<ListData>, val click: MainActivity) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val name = item.nameId
        val number = item.NumberId
        val address = item.AddressId
        val btnDelete = item.btnDelete


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflate = LayoutInflater.from(context).inflate(R.layout.activity_main2, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name?.text = list[position].name
        holder.number?.text = list[position].number
        holder.address?.text = list[position].address
        holder.btnDelete.setOnClickListener {
            click.onClick(list[position].id  , position)

        }

    }


    interface onClick {
        fun onClick(id: Int, position: Int)

    }

}