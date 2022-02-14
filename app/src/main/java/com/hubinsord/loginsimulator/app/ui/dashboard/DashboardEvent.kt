package com.hubinsord.loginsimulator.app.ui.dashboard


sealed class DashboardEvent {
    object LoggedInFirstTime : DashboardEvent()
    data class LoggedIn(val date: String) : DashboardEvent()
    object Logout : DashboardEvent()
}
