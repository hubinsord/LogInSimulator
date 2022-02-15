package com.hubinsord.loginsimulator.app.ui.login

sealed class CredentialInputValidator {

    object UserNameEmpty : CredentialInputValidator()
    object UserNameTooShort : CredentialInputValidator()
    object UserNameTooLittleLetters : CredentialInputValidator()
    object UserNameNotExisting : CredentialInputValidator()
    object PasswordIncorrect : CredentialInputValidator()
    object UserNameDefault: CredentialInputValidator()
    object PasswordDefault: CredentialInputValidator()
}

