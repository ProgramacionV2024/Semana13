package com.jalfaro.semana12.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jalfaro.semana12.database.entities.Mascota

@Dao
interface MascotaDao {
    @Query("Select * from mascota")
    suspend fun getAllMascota(): List<Mascota>

    @Insert
    suspend fun insertMascota(mascota: Mascota)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMascota(mascota: Mascota)

    @Delete
    suspend fun deleteMascota(mascota: Mascota)
}