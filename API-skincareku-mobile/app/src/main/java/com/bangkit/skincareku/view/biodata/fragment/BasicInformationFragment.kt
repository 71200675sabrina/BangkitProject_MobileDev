package com.bangkit.skincareku.view.biodata.fragment

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import com.bangkit.skincareku.R
import com.bangkit.skincareku.databinding.FragmentBasicInformationBinding
import com.bangkit.skincareku.networking.data.DataManager
import com.bangkit.skincareku.view.biodata.ProgressBarListener
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class BasicInformationFragment : Fragment() {

    private var _binding: FragmentBasicInformationBinding? = null
    private val binding get() = _binding!!
    private var progressBarListener: ProgressBarListener? = null
    private val calendar: Calendar = Calendar.getInstance()

    val dataManager: DataManager by lazy {
        DataManager(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBasicInformationBinding.inflate(inflater, container, false)

        progressBarListener?.updateProgressBar(30)

        val skinHealthInformationFragment = SkinHealthInformationFragment()
        val fragmentManager = requireActivity().supportFragmentManager

        val birthdate = binding.etBirthDate
        val birthdateInputLayout = binding.tilBirthDate

        val genderInputLayout = binding.tilGender
        val gender = binding.etGender
        val values = listOf("Male", "Female")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, values)
        gender.setAdapter(adapter)

        gender.setOnItemClickListener { parent, view, position, id ->
            val selectedValue = parent.getItemAtPosition(position) as String
            dataManager.saveGender(selectedValue)
        }

        birthdate.setOnClickListener() {
            showDatePickerDialog()
        }


        binding.btnNext.setOnClickListener {
            val birthdateString = binding.etBirthDate.text.toString()
            val genderString = binding.etGender.text.toString()
            if(birthdateString.isEmpty()) {
                birthdateInputLayout.error = getString(R.string.birthdate_error)
            }
            if(genderString.isEmpty()) {
                genderInputLayout.error = getString(R.string.gender_error)
            }
            if(birthdateString.isNotEmpty() && genderString.isNotEmpty()){
                birthdateInputLayout.error = null
                genderInputLayout.error = null
                fragmentManager.beginTransaction()
                    .replace(R.id.fl_biodata, skinHealthInformationFragment)
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
                    .addToBackStack(null)
                    .commit()
            }

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        progressBarListener = context as? ProgressBarListener
        if(progressBarListener == null) {
            throw ClassCastException("$context must implement ProgressBarListener")
        }
    }

    private fun showDatePickerDialog() {
        val dateButton = binding.etBirthDate as EditText
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireActivity(),
            { _, selectedYear, selectedMonth, selectedDay ->
                // Update the calendar with the selected date
                calendar.set(selectedYear, selectedMonth, selectedDay)

                // Format the date as desired
                val formattedDate = formatDate(calendar.time)

                // Update the button text or perform any other action
                dateButton.setText(formattedDate)
                dataManager.saveBirthdate(formattedDate)
            }, year, month, day)

        // Show the date picker dialog
        datePickerDialog.show()
    }

    private fun formatDate(date: Date): String {
        val format = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
        return format.format(date)
    }
}
