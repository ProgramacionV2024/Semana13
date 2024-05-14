package com.jalfaro.semana12.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jalfaro.semana12.MyApplication
import com.jalfaro.semana12.adapter.MascotaAdapter
import com.jalfaro.semana12.database.entities.Mascota
import kotlinx.coroutines.launch

class SelectMascotaViewModel(val app: Application): AndroidViewModel(app) {
    private val _mascotas: MutableLiveData<List<Mascota>> = MutableLiveData(emptyList())
    val mascotas: LiveData<List<Mascota>> = _mascotas

    fun loadMascotas() {
        viewModelScope.launch {
            val arrayData = (app as MyApplication).database.getMascotaDao().getAllMascota()
            _mascotas.postValue(arrayData)
        }
    }

    fun deleteMascota(mascota: Mascota, adapter: MascotaAdapter) {
        adapter.deleteItem(mascota)
        viewModelScope.launch {
            (app as MyApplication).database.getMascotaDao().deleteMascota(mascota)
        }
    }

    fun updateMascota(mascota: Mascota, adapter: MascotaAdapter) {
        adapter.updateItem(mascota)
        viewModelScope.launch {
            (app as MyApplication).database.getMascotaDao().updateMascota(mascota)
        }
    }
}