package com.jalfaro.semana12.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jalfaro.semana12.actualizarMascotaDialog
import com.jalfaro.semana12.adapter.MascotaAdapter
import com.jalfaro.semana12.database.entities.Mascota
import com.jalfaro.semana12.databinding.FragmentSelectMascotaBinding
import com.jalfaro.semana12.editaMascotaDialog
import com.jalfaro.semana12.interfaces.MascotaClickListener
import com.jalfaro.semana12.viewModel.SelectMascotaViewModel


class SelectMascotaFragment : Fragment(), MascotaClickListener {
    lateinit var binding : FragmentSelectMascotaBinding
    lateinit var viewModel: SelectMascotaViewModel
    val adapter = MascotaAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectMascotaBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SelectMascotaViewModel::class.java)
        binding.rvMascotas.adapter = adapter
        binding.rvMascotas.layoutManager = LinearLayoutManager(context)
        viewModel.mascotas.observe(viewLifecycleOwner) { newList ->
            Log.d("TEST", "${newList.size}")
            adapter.loadNewItems(newList)
        }
        viewModel.loadMascotas()
        return binding.root
    }

    override fun onClick(mascota: Mascota) {
        context?.let { ctx ->
            actualizarMascotaDialog(ctx,
                mascota,
                { mascota ->
                    editaMascotaDialog(ctx, mascota) { pet ->
                        viewModel.updateMascota(pet, adapter)
                    }
                },
                { mascota ->
                    viewModel.deleteMascota(mascota, adapter)
                })
        }
    }
}