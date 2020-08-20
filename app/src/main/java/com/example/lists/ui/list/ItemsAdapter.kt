package com.example.lists.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lists.R
import com.example.lists.data.Item
import javax.inject.Inject

class ItemsAdapter @Inject constructor() : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>(){

    var items = emptyList<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClick: (Item) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.listItemTextView.text = items[position].itemName
        holder.listItemTextView.setOnClickListener {
            onClick(items[position])
        }
    }

    override fun getItemCount() = items.size


    class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val listItemTextView: TextView = itemView.findViewById(R.id.listItemTextView)
    }

}