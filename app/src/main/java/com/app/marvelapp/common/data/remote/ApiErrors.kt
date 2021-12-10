package com.app.marvelapp.common.data.remote

import android.accounts.NetworkErrorException
import com.app.marvelapp.R
import com.app.marvelapp.common.result.MyResult
import kotlinx.coroutines.CancellationException
import java.io.IOException
import java.net.UnknownHostException

const val ServerError = 500

typealias UserFriendlyError = Int

sealed class CompleteApiError<in T>
object NoInternetApiError : CompleteApiError<Any?>()
object NetworkApiError : CompleteApiError<Any?>()
object ServerApiError : CompleteApiError<Any?>()
object UnknownApiError : CompleteApiError<Any?>()
object ResourceNotFound : CompleteApiError<Any?>()
class ExpectedApiError<T>(val which: T) : CompleteApiError<T>()

sealed class CompleteUiApiError<in T>


fun <T> CompleteApiError<T>.toBasicUi(): Int = when (this) {
    NoInternetApiError -> R.string.api_error_no_internet
    NetworkApiError, ServerApiError, UnknownApiError -> R.string.api_some_error
    ResourceNotFound -> R.string.api_error_resource_not_found
    else -> R.string.api_error_unknown
}

object ApiErrorHandling {

    fun handle(e: Throwable) = when (e) {
        is CancellationException -> throw e
        is NoConnectivityException -> MyResult.Err(NoInternetApiError)
        is NetworkErrorException -> MyResult.Err(NetworkApiError)
        is UnknownHostException -> MyResult.Err(NoInternetApiError)
        is ClassCastException -> {
            MyResult.Err(ServerApiError)
        }
        else -> {
            MyResult.Err(UnknownApiError)
        }
    }
}

class NoConnectivityException : IOException() {
    override val message: String = "No internet connection"
}
