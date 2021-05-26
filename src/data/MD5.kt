package com.tonypepe.data

import java.math.BigInteger
import java.security.MessageDigest

fun String.toMD5(): String {
    val instance = MessageDigest.getInstance("MD5")
    val ans = BigInteger(1, instance.digest(this.toByteArray())).toString(16).padStart(32, '0').uppercase()
    return ans
}
