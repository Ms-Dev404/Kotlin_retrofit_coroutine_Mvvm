package com.business.mykotlinapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.annotation.GlideModule
import com.business.mykotlinapp.R
import com.business.mykotlinapp.model.ListItems

@GlideModule
class ListItemAdapter(var Items:List<ListItems>, ctx:Context): RecyclerView.Adapter<ListItemAdapter.ViewHolders>() {
    lateinit var data: ListItems
    var inflater: LayoutInflater = LayoutInflater.from(ctx)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolders {

        return ViewHolders(inflater.inflate(R.layout.rv_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolders, position: Int) {

        data = Items[position]

        holder.tvtitle.text = data.title

       holder.tvdesc.text=data.description

    }

    override fun getItemCount(): Int {
        return Items.size
    }

    class ViewHolders(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvtitle: TextView = itemView.findViewById(R.id.tv_title)
        var tvdesc: TextView = itemView.findViewById(R.id.tv_dsc)

    }
}

