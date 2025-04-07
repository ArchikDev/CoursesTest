package com.example.coursestest.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.coursestest.presentation.theme.Green

@Composable
fun ButtonSimple(
    isEnabled: Boolean = true,
    text: String = "",
    icon: Int? = null,
    onClick: () -> Unit = { },
    height: Dp = 40.dp,
    bgColor: Color = Green,
    bgBrush: Brush? = null,
    modifier: Modifier = Modifier
) {
    val bg by remember {
        derivedStateOf {
            bgBrush ?: Brush.linearGradient(listOf(bgColor, bgColor))
        }
    }

    Button(
        modifier = Modifier
            .then(modifier)
            .height(height)
            .clip(RoundedCornerShape(30.dp))
            .alpha(if (isEnabled) 1f else .4f)
            .background(bg)
            .padding(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = White,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = White
        ),
        onClick = { onClick() },
        enabled = isEnabled
    ) {
        if (text.isNotEmpty()) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge
            )
        }
        if (icon != null) {
            Icon(painter = painterResource(icon), contentDescription = null)
        }

    }
}