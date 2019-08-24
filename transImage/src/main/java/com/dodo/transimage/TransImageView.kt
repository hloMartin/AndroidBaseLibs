package com.dodo.transimage

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import com.dodo.baselibs.utils.UScreenUtil

class TransImageView constructor(context: Context, attrs: AttributeSet? = null) : ImageView(context, attrs) {
    val TAG = "[TransImageView]"

    /**
     * 动画时长
     */
    private val ANIMATION_TIME = 200L

    /**
     * 标记当前是否在动画中
     */
    private var mFlagIsAnimationIng = false

    private var mBitmapWidth: Float = 0f
    private var mBitmapHeight: Float = 0f
    private var mBitmap: Bitmap? = null

    private var mViewRectFStart: RectF? = null
    private var mViewRectFEnd: RectF? = null
    private var mViewRectFCurrent: RectF? = null

    private lateinit var mPaint: Paint

    private var mScreenSize: Point? = null

    private var mAnimator: ValueAnimator? = null

    init {
        mScreenSize = UScreenUtil.getAppScreenSize(context)
    }

    override fun setImageBitmap(bm: Bitmap?) {
        super.setImageBitmap(bm)
        mBitmap = bm
    }

    /**
     * 这里开启动画
     */
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (width == 0 || height == 0) {
            return
        }

    }

    /**
     * 设置之前图片的位置信息
     */
    fun setLastViewRectF(startRect: RectF?) {
        mViewRectFStart = startRect
    }

    /**
     * 显示图片展示动画
     */
    private fun doEnterAnimation() {

    }

    /**
     * 显示图片退出动画
     */
    private fun doExitAnimation() {

    }

    @Synchronized
    private fun startAnimation(startRect: RectF, endRectF: RectF) {
        mAnimator = ValueAnimator.ofObject(ImageEvaluator(), startRect, endRectF)
        mAnimator?.run {
            addUpdateListener {
                //动画过程
                mViewRectFCurrent = it.animatedValue as RectF
                postInvalidate()
            }
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    mFlagIsAnimationIng = false
                }
            })
            interpolator = DecelerateInterpolator()
            duration = ANIMATION_TIME
            start()
            mFlagIsAnimationIng = true
        }
    }

    override fun onDraw(canvas: Canvas) {
        if (visibility == View.GONE) {
            return
        }
        if (mFlagIsAnimationIng) {
            drawAnimation(canvas)
        } else {
            super.onDraw(canvas)
        }
    }

    private fun drawAnimation(canvas: Canvas) {
        if (mViewRectFCurrent == null) {
            return
        }
        if (mBitmap == null) {
            return
        }
        canvas.save()

        val rate = mBitmap!!.width * 1f / mBitmap!!.height

        var displayWidth: Float
        var displayHeight: Float

        if (mBitmap!!.width > mBitmap!!.height) {
            //横图，根据高度，以及长宽比，计算出此时图片对应的真实显示宽度
            displayHeight = mViewRectFCurrent!!.height()
            displayWidth = displayHeight * rate
        } else {
            //竖图，根据宽度，以及长宽比，计算出此时图片对应的真实高度
            displayWidth = mViewRectFCurrent!!.width()
            displayHeight = displayWidth / rate
        }

        val widthGap = (displayWidth - mViewRectFCurrent!!.width()) * 1f / 2
        val heightGap = (displayHeight - mViewRectFCurrent!!.height()) * 1f / 2

        //裁剪区域
        canvas.clipRect(mViewRectFCurrent)
        canvas.translate(mViewRectFCurrent!!.left - widthGap, mViewRectFCurrent!!.top - heightGap)
        val srcRect = Rect(0, 0, mBitmap!!.width, mBitmap!!.height)
        //渲染区域
        val desRect = Rect(0, 0, displayWidth.toInt(), displayHeight.toInt())
        if (!mBitmap!!.isRecycled) {
            canvas.drawBitmap(mBitmap, srcRect, desRect, null)
        }

        canvas.restore()
    }


}