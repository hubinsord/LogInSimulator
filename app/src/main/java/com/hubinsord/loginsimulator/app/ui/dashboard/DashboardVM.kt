package com.hubinsord.loginsimulator.app.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hubinsord.loginsimulator.app.data.repository.AccountRepositoryImpl
import com.hubinsord.loginsimulator.core.wrapper.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
    private val repository: AccountRepositoryImpl,
    private val state: SavedStateHandle
) : ViewModel() {

    private val _event = MutableLiveData<Event<DashboardEvent>>()
    val event: LiveData<Event<DashboardEvent>> get() = _event

    private val userId = state.get<Int>("id")

    var account = userId?.let { repository.getAccountById(it) }

    private fun updateAccount() {
        account?.lastLoginDate = System.currentTimeMillis()
        account?.let { repository.updateAccount(userId!!, it) }
    }

    fun onButtonLogOutClicked() {
        updateAccount()
        _event.postValue(Event(DashboardEvent.Logout))
    }

    fun onUserLoggedIn() {
        account?.let {
            if (it.lastLoginDate == null) {
                _event.postValue(Event(DashboardEvent.LoggedInFirstTime))
            } else
                _event.postValue(Event(DashboardEvent.LoggedIn(it.lastLoginDateFormatted ?: "")))
        }

    }
}
