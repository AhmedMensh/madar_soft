package com.example.madarsofttask.ui.new_user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.madarsofttask.R
import com.example.madarsofttask.databinding.FragmentNewUserBinding
import com.example.madarsofttask.domain.models.Errors
import com.example.madarsofttask.domain.models.UserModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewUserFragment : Fragment() {

    private lateinit var binding: FragmentNewUserBinding
    private val viewModel: NewUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewUserBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGenderMenu()
        initViewsClick()
        collectUIState()
    }

    private fun initViewsClick() {
        binding.btnAdd.setOnClickListener {
            viewModel.addNewUser(
                UserModel(
                    name = binding.etName.text.toString(),
                    age = binding.etAge.text.toString(),
                    jobTitle = binding.etJobTitle.text.toString(),
                    gender = viewModel.gender.orEmpty()
                )
            )
        }

        binding.btnUsers.setOnClickListener {
            findNavController().navigate(R.id.usersFragment)
        }
    }

    private fun collectUIState() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                NewUserUIState.Ideal -> {}
                is NewUserUIState.Error -> {
                    when (it.error) {
                        Errors.EMPTY_INPUT -> {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.please_enter_all_data), Toast.LENGTH_SHORT
                            ).show()
                        }

                        Errors.INVALID_AGE -> {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.invalid_age), Toast.LENGTH_SHORT
                            ).show()

                        }
                        Errors.GENERAL_ERROR -> {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.something_went_wrong),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {}
                    }
                }

                is NewUserUIState.Loading -> binding.progressBar.isVisible = it.isLoading
                is NewUserUIState.Success -> {
                    resetUserForm()
                    Toast.makeText(requireContext(),
                        getString(R.string.user_add_successfully), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun resetUserForm(){
        binding.etName.text?.clear()
        binding.etAge.text?.clear()
        binding.etJobTitle.text?.clear()
        binding.tvGender.text?.clear()
        viewModel.gender = null
    }
    private fun initGenderMenu() {
        val genders = resources.getStringArray(R.array.genders)
        val gendersAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, genders)

        gendersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.tvGender.setAdapter(gendersAdapter)
        binding.tvGender.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                viewModel.gender = genders[position]
            }

    }
}