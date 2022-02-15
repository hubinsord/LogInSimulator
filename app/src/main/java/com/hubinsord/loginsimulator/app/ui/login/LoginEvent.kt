package com.hubinsord.loginsimulator.app.ui.login

sealed class LoginEvent{
    data class NavigateToDashboard(val id: Int) : LoginEvent()
}
