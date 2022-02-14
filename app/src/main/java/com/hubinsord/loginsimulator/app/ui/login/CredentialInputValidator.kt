package com.hubinsord.loginsimulator.app.ui.login

sealed class CredentialInputValidator {
    data class NavigateToDashboard(val id: Int) : CredentialInputValidator()
    object UserNameEmpty : CredentialInputValidator()
    object UserNameTooShort : CredentialInputValidator()
    object UserNameTooLittleLetters : CredentialInputValidator()
    object UserNameNotExisting : CredentialInputValidator()
    object PasswordIncorrect : CredentialInputValidator()
}

