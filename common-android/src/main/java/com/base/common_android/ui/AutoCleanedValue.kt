package com.base.common_android.ui

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AutoCleanedValue<T : Any>(
    lifecycleOwner: LifecycleOwner,
    private val initializer: (() -> T)?
) : ReadWriteProperty<LifecycleOwner, T> {

    private var _value: T? = null

    init {
        lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            val lifecycleObserver = object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    _value = null
                }
            }

            override fun onCreate(owner: LifecycleOwner) {
                lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
            }

            override fun onDestroy(owner: LifecycleOwner) {
                lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
            }
        })
    }

    override fun getValue(thisRef: LifecycleOwner, property: KProperty<*>): T {
        val value = _value

        if (value != null) {
            return value
        }

        if (thisRef.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            return initializer?.invoke().also { _value = it }
                ?: throw IllegalStateException(
                    "The value has not yet been set or no default initializer provided"
                )
        } else {
            throw IllegalStateException("Lifecycle might reach onDestroy or not initialized yet")
        }
    }

    override fun setValue(thisRef: LifecycleOwner, property: KProperty<*>, value: T) {
        _value = value
    }
}

fun <T : Any> LifecycleOwner.autoCleaned(initializer: (() -> T)? = null): AutoCleanedValue<T> {
    return AutoCleanedValue(this, initializer)
}
