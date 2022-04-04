package com.base.common_android.ui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.base.common.IDispatchers
import com.base.common_android.utils.logging.ILogTaggable
import com.base.common_android.utils.truncateLogTag
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class StateViewModel<S : IViewState, I : IViewIntent, E : IViewEffect>(
    protected val dispatchers: IDispatchers,
    private val reducer: suspend (accumulator: S, value: S) -> S = { _, value -> value }
) : ViewModel(), ILogTaggable {
    private val _state: MutableStateFlow<S> by lazy { getStateFlow() }
    private val _effect by lazy { getEffectFlow() }

    private val intentFlow = MutableSharedFlow<I>(INTENT_FLOW_EXTRA_BUFFER_CAPACITY)

    val stateLiveData by lazy { _state.asLiveData() }
    val effectLiveData by lazy { _effect.asLiveData() }
    override val TAG = this::class.java.simpleName.truncateLogTag()

    init {
        viewModelScope.launch(dispatchers.Default) {
            handleIntents()
        }
    }

    suspend fun emitIntent(intent: I) {
        intentFlow.emit(intent)
    }

    private suspend fun handleIntents() {
        intentFlow.collect { intent ->
            onIntent(intent)?.let { state -> setState(state) }
        }
    }

    open suspend fun handleExternalViewState(state: IViewState) {}
    open suspend fun handleExternalViewEffect(effect: IViewEffect) {}

    protected suspend fun setState(state: S) {
        val newState = reducer(this._state.value, state)
        this._state.emit(newState)
    }

    protected suspend fun setState(reduce: S.() -> S) {
        val newState = _state.value.reduce()
        _state.emit(newState)
    }

    protected suspend fun emitEffect(effect: E) {
        this._effect.emit(effect)
    }

    protected suspend fun emitEffect(builder: () -> E) {
        _effect.emit(builder())
    }

    abstract suspend fun onIntent(intent: I): S?

    /**
     * Provide view model view state [MutableStateFlow].
     *
     * This function is called once, when view model is created and provide [MutableStateFlow]
     * view state used by view model.
     *
     * If you need share the view model's view state with other components then:
     * 1. Pass an state [MutableStateFlow] with constructor - this value will be injected by Hilt.
     * 2. Return the [MutableStateFlow] with this function.
     * 3. Add the [MutableStateFlow] to Hilt - most probably installed in
     * [ActivityRetainedComponent] and [ActivityScoped].
     * 4. Inject the [MutableStateFlow] for required component.
     *
     * @return [MutableStateFlow] view state used by the view model.
     */
    protected abstract fun getStateFlow(): MutableStateFlow<S>

    /**
     * Provide view model view effect [MutableSharedFlow].
     *
     * This function is called once, when view model is created and provide [MutableSharedFlow]
     * view effect used by view model.
     *
     * If you need share the view model's view effect with other components then:
     * 1. Pass an effect [MutableSharedFlow] with constructor - this value will be injected by Hilt.
     * 2. Return the [MutableSharedFlow] with this function.
     * 3. Add the [MutableSharedFlow] to Hilt - most probably installed in
     * [ActivityRetainedComponent] and [ActivityScoped].
     * 4. Inject the [MutableSharedFlow] for required component.
     *
     * @return [MutableSharedFlow] view effect used by the view model.
     */
    protected open fun getEffectFlow(): MutableSharedFlow<E> = MutableSharedFlow()

    private companion object {
        private const val INTENT_FLOW_EXTRA_BUFFER_CAPACITY = 64
    }
}
