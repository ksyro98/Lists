package com.example.lists.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lists.R
import com.example.lists.data.ItemsList
import com.example.lists.toast
import javax.inject.Inject

class NamesAdapter @Inject constructor() : RecyclerView.Adapter<NamesAdapter.NamesViewHolder>(){

    var names: List<ItemsList> = emptyList()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }
    var onClick: (ItemsList) -> Unit = {}
    var onLongClick: (ItemsList) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.name_item, parent, false)

        return NamesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
        holder.nameTextView.text = names[position].name
        holder.nameTextView.setOnClickListener {
            onClick(names[position])
        }
        holder.nameTextView.setOnLongClickListener {
            onLongClick(names[position])
            true
        }
    }

    override fun getItemCount(): Int = names.size


    class NamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    }

}
