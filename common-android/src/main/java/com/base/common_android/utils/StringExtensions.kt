package com.base.common_android.utils

/**
 * Return if [String] contains only letters.
 *
 * @return True is string contains only letters, False otherwise.
 */
fun String.isLetters(): Boolean {
    return matches("^[a-zA-Z]*$".toRegex())
}

/**
 * Then logging, we might encounter an error with the message "The loggin tag can be at most 23
 * characters..".
 * This function will truncate log tags with more than 23 characters.
 */
fun String.truncateLogTag(): String {
    return takeLast(23)
}
