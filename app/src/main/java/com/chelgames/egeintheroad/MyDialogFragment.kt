package com.chelgames.egeintheroad

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MyDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Вы уверены что хотите закончить попытку?")
                .setMessage("После завершения попытки, изменить варианты ответа будет невозможно.")
                .setCancelable(true)
                .setPositiveButton("Да") { dialog, id ->
                    (activity as Work?)?.finishTry()
                }
                .setNegativeButton("Нет", { dialog, id -> })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}