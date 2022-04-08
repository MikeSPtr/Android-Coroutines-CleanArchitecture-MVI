package com.base.common_android.ui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.base.common.IDispatchers
import com.base.common_android.utils.logging.ILogTaggable
import com.base.common_android.utils.truncateLogTag
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class StateViewModel<S : IViewState, I : IViewIntent, E : IViewEvent>(
    defaultState: S,
    protected val dispatchers: IDispatchers,
    private val reducer: suspend (accumulator: S, value: S) -> S = { _, value -> value }
) : ViewModel(), ILogTaggable {
    private val _state = MutableStateFlow(defaultState)
    private val _event = MutableSharedFlow<E>(
        extraBufferCapacity = EVENT_FLOW_EXTRA_BUFFER_CAPACITY
    )

    private val intentFlow = MutableSharedFlow<I>(
        extraBufferCapacity = INTENT_FLOW_EXTRA_BUFFER_CAPACITY
    )

    val stateLiveData by lazy { _state.asLiveData() }
    val eventLiveData by lazy { _event.asLiveData() }
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

    protected suspend fun setState(state: S) {
        val newState = reducer(this._state.value, state)
        this._state.emit(newState)
    }

    protected suspend fun setState(reduce: S.() -> S) {
        val newState = _state.value.reduce()
        _state.emit(newState)
    }

    protected suspend fun emitEvent(event: E) {
        this._event.emit(event)
    }

    protected suspend fun emitEvent(builder: () -> E) {
        _event.emit(builder())
    }

    abstract suspend fun onIntent(intent: I): S?

    private companion object {
        private const val INTENT_FLOW_EXTRA_BUFFER_CAPACITY = 64
        private const val EVENT_FLOW_EXTRA_BUFFER_CAPACITY = Int.MAX_VALUE
    }
}
