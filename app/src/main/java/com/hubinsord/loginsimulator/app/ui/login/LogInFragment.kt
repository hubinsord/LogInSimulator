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
        initViews()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        viewModel.onDefaultValidation()
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
        viewModel.validationState.observe(viewLifecycleOwner){
            when(it){
                is CredentialInputValidator.UserNameEmpty -> {
                    binding.tvUserNameValidation.text = resources.getString(R.string.fragment_log_in_empty_user_name)
                }
                is CredentialInputValidator.UserNameTooShort -> {
                    binding.tvUserNameValidation.text = resources.getString(R.string.fragment_log_in_short_user_name)
                }
                is CredentialInputValidator.UserNameTooLittleLetters -> {
                    binding.tvUserNameValidation.text = resources.getString(R.string.fragment_log_in_too_little_letters)
                }
                is CredentialInputValidator.UserNameNotExisting -> {
                    binding.tvUserNameValidation.text = resources.getString(R.string.fragment_log_in_user_doesnt_exist)
                }
                is CredentialInputValidator.PasswordIncorrect -> {
                    binding.tvPasswordValidation.text = getString(R.string.fragment_log_in_incorrect_password)
                }
                is CredentialInputValidator.UserNameDefault -> {
                    binding.tvUserNameValidation.text = resources.getString(R.string.fragment_log_in_default_validation)
                }
                is CredentialInputValidator.PasswordDefault -> {
                    binding.tvPasswordValidation.text = resources.getString(R.string.fragment_log_in_default_validation)
                }
            }
        }
    }

    private fun handleEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.NavigateToDashboard -> {
                val action = LogInFragmentDirections.actionLogInFragmentToDashboardFragment(event.id)
                findNavController().navigate(action)
            }
        }
    }
}