package com.bangkit.skincareku.view.biodata.fragment

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.bangkit.skincareku.R
import com.bangkit.skincareku.databinding.FragmentAllergyInformationBinding
import com.bangkit.skincareku.networking.data.DataManager
import com.bangkit.skincareku.view.biodata.BiodataViewModel
import com.bangkit.skincareku.view.biodata.ProgressBarListener
import com.bangkit.skincareku.view.main.MainActivity

class AllergyInformationFragment : Fragment() {

    private var _binding: FragmentAllergyInformationBinding? = null
    private val binding get() = _binding!!
    private var progressBarListener: ProgressBarListener? = null
    private val allergies = mutableListOf<String>()
    private lateinit var biodataViewModel: BiodataViewModel
    private lateinit var progressDialog: ProgressDialog

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
        _binding = FragmentAllergyInformationBinding.inflate(inflater, container, false)

        biodataViewModel = BiodataViewModel()

        progressBarListener?.updateProgressBar(80)

        setupViewModel()

        binding.btnSubmit.setOnClickListener{
            if(allergies.size > 0) {
                saveData()
                val email = dataManager.getEmail().toString()
                val gender = dataManager.getGender().toString()
                val skinProblem = dataManager.getSkinProblem().toString()
                val allergy = dataManager.getAllergy().toString()
                val birthdate = dataManager.getBirthdate().toString()

                biodataViewModel.updateUser(email, gender, skinProblem, allergy, birthdate)
            }else {
                binding.tvSelectProblem.visibility = View.VISIBLE
            }
        }

        setupSkinProblem()

        return binding.root
    }

    private fun setupViewModel() {

        biodataViewModel.isSuccessful.observe(requireActivity(), {
            if(it){
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        })
        biodataViewModel.isConnectionError.observe(requireActivity(), {
            if(it){
                AlertDialog.Builder(requireContext()).apply {
                    setTitle(getString(R.string.no_internet))
                    setMessage(getString(R.string.connection_error))
                    setPositiveButton(getString(R.string.retry)) { _, _ ->
                        val email = dataManager.getEmail().toString()
                        val gender = dataManager.getGender().toString()
                        val skinProblem = dataManager.getSkinProblem().toString()
                        val allergy = dataManager.getAllergy().toString()
                        val birthdate = dataManager.getBirthdate().toString()

                        biodataViewModel.updateUser(email, gender, skinProblem, allergy, birthdate)
                    }
                    create()
                    show()
                }
            }
        })

        biodataViewModel.isLoading.observe(requireActivity(), {
            if (it) {
                progressDialog = ProgressDialog(requireContext())
                progressDialog.show()
                progressDialog.setContentView(R.layout.item_progress_dialog)
                progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            }else {
                progressDialog.dismiss()
            }
        })
    }

    private fun setupSkinProblem () {
        val acids = binding.btnAcids
        acids.setOnClickListener{
            if(allergies.contains("Acids")) {
                allergies.remove("Acids")
                acids.setTextColor(resources.getColor(R.color.grey))
                acids.setBackgroundResource(R.drawable.button_outline)
            } else {
                allergies.add("Acids")
                acids.setTextColor(resources.getColor(R.color.blue))
                acids.setBackgroundResource(R.drawable.button_outline_selected)
            }
        }

        val essentialOils = binding.btnEssentialOils
        essentialOils.setOnClickListener{
            if(allergies.contains("Essential Oils")) {
                allergies.remove("Essential Oils")
                essentialOils.setTextColor(resources.getColor(R.color.grey))
                essentialOils.setBackgroundResource(R.drawable.button_outline)
            } else {
                allergies.add("Essential Oils")
                essentialOils.setTextColor(resources.getColor(R.color.blue))
                essentialOils.setBackgroundResource(R.drawable.button_outline_selected)
            }
        }

        val sulfates = binding.btnSulfates
        sulfates.setOnClickListener{
            if(allergies.contains("Sulfates")) {
                allergies.remove("Sulfates")
                sulfates.setTextColor(resources.getColor(R.color.grey))
                sulfates.setBackgroundResource(R.drawable.button_outline)
            } else {
                allergies.add("Oily Skin")
                sulfates.setTextColor(resources.getColor(R.color.blue))
                sulfates.setBackgroundResource(R.drawable.button_outline_selected)
            }
        }
    }

    private fun saveData() {
        var allergyString = allergies.joinToString(", ")
        dataManager.saveAllergy(allergyString)
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

}