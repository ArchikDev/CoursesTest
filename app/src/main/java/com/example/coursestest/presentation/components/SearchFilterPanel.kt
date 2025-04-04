package com.example.coursestest.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.coursestest.R
import com.example.coursestest.presentation.theme.LightGray

@Composable
fun SearchFilterPanel() {
    val searchText = remember { mutableStateOf(TextFieldValue("")) }

    Row(modifier = Modifier.fillMaxWidth()) {
        FieldSearch(
            modifier = Modifier.weight(1f),
            value = searchText.value,
            onValueChanged = {
                searchText.value = it
            },
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = {},
            modifier = Modifier.size(56.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = LightGray,
            ),
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_filter),
                contentDescription = null)
        }
    }
}