package com.base.common_android.ui.mvi

sealed class DefaultViewEvent : IViewEvent {
    data class ErrorViewEvent(
        val error: Throwable?,
        val message: String?
    ) : DefaultViewEvent()

    data class InfoViewEvent(
        val message: String?
    ) : DefaultViewEvent()
}
