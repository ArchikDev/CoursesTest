package com.example.coursestest.utils

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.roundToInt

class ShiftAlignment(
    private val verticalShift: Float,
) : Alignment {
    override fun align(size: IntSize, space: IntSize, layoutDirection: LayoutDirection): IntOffset {
        val centerX = (space.width - size.width).toFloat() / 2f
        val centerY = 0
        val shiftedY = centerY + verticalShift
        return IntOffset(centerX.roundToInt(), shiftedY.roundToInt())
    }
}