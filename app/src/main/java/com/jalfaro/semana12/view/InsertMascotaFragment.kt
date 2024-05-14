package com.jalfaro.semana12.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jalfaro.semana12.R
import com.jalfaro.semana12.database.entities.Mascota
import com.jalfaro.semana12.databinding.FragmentInsertMascotaBinding
import com.jalfaro.semana12.viewModel.InsertMascotaViewModel


class InsertMascotaFragment : Fragment() {
    lateinit var binding: FragmentInsertMascotaBinding
    lateinit var viewModel: InsertMascotaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInsertMascotaBinding.inflate(inflater, container, false)
        binding.btnGrabar.setOnClickListener {
            val mascota = Mascota(
                nombre = binding.nombre.text.toString(),
                raza = binding.raza.text.toString(),
                dueno = binding.dueno.text.toString()
            )
            binding.raza.text.clear()
            binding.dueno.text.clear()
            binding.nombre.text.clear()
            viewModel.grabar(mascota)
        }
        binding.btnListado.setOnClickListener {
            findNavController().navigate(R.id.action_insertMascotaFragment_to_selectMascotaFragment)
        }
        viewModel = ViewModelProvider(this).get(InsertMascotaViewModel::class.java)
        return binding.root
    }

}