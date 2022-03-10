package com.base.common.utils

fun String?.getNotEmptyOrNull(): String? {
    return takeIf { !isNullOrEmpty() }
}