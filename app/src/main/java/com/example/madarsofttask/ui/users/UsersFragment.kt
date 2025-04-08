package com.example.madarsofttask.ui.users

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.madarsofttask.R
import com.example.madarsofttask.databinding.FragmentUsersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private val viewModel: UsersViewModel by viewModels()
    private val userAdapter by lazy { UserAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectUIState()
    }

    private fun collectUIState() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is UsersUIState.Error -> {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                is UsersUIState.Loading -> binding.progressBar.isVisible = it.isLoading
                is UsersUIState.Success -> {
                    if (it.users.isEmpty()) {
                        binding.noUsers.isVisible = true
                    } else {
                        binding.rvUsers.adapter = userAdapter
                        userAdapter.submitList(it.users)
                    }
                }
            }
        }
    }

}