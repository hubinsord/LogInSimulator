package com.hubinsord.loginsimulator.app.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hubinsord.loginsimulator.R
import com.hubinsord.loginsimulator.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private val viewModel: DashboardVM by viewModels()

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        initViews()
        initObservers()
    }

    private fun initViews() {
        viewModel.onUserLoggedIn()
        binding.btnLogOut.setOnClickListener {
            viewModel.onButtonLogOutClicked()
        }
    }

    private fun initObservers() {
        viewModel.event.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                handleEvent(it)
            }
        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun handleEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.LoggedInFirstTime -> {
                binding.tvLoginInfo.text = getString(R.string.fragment_dashboard_first_login)
            }
            is DashboardEvent.LoggedIn -> {
                binding.tvLoginInfo.text = getString(R.string.dashboard_tv_logging_info, event.date)
            }
            is DashboardEvent.Logout -> {
                val action = DashboardFragmentDirections.actionDashboardFragmentToLogInFragment()
                findNavController().navigate(action)
            }
        }
    }
}