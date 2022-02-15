package com.hubinsord.loginsimulator.app.ui.login

sealed class CredentialInputValidator {
    object UserNameDefault: CredentialInputValidator()
    object UserNameEmpty : CredentialInputValidator()
    object UserNameTooShort : CredentialInputValidator()
    object UserNameTooLittleLetters : CredentialInputValidator()
    object UserNameNotExisting : CredentialInputValidator()
    object PasswordDefault: CredentialInputValidator()
    object PasswordIncorrect : CredentialInputValidator()
}

