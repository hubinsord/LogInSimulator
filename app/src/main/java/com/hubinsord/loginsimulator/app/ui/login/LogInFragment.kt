package com.hubinsord.loginsimulator.app.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hubinsord.loginsimulator.R
import com.hubinsord.loginsimulator.databinding.FragmentLogInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : Fragment(R.layout.fragment_log_in) {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LogInVM by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        initListeners()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListeners() {
        binding.btnLogIn.setOnClickListener {
            viewModel.onBtnLogInClicked()
        }
    }

    private fun initObservers() {
        viewModel.event.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                handleEvent(it)
            }
        }
    }

    private fun handleEvent(event: LogInVM.LogInEvent) {
        when (event) {
            is LogInVM.LogInEvent.NavigateToDashboard -> {
                val action = LogInFragmentDirections.actionLogInFragmentToDashboardFragment(event.id)
                findNavController().navigate(action)
            }
            is LogInVM.LogInEvent.UserNameEmpty -> {
                binding.tvUserNameValidation.text = resources.getString(R.string.fragment_log_in_empty_user_name)
            }
            is LogInVM.LogInEvent.UserNameTooShort -> {
                binding.tvUserNameValidation.text = resources.getString(R.string.fragment_log_in_short_user_name)
            }
            is LogInVM.LogInEvent.UserNameTooLittleLetters -> {
                binding.tvUserNameValidation.text = resources.getString(R.string.fragment_log_in_too_little_letters)
            }
            is LogInVM.LogInEvent.UserNameNotExisting -> {
                binding.tvUserNameValidation.text = resources.getString(R.string.fragment_log_in_user_doesnt_exist)
            }
            is LogInVM.LogInEvent.PasswordIncorrect -> {
                binding.tvPasswordValidation.text = getString(R.string.fragment_log_in_incorrect_password)
            }
        }
    }
}