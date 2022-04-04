package com.base.common_android.utils.logging

import android.util.Log
import com.base.common.utils.logging.ILogger

class AndroidLogger : ILogger {
    override fun v(tag: String?, msg: String) {
        Log.v(tag, msg)
    }

    override fun v(tag: String?, msg: String?, tr: Throwable?) {
        Log.v(tag, msg, tr)
    }

    override fun d(tag: String?, msg: String) {
        Log.d(tag, msg)
    }

    override fun d(tag: String?, msg: String?, tr: Throwable?) {
        Log.d(tag, msg, tr)
    }

    override fun i(tag: String?, msg: String) {
        Log.i(tag, msg)
    }

    override fun i(tag: String?, msg: String?, tr: Throwable?) {
        Log.i(tag, msg, tr)
    }

    override fun w(tag: String?, msg: String) {
        Log.w(tag, msg)
    }

    override fun w(tag: String?, msg: String?, tr: Throwable?) {
        Log.w(tag, msg, tr)
    }

    override fun w(tag: String?, tr: Throwable?) {
        Log.w(tag, tr)
    }

    override fun isLoggable(var0: String?, var1: Int): Boolean {
        return Log.isLoggable(var0, var1)
    }

    override fun e(tag: String?, msg: String) {
        Log.e(tag, msg)
    }

    override fun e(tag: String?, msg: String?, tr: Throwable?) {
        Log.e(tag, msg, tr)
    }

    override fun wtf(tag: String?, msg: String?) {
        Log.wtf(tag, msg)
    }

    override fun wtf(tag: String?, tr: Throwable) {
        Log.wtf(tag, tr)
    }

    override fun wtf(tag: String?, msg: String?, tr: Throwable?) {
        Log.wtf(tag, msg, tr)
    }

    override fun getStackTraceString(tr: Throwable?): String {
        return Log.getStackTraceString(tr)
    }

    override fun println(priority: Int, tag: String?, msg: String) {
        Log.println(priority, tag, msg)
    }
}
