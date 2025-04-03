package com.example.coursestest.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.coursestest.presentation.theme.LightGray
import com.example.coursestest.presentation.theme.White50

@Composable
fun FieldInput(
    modifier: Modifier = Modifier,
    type: String = "text",
    value: String,
    onValueChanged: (String) -> Unit,
    label: String,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    placeholder: String = ""
) {
    val focusRequester = remember { FocusRequester() }
    val isFocused = remember { mutableStateOf(false) }


    Column(modifier = Modifier.then(modifier).fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(10.dp))
        BasicTextField(
            modifier = Modifier
                .then(modifier)
                .onFocusChanged { isFocused.value = it.isFocused },
            value = value,
            onValueChange = onValueChanged,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = White50
            ),
            visualTransformation = if (type == "password") PasswordVisualTransformation() else VisualTransformation.None,
            cursorBrush = SolidColor(White50),
            maxLines = maxLines,
            keyboardOptions = keyboardOptions,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = LightGray,
                            shape = RoundedCornerShape(30.dp)
                        )
                        .height(40.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(LightGray)
                        .padding(horizontal = 16.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize().focusRequester(focusRequester)
                    ) {
                        if(value.isEmpty() and !isFocused.value)
                            Text(
                                text = placeholder,
                                color = White50,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        innerTextField.invoke()
                    }
                }
            }
        )
    }
}