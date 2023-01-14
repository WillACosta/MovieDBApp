package com.will.moviedbapp.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import com.will.moviedbapp.R
import com.will.moviedbapp.databinding.UserNameUpdateFormBinding
import com.will.moviedbapp.ui.viewmodels.PreferencesViewModel
import com.will.moviedbapp.ui.viewmodels.NameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateNameFragment : DialogFragment() {

    private val binding: UserNameUpdateFormBinding by lazy {
        UserNameUpdateFormBinding.inflate(layoutInflater)
    }

    private val nameViewModel: NameViewModel by viewModel()
    private val preferencesViewModel: PreferencesViewModel by viewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setListeners()

        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setView(binding.root).setTitle("Update name")
                .setPositiveButton(
                    R.string.save,
                    DialogInterface.OnClickListener { _, _ ->
                        submitUserName()
                    }
                ).setNegativeButton(
                    R.string.cancel,
                    DialogInterface.OnClickListener { dialog, _ ->
                        dialog.cancel()
                    }
                )
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        const val TAG = "UpdateNameFragmentDialog"
    }

    private fun setListeners() {
        binding.edtName.doOnTextChanged { value, _, _, _ ->
            nameViewModel.onNameChanged(value.toString())
        }

        nameViewModel.error.observe(this) { error ->
            binding.edtContainer.error = error
        }

        preferencesViewModel.userData.observe(this) {
            binding.edtName.setText(it.name)
        }
    }

    private fun submitUserName() {
        val validName = binding.edtContainer.error == null

        if (validName) {
            nameViewModel.submitName()
            dialog?.cancel()
        }
    }
}
