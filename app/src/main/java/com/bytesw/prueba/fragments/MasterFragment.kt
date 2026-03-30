package com.bytesw.prueba.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bytesw.prueba.R
import com.bytesw.prueba.adapters.DonutAdapter
import com.bytesw.prueba.models.Donut
import com.bytesw.prueba.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MasterFragment : Fragment() {

    interface OnDonutSelectedListener {
        fun onDonutSelected(donut: Donut)
    }

    private var listener: OnDonutSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnDonutSelectedListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_master, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerDonuts)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        // Llamada asíncrona a la API con Retrofit
        RetrofitClient.apiService.getDonuts().enqueue(object : Callback<List<Donut>> {
            override fun onResponse(call: Call<List<Donut>>, response: Response<List<Donut>>) {
                if (response.isSuccessful) {
                    val donuts = response.body() ?: emptyList()
                    recycler.adapter = DonutAdapter(donuts) { donut ->
                        listener?.onDonutSelected(donut)
                    }
                } else {
                    Toast.makeText(context, getString(R.string.error_api), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Donut>>, t: Throwable) {
                Toast.makeText(context, "${getString(R.string.error_api)}: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
