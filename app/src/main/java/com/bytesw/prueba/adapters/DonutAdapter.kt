package com.bytesw.prueba.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bytesw.prueba.R
import com.bytesw.prueba.models.Donut

class DonutAdapter(
    private val donuts: List<Donut>,
    private val onClick: (Donut) -> Unit
) : RecyclerView.Adapter<DonutAdapter.DonutViewHolder>() {

    inner class DonutViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvDonutNombre)
        val tvTipo: TextView   = view.findViewById(R.id.tvDonutTipo)
        val tvPpu: TextView    = view.findViewById(R.id.tvDonutPpu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonutViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_donut, parent, false)
        return DonutViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonutViewHolder, position: Int) {
        val donut = donuts[position]
        holder.tvNombre.text = donut.name
        holder.tvTipo.text   = donut.type
        holder.tvPpu.text    = "Q%.2f".format(donut.ppu)
        holder.itemView.setOnClickListener { onClick(donut) }
    }

    override fun getItemCount() = donuts.size
}
