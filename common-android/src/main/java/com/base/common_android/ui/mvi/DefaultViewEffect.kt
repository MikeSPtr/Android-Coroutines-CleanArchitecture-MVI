package com.base.common_android.ui.mvi

sealed class DefaultViewEffect : IViewEffect {
    data class ErrorViewEffect(
        val error: Throwable?,
        val message: String?
    ) : DefaultViewEffect()

    data class InfoViewEffect(
        val message: String?
    ) : DefaultViewEffect()
}