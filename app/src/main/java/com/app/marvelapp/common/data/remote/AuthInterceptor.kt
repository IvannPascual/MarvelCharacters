package com.app.marvelapp.common.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val authHashGenerator: AuthHashGenerator) : Interceptor {

    private val apiKey = "apikey"
    private val timestamp = "ts"
    private val hash = "hash"
    //TODO move to local properties
    private val privateKey = ""
    private val publicKey = ""

    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val originalHttpUrl = original.url
        val ts = System.currentTimeMillis()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(apiKey, publicKey)
            .addQueryParameter(timestamp, "$ts")
            .addQueryParameter(hash, authHashGenerator.getHash(ts, privateKey, publicKey)).build()

        val requestBuilder = original.newBuilder().url(url)
        return chain.proceed(requestBuilder.build())
    }


}