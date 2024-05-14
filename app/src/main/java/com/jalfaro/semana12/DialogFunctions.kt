package com.jalfaro.semana12

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.Editable
import android.view.LayoutInflater
import com.jalfaro.semana12.database.entities.Mascota
import com.jalfaro.semana12.databinding.EditDialogLayoutBinding

fun actualizarMascotaDialog(context: Context,
                            mascota: Mascota,
                            editar: (Mascota) -> Unit,
                            borrar: (Mascota) -> Unit
) {
    val dialogo = AlertDialog.Builder(context)
    dialogo.setTitle(context.resources.getString(R.string.modificar_mascota_titulo))
    dialogo.setMessage("Que accion desea hacer con la mascota ${mascota.nombre}?")
    dialogo.setPositiveButton("EDITAR") { d, _ ->
        editar(mascota)
    }
    dialogo.setNegativeButton("BORRAR") { d, _ ->
        borrar(mascota)
    }
    dialogo.setNeutralButton("CANCELAR") {d, _ ->
        d.dismiss()
    }
    dialogo.show()
}

fun editaMascotaDialog(context: Context,
                       mascota: Mascota,
                       update: (Mascota) -> Unit ) {
    val dialogBinding = EditDialogLayoutBinding.inflate(LayoutInflater.from(context))
    val dialog = AlertDialog.Builder(context)
    dialogBinding.nombre.setText(mascota.nombre)
    dialogBinding.dueno.setText(mascota.dueno)
    dialogBinding.raza.setText(mascota.raza)
    dialog.setTitle("Modificar Mascota")
    dialog.setView(dialogBinding.root)
    dialog.setPositiveButton("CAMBIAR") {  d, _ ->
        val pet = Mascota(id= mascota.id,
                          nombre = dialogBinding.nombre.text.toString(),
                          dueno = dialogBinding.dueno.text.toString(),
                          raza = dialogBinding.raza.text.toString()
        )
        update(pet)
    }
    dialog.setNegativeButton("CANCELAR") { d, _ ->
        d.dismiss()
    }
    dialog.show()
}