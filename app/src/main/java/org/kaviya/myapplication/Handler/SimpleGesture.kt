package org.kaviya.myapplication.Handler

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import org.kaviya.myapplication.MainActivity

class SimpleGesture(private val context: MainActivity, sgl: SimpleGestureListener) : SimpleOnGestureListener() {
    private val swipe_Min_Distance = 100
    private val swipe_Max_Distance = 350
    private val swipe_Min_Velocity = 100
    private val running = true
    private val detector: GestureDetector
    private val listener: SimpleGestureListener

    companion object {
        const val SWIPE_UP = 1
        const val SWIPE_DOWN = 2
        const val SWIPE_LEFT = 3
        const val SWIPE_RIGHT = 4

    }

    init {
        detector = GestureDetector(context, this)
        listener = sgl
    }

    //region Touch event
    fun onTouchEvent(event: MotionEvent?) {
        if (!running) return
        val result = detector.onTouchEvent(event)
    }
//endregion

    //region onFling
    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        var velocityX = velocityX
        var velocityY = velocityY

        val xDistance = Math.abs(e1.x - e2.x)
        val yDistance = Math.abs(e1.y - e2.y)

        if (xDistance > swipe_Max_Distance || yDistance > swipe_Max_Distance)
            return false

        velocityX = Math.abs(velocityX)
        velocityY = Math.abs(velocityY)
        var result = false
// right to left
        if (velocityX > swipe_Min_Velocity && xDistance > swipe_Min_Distance) {
            if (e1.x > e2.x)
                listener.onSwipe(SWIPE_LEFT) else listener.onSwipe(SWIPE_RIGHT)
            result = true
        }
        // bottom to up
        else if (velocityY > swipe_Min_Velocity && yDistance > swipe_Min_Distance) {
            if (e1.y > e2.y)
                listener.onSwipe(SWIPE_UP) else listener.onSwipe(SWIPE_DOWN)
            result = true
        }
        return result
    }
    //endregion

    //region single tap
    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        Log.d("Gesture ", " onSingleTapConfirmed");
        return true;
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.d("Gesture ", " onSingleTapUp");
        return true;
    }
//    endregion

    //region long press
    override fun onLongPress(e: MotionEvent) {
        Log.d("Gesture ", " onLongPress")
        super.onLongPress(e)

    }
    //endregion

    //region onDwon
    override fun onDown(e: MotionEvent?): Boolean {
        Log.d("Gesture ", " onDown");
        return true;
    }
//endregion

    //region double tap
    override fun onDoubleTap(arg: MotionEvent): Boolean {
        listener.onDoubleTap()
        return true
    }

    override fun onDoubleTapEvent(arg: MotionEvent): Boolean {
        return true
    }
    //endregion

    //region show press
    override fun onShowPress(e: MotionEvent?) {
        Log.d("Gesture ", " onShowPress");
    }
    //endregion

    //region scroll
    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        if (e1.y < e2.y) {
            Log.d("Gesture ", " Scroll Down")
        }
        if (e1.y > e2.y) {
            Log.d("Gesture ", " Scroll Up")
        }
        return true
    }
//endregion

    //region interface
    interface SimpleGestureListener {
        fun onSwipe(direction: Int)
        fun onDoubleTap()
    }
//endregion


}