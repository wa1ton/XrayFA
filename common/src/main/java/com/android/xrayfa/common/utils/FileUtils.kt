package com.android.xrayfa.common.utils

import java.io.File
import java.io.FileInputStream
import java.security.MessageDigest

fun calculateFileHash(file: File, algorithm: String = "SHA-256"): String {
    if (!file.exists()) return ""

    val buffer = ByteArray(8192)
    val digest = MessageDigest.getInstance(algorithm)
    FileInputStream(file).use { fis ->
        var bytesRead: Int
        while (fis.read(buffer).also { bytesRead = it } != -1) {
            digest.update(buffer, 0, bytesRead)
        }
    }
    return digest.digest().joinToString("") { "%02x".format(it) }
}