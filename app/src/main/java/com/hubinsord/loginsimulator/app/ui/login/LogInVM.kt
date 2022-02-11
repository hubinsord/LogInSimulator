package com.hubinsord.loginsimulator.app.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hubinsord.loginsimulator.core.data.model.Account
import com.hubinsord.loginsimulator.core.domain.AccountRepository
import com.hubinsord.loginsimulator.core.wrapper.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogInVM @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    sealed class LogInEvent {
        object NavigateToDashboard : LogInEvent()
        object UserNameEmpty : LogInEvent()
        object UserNameTooShort : LogInEvent()
        object UserNameTooLittleLetters : LogInEvent()
        object UserNameNotExisting : LogInEvent()
        object PasswordIncorrect : LogInEvent()
    }

    private val _event = MutableLiveData<Event<LogInEvent>>()
    val event: LiveData<Event<LogInEvent>> get() = _event

    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val accounts = accountRepository.getAccounts()
    private lateinit var currentAccount: Account

    fun btnLogInClicked() {
        validateUserName()
    }

    private fun validateUserName() {
        userName.value.let { username ->
            when {
                username.isNullOrEmpty()|| username.isBlank()  -> _event.postValue(Event(LogInEvent.UserNameEmpty))
                username.length < 6 -> _event.postValue(Event(LogInEvent.UserNameTooShort))
                countLetters(username) < 4 -> _event.postValue(Event(LogInEvent.UserNameTooLittleLetters))
                userExists(username) -> validatePassword()
                else -> _event.postValue(Event(LogInEvent.UserNameNotExisting))
            }
        }
    }

    private fun countLetters(username: String): Int {
        val digitsNumber = username.filter { it.isDigit() }.length
        return username.length.minus(digitsNumber)
    }

    private fun userExists(username: String): Boolean {
        var userExists = false
        accounts.forEach { account ->
            if (account.userName == username){
                userExists = true
                currentAccount = account
            }
        }
        return userExists
    }

    private fun validatePassword() {
        if (currentAccount.password == password.value){
            _event.postValue(Event(LogInEvent.NavigateToDashboard))
        } else{
            _event.postValue(Event(LogInEvent.PasswordIncorrect))

        }
    }
}


