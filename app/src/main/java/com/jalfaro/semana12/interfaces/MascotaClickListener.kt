package com.jalfaro.semana12.interfaces

import com.jalfaro.semana12.database.entities.Mascota

interface MascotaClickListener {
    fun onClick(mascota: Mascota)
}