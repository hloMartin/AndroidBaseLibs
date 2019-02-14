package com.dodo.baselibs.utils

import java.lang.StringBuilder
import java.security.MessageDigest

/**
 * 加密相关
 *
 * @author martin
 */
object UEncryptUtil {

    /**
     * MD5
     *
     * @param secretKey
     * @return
     */
    fun md5(str: String): String? {
        str?.let {
            return encode(str, "MD5")
        }
        return null
    }

    /**
     * sha1
     */
    fun sha1(str: String?): String? {
        str?.let {
            return encode(str, "SHA-1")
        }
        return null
    }

    /**
     * sha256
     */
    fun sha256(str: String?): String? {
        str?.let {
            return encode(str, "SHA-256")
        }
        return null
    }

    private fun encode(str: String, encodeType: String): String {
        val digest = MessageDigest.getInstance(encodeType)
        val result = digest.digest(str.toByteArray())
        return toHex(result)
    }

    private fun toHex(byteArray: ByteArray): String {
        val result = with(StringBuilder()) {
            byteArray.forEach {
                val hex = it.toInt() and (0xFF)
                val hexStr = Integer.toHexString(hex)
                if (hexStr.length == 1) {
                    this.append("0")
                }
                this.append(hexStr)
            }
            this.toString()
        }
        return result
    }

}
