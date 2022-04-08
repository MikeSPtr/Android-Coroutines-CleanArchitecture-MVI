package com.base.common_android.ui.mvi

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.base.common_android.utils.logging.ILogTaggable
import com.base.common_android.utils.truncateLogTag
import kotlinx.coroutines.launch

abstract class StateAppCompatActivity<S : IViewState, I : IViewIntent, E : IViewEvent> :
    AppCompatActivity(), ILogTaggable {

    override val TAG = this::class.java.simpleName.truncateLogTag()
    protected abstract val viewModel: StateViewModel<S, I, E>

    abstract fun render(viewState: S)
    open fun event(event: E) {}

    override fun onStart() {
        super.onStart()
        viewModel.apply {
            stateLiveData.observe(this@StateAppCompatActivity) { state ->
                render(state)
            }
            eventLiveData.observe(this@StateAppCompatActivity) { event ->
                event(event)
            }
        }
    }

    fun emitIntent(intent: I) {
        lifecycleScope.launch {
            viewModel.emitIntent(intent)
        }
    }
}
