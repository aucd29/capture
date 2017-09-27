/*
 * Copyright (C) Hanwha S&C Ltd., 2017. All rights reserved.
 *
 * This software is covered by the license agreement between
 * the end user and Hanwha S&C Ltd., and may be
 * used and copied only in accordance with the terms of the
 * said agreement.
 *
 * Hanwha S&C Ltd., assumes no responsibility or
 * liability for any errors or inaccuracies in this software,
 * or any consequential, incidental or indirect damage arising
 * out of the use of the software.
 */

package net.sarangnamu.common.ui

import android.graphics.Bitmap
import android.util.Log
import android.view.View

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2017. 9. 27.. <p/>
 */

class Capture {
    companion object {
        fun get(view: View): Bitmap? {
            view.clearFocus()
            view.isPressed = false

            val cacheInfo = view.willNotCacheDrawing()
            view.setWillNotCacheDrawing(false)
            view.invalidate()
            view.buildDrawingCache()

            val color = view.drawingCacheBackgroundColor
            view.drawingCacheBackgroundColor = 0
            if (color != 0) {
                view.destroyDrawingCache()
            }

            val cached = view.getDrawingCache()
            if (cached == null) {
                return null
            }

            val bitmap = Bitmap.createBitmap(cached)
            view.destroyDrawingCache()
            view.setWillNotCacheDrawing(cacheInfo)
            view.drawingCacheBackgroundColor = color

            return bitmap
        }
    }
}