package com.hubinsord.loginsimulator.app.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hubinsord.loginsimulator.app.domain.AccountRepository
import com.hubinsord.loginsimulator.core.wrapper.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogInVM @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _event = MutableLiveData<Event<CredentialInputValidator>>()
    val event: LiveData<Event<CredentialInputValidator>> get() = _event

    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val accounts = accountRepository.getAccounts()
    private var validatedAccountIndex: Int = -1

    fun onBtnLogInClicked() {
        validateUserName()
    }

    private fun validateUserName() {
        userName.value.let { username ->
            when {
                username.isNullOrEmpty()|| username.isBlank()  -> _event.postValue(Event(CredentialInputValidator.UserNameEmpty))
                username.length < 6 -> _event.postValue(Event(CredentialInputValidator.UserNameTooShort))
                countLetters(username) < 4 -> _event.postValue(Event(CredentialInputValidator.UserNameTooLittleLetters))
                userExists(username) -> validatePassword()
                else -> _event.postValue(Event(CredentialInputValidator.UserNameNotExisting))
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
                validatedAccountIndex = accounts.indexOf(account)
            }
        }
        return userExists
    }

    private fun validatePassword() {
        if (isIndexInListBound() && isPasswordCorrect()){
            _event.postValue(Event(CredentialInputValidator.NavigateToDashboard(validatedAccountIndex)))
        } else{
            _event.postValue(Event(CredentialInputValidator.PasswordIncorrect))

        }
    }

    private fun isIndexInListBound() = validatedAccountIndex > -1

    private fun isPasswordCorrect() = accounts[validatedAccountIndex].password == password.value
}


