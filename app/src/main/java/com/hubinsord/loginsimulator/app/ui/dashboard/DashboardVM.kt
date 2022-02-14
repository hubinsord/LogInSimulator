package com.hubinsord.loginsimulator.app.ui.dashboard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hubinsord.loginsimulator.core.data.repository.AccountRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
    private val repository: AccountRepositoryImpl,
    private val state: SavedStateHandle
) : ViewModel() {

    val userId = state.get<Int>("id")
    val account = userId?.let { repository.getAccountById(it) }
}