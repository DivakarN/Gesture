package org.kaviya.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import org.kaviya.myapplication.Handler.SimpleGesture

class MainActivity : AppCompatActivity(), SimpleGesture.SimpleGestureListener {

    private var detector: SimpleGesture? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        detector = SimpleGesture(this,this)

    }
    // regionCall onTouchEvent of SimpleGestureFilter class
    override fun dispatchTouchEvent(me: MotionEvent): Boolean {
        detector!!.onTouchEvent(me)
        return super.dispatchTouchEvent(me)
    }
    //endregion

    //region swipe
    override fun onSwipe(direction: Int) {
        var swipe = ""
        when (direction) {
            SimpleGesture.Companion.SWIPE_RIGHT -> swipe = "Swipe Right"
            SimpleGesture.Companion.SWIPE_LEFT -> swipe = "Swipe Left"
            SimpleGesture.Companion.SWIPE_DOWN -> swipe = "Swipe Down"
            SimpleGesture.Companion.SWIPE_UP -> swipe = "Swipe Up"
        }
        Toast.makeText(this, swipe, Toast.LENGTH_SHORT).show()
    }
    //endregion

    //region Double tap
    override fun onDoubleTap() {
        Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show()
    }
    //endregion

}
