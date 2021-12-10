package com.app.marvelapp.common.result

/**
 * This class is used to provide a result for an operation, which may be succesfull or fail.
 * Based on https://github.com/michaelbull/kotlin-result
 */

sealed class MyResult<out V, out E> {
    data class Ok<out V>(val value: V) : MyResult<V, Nothing>()
    data class Err<out E>(val error: E) : MyResult<Nothing, E>()
}

/**
 * Invokes an [action] if this [MyResult] is [Ok].
 */
inline infix fun <V, E> MyResult<V, E>.onSuccess(action: (V) -> Unit): MyResult<V, E> {
    if (this is MyResult.Ok) {
        action(value)
    }
    return this
}

/**
 * Maps this [MyResult<V, E>][MyResult] to [MyResult<U, E>][MyResult] by either applying the
 * [transform] function to the [value][Ok.value] if this [MyResult] is [Ok], or returning this
 * [Err].
 */
inline infix fun <V, E, U> MyResult<V, E>.map(transform: (V) -> U): MyResult<U, E> {
    return when (this) {
        is MyResult.Ok -> MyResult.Ok(transform(value))
        is MyResult.Err -> this
    }
}


/**
 * Maps this [MyResult<V, E>][MyResult] to [MyResult<V, F>][MyResult] by either applying the
 * [transform] function to the [error][Err.error] if this [MyResult] is [Err], or returning this
 * [Ok].
 */
inline infix fun <V, E, F> MyResult<V, E>.mapError(transform: (E) -> F): MyResult<V, F> {
    return when (this) {
        is MyResult.Ok -> this
        is MyResult.Err -> MyResult.Err(transform(error))
    }
}


