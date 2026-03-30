package com.bytesw.prueba.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bytesw.prueba.R
import com.bytesw.prueba.models.Donut

class DetailFragment : Fragment() {

    private lateinit var tvPlaceholder: TextView
    private lateinit var layoutDetalle: LinearLayout
    private lateinit var tvNombre: TextView
    private lateinit var tvTipo: TextView
    private lateinit var tvId: TextView
    private lateinit var tvPpu: TextView
    private lateinit var tvBatters: TextView
    private lateinit var tvToppings: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvPlaceholder = view.findViewById(R.id.tvPlaceholder)
        layoutDetalle = view.findViewById(R.id.layoutDetalle)
        tvNombre      = view.findViewById(R.id.tvNombre)
        tvTipo        = view.findViewById(R.id.tvTipo)
        tvId          = view.findViewById(R.id.tvId)
        tvPpu         = view.findViewById(R.id.tvPpu)
        tvBatters     = view.findViewById(R.id.tvBatters)
        tvToppings    = view.findViewById(R.id.tvToppings)
    }

    fun mostrarDetalle(donut: Donut) {
        tvPlaceholder.visibility = View.GONE
        layoutDetalle.visibility = View.VISIBLE

        tvNombre.text = donut.name
        tvTipo.text   = "Tipo: ${donut.type}"
        tvId.text     = "ID: ${donut.id}"
        tvPpu.text    = "Precio por unidad: ${"$%.2f".format(donut.ppu)}"

        // Batters
        val battersText = donut.batters?.batter
            ?.joinToString("\n") { "• ${it.type} (ID: ${it.id})" }
            ?: "Sin batters"
        tvBatters.text = battersText

        // Toppings
        val toppingsText = donut.topping
            ?.joinToString("\n") { "• ${it.type} (ID: ${it.id})" }
            ?: "Sin toppings"
        tvToppings.text = toppingsText
    }
}
