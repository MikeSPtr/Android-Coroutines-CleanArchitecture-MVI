package com.base.common_android.utils.logging

import com.base.common.utils.logging.ILogger

object Logger : ILogger {
    private val loggers = mutableSetOf<ILogger>()

    object LoggerSettings {
        fun setLogger(logger: ILogger) {
            loggers.add(logger)
        }

        fun unsetLogger(logger: ILogger) {
            loggers.remove(logger)
        }
    }

    override fun v(tag: String?, msg: String) {
        for (logger in loggers) {
            logger.v(tag, msg)
        }
    }

    override fun v(tag: String?, msg: String?, tr: Throwable?) {
        for (logger in loggers) {
            logger.v(tag, msg, tr)
        }
    }

    override fun d(tag: String?, msg: String) {
        for (logger in loggers) {
            logger.d(tag, msg)
        }
    }

    override fun d(tag: String?, msg: String?, tr: Throwable?) {
        for (logger in loggers) {
            logger.d(tag, msg, tr)
        }
    }

    override fun i(tag: String?, msg: String) {
        for (logger in loggers) {
            logger.i(tag, msg)
        }
    }

    override fun i(tag: String?, msg: String?, tr: Throwable?) {
        for (logger in loggers) {
            logger.i(tag, msg, tr)
        }
    }

    override fun w(tag: String?, msg: String) {
        for (logger in loggers) {
            logger.w(tag, msg)
        }
    }

    override fun w(tag: String?, msg: String?, tr: Throwable?) {
        for (logger in loggers) {
            logger.w(tag, msg, tr)
        }
    }

    override fun w(tag: String?, tr: Throwable?) {
        for (logger in loggers) {
            logger.w(tag, tr)
        }
    }

    override fun isLoggable(var0: String?, var1: Int): Boolean {
        return loggers.isNotEmpty()
    }

    override fun e(tag: String?, msg: String) {
        for (logger in loggers) {
            logger.e(tag, msg)
        }
    }

    override fun e(tag: String?, msg: String?, tr: Throwable?) {
        for (logger in loggers) {
            logger.e(tag, msg, tr)
        }
    }

    override fun wtf(tag: String?, msg: String?) {
        for (logger in loggers) {
            logger.wtf(tag, msg)
        }
    }

    override fun wtf(tag: String?, tr: Throwable) {
        for (logger in loggers) {
            logger.wtf(tag, tr)
        }
    }

    override fun wtf(tag: String?, msg: String?, tr: Throwable?) {
        for (logger in loggers) {
            logger.wtf(tag, msg, tr)
        }
    }

    override fun getStackTraceString(tr: Throwable?): String {
        TODO("Not yet implemented")
    }

    override fun println(priority: Int, tag: String?, msg: String) {
        TODO("Not yet implemented")
    }
}
