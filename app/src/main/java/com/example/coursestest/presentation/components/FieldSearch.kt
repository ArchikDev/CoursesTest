package com.example.coursestest.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.coursestest.R
import com.example.coursestest.presentation.theme.LightGray
import com.example.coursestest.presentation.theme.White
import com.example.coursestest.presentation.theme.White50

@Composable
fun FieldSearch(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChanged: (TextFieldValue) -> Unit,
    maxLines: Int = 1,
) {
    val isFocused = remember { mutableStateOf(false) }

    BasicTextField(
        modifier = Modifier
            .then(modifier)
            .onFocusChanged { isFocused.value = it.isFocused },
        value = value,
        onValueChange = onValueChanged,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = White50
        ),
        maxLines = maxLines,
        cursorBrush = SolidColor(White50),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = LightGray,
                        shape = RoundedCornerShape(28.dp)
                    )
                    .height(56.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(LightGray)
                    .padding(horizontal = 18.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_search),
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier.padding(end = 14.dp)
                    )
                    if(value.text.isEmpty() and !isFocused.value)
                        Text(
                            text = stringResource(R.string.searchCoursesTxt),
                            color = White50,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    Box(modifier = Modifier.weight(1f)) {
                        innerTextField.invoke()
                    }
                }
            }
        },
    )
}