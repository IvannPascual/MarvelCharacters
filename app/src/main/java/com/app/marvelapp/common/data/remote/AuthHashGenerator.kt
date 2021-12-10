package com.app.marvelapp.common.data.remote

import java.math.BigInteger
import java.security.MessageDigest
import java.security.PublicKey

class AuthHashGenerator {

    fun getHash(timeStamp :Long, privateKey: String, publicKey: String):String {

        /*
        md5(ts+privateKey+publicKey)
         */
        val hasStr="$timeStamp${privateKey+publicKey}"
        val m= MessageDigest.getInstance("MD5")
        return BigInteger(1, m.digest(hasStr.toByteArray())).toString(16)
    }

}