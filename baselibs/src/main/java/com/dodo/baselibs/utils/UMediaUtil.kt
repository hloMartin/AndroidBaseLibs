package com.dodo.baselibs.utils

import java.io.File

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * 多媒体相关工具
 *
 * @author martin
 */
object UMediaUtil {

    /**
     * play Video
     *
     * @param videoPath
     * @param context
     */
    fun playVideo(videoPath: String, context: Context) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        val uri = Uri.fromFile(File(videoPath))
        intent.setDataAndType(uri, "video/*")
        //调用系统自带的播放器
        context.startActivity(intent)
    }


}
