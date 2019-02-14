package com.dodo.baselibs.utils

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream

/**
 * 文件操作相关
 *
 * @author martin
 */
object UFileUtil {

    /**
     * 复制文件
     *
     * @param oldPath
     * @param newPath
     */
    fun copyFile(oldPath: String, newPath: String) {
        try {
            val newFile = File(newPath)
            if (newFile.exists() && newFile.isFile) {
                return
            }
            if (newFile.parentFile == null || !newFile.parentFile.isDirectory) {
                newFile.parentFile.mkdirs()
            }
            val oldfile = File(oldPath)
            if (oldfile.exists()) {
                // 读入原文件
                val inStream = FileInputStream(oldPath)
                val outStream = FileOutputStream(newPath)
                val buffer = ByteArray(1024 * 8)

                var byteread = 0
                while (inStream.read(buffer)?.let { it != -1 }) {
                    outStream.write(buffer, 0, byteread)
                }
                outStream.close()
                inStream.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
