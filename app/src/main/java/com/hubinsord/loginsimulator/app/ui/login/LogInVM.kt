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

    private val _event = MutableLiveData<Event<LoginEvent>>()
    val event: LiveData<Event<LoginEvent>> get() = _event

    private val _validationState = MutableLiveData<CredentialInputValidator>()
    val validationState: LiveData<CredentialInputValidator> get() = _validationState

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
                username.isNullOrEmpty()|| username.isBlank()  -> _validationState.postValue(CredentialInputValidator.UserNameEmpty)
                username.length < 6 -> _validationState.postValue(CredentialInputValidator.UserNameTooShort)
                countLetters(username) < 4 -> _validationState.postValue(CredentialInputValidator.UserNameTooLittleLetters)
                userExists(username) -> {
                    _validationState.postValue(CredentialInputValidator.UserNameDefault)
                    validatePassword()}
                else -> _validationState.postValue(CredentialInputValidator.UserNameNotExisting)
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
            _event.postValue(Event(LoginEvent.NavigateToDashboard(validatedAccountIndex)))
            _validationState.postValue(CredentialInputValidator.PasswordDefault)
        } else{
            _validationState.postValue(CredentialInputValidator.PasswordIncorrect)
        }
    }

    private fun isIndexInListBound() = validatedAccountIndex > -1

    private fun isPasswordCorrect() = accounts[validatedAccountIndex].password == password.value

}


