package com.base.common_android.ui.mvi

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.base.common_android.utils.logging.ILogTaggable
import com.base.common_android.utils.truncateLogTag
import kotlinx.coroutines.launch

abstract class StateFragment<S : IViewState, I : IViewIntent, E : IViewEffect>(
    @LayoutRes contentLayoutId: Int
) : Fragment(contentLayoutId), ILogTaggable {

    override val TAG = this::class.java.simpleName.truncateLogTag()
    protected abstract val viewModel: StateViewModel<S, I, E>
    abstract fun render(viewState: S)
    open fun effect(effect: E) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            stateLiveData.observe(viewLifecycleOwner) { state ->
                render(state)
            }
            effectLiveData.observe(viewLifecycleOwner) { effect ->
                effect(effect)
            }
        }
    }

    fun emitIntent(intent: I) {
        lifecycleScope.launch {
            viewModel.emitIntent(intent)
        }
    }
}
