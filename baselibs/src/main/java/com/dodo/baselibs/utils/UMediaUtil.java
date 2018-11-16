package com.dodo.baselibs.utils;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 多媒体相关工具
 *
 * @author martin
 */
public class UMediaUtil {

    /**
     * play Video
     *
     * @param videoPath
     * @param context
     */
    public static void playVideo(String videoPath, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        Uri uri = Uri.fromFile(new File(videoPath));
        intent.setDataAndType(uri, "video/*");
        //调用系统自带的播放器
        context.startActivity(intent);
    }


}
