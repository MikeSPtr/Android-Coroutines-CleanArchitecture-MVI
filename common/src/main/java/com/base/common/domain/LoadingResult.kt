package com.base.common.domain

sealed class LoadingResult<out Type> {
    object Loading : LoadingResult<Nothing>()
    data class Result<out Type>(val result: kotlin.Result<Type>) : LoadingResult<Type>()
}

/**
 * Get [LoadingResult] result.
 *
 * @return [LoadingResult] [Result] if [LoadingResult.Result], null otherwise.
 */
fun <T> LoadingResult<T>.getResultOrNull(): Result<T>? {
    return (this as? LoadingResult.Result)?.result
}

/**
 * Map [LoadingResult] value type.
 *
 * If [LoadingResult.Loading] then returns [LoadingResult.Loading].
 * If [LoadingResult.Result] with success [Result] then map value with [successMapper].
 * If [LoadingResult.Result] with failure [Result] then map to [Result] failure with the same
 * exception as origin.
 *
 * @param successMapper maps success [Result] with [R] type to [T] type success [Result].
 * @param failureMapper maps failed [Result] with [R] type to [T] type failure [Result].
 *
 * @return [T] type [LoadingResult].
 */
fun <R, T> LoadingResult<R>.mapResult(
    successMapper: (result: Result<R>) -> Result<T>,
    failureMapper: ((result: Result<R>) -> Result<T>) =
        { result -> Result.failure(result.exceptionOrNull()!!) }
): LoadingResult<T> {
    return when (this) {
        LoadingResult.Loading -> LoadingResult.Loading
        is LoadingResult.Result -> {
            result.mapResultToLoadingResult(successMapper, failureMapper)
        }
    }
}

/**
 * Map [LoadingResult] value type.
 *
 * If [LoadingResult.Loading] then returns [LoadingResult.Loading].
 * If [LoadingResult.Result] with success [Result] then map value with [successMapper].
 * If [LoadingResult.Result] with failure [Result] then map to [Result] failure with the same
 * exception as origin.
 *
 * @param successMapper maps [Result] value for success [Result]
 *
 * @return Mapped LoadingResult.
 */
fun <T, P> LoadingResult<T>.map(
    successMapper: (value: T) -> P,
): LoadingResult<P> {
    return when (this) {
        LoadingResult.Loading -> LoadingResult.Loading
        is LoadingResult.Result -> LoadingResult.Result(result.map(successMapper))
    }
}

/**
 * Map [Result] to [LoadingResult.Result].
 *
 * @return [LoadingResult.Result] with [Result] as result.
 */
fun <T> Result<T>.mapResultToLoadingResult(): LoadingResult<T> = LoadingResult.Result(this)

/**
 * Map [Result] with [T] type to [LoadingResult.Result] with [P] type.
 *
 *
 */
fun <T, P> Result<T>.mapResultToLoadingResult(
    successMapper: (result: Result<T>) -> Result<P>,
    failedMapper: ((result: Result<T>) -> Result<P>) =
        { result -> Result.failure(result.exceptionOrNull()!!) }
): LoadingResult.Result<P> {
    return when {
        isSuccess -> LoadingResult.Result(successMapper(this))
        else -> LoadingResult.Result(failedMapper(this))
    }
}

fun <T> Result<T>.mapToLoadingResult(): LoadingResult.Result<T> = LoadingResult.Result(this)

fun <T, P> Result<T>.mapToLoadingResult(
    successMapper: (value: T) -> P
): LoadingResult.Result<P> {
    return LoadingResult.Result(map(successMapper))
}
