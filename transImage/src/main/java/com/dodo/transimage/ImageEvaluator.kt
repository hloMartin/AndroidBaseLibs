package com.dodo.transimage

import android.animation.TypeEvaluator
import android.graphics.RectF

class ImageEvaluator : TypeEvaluator<RectF> {
    override fun evaluate(fraction: Float, startValue: RectF, endValue: RectF): RectF {
        var newRectF = RectF()

        //先计算大小的变化
        val tempWidth = (startValue.width() + (endValue.width() - startValue.width()) * fraction)
        val tempHeight = (startValue.height() + (endValue.height() - startValue.height()) * fraction)

        //再计算坐标的移动，坐标的移动会影响大小的，所以要放在后面
        newRectF.left = startValue.left + (endValue.left - startValue.left) * fraction
        newRectF.top = startValue.top + (endValue.top - startValue.top) * fraction

        //移动后，重新赋值 right bottom
        newRectF.right = newRectF.left + tempWidth
        newRectF.bottom = newRectF.top + tempHeight

        return newRectF
    }
}