package com.example.coursestest.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone

@RequiresApi(Build.VERSION_CODES.O)
fun dateFormat(dateSt: String): String {
    return try {
        LocalDateTime.parse("${dateSt}T00:00")
            .atZone(ZoneId.of(TimeZone.getDefault().id, ZoneId.SHORT_IDS))
            .toLocalDateTime()
            .format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.forLanguageTag("ru-RU")))
    } catch (_: Exception) { "-" }
}

@RequiresApi(Build.VERSION_CODES.O)
fun String?.toDateLong(): Long {
    return try {
        LocalDateTime.parse("${this}T00:00")
            .atZone(ZoneId.of(TimeZone.getDefault().id, ZoneId.SHORT_IDS))
            .toInstant()
            .toEpochMilli()
    } catch (_: Exception) { 0 }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisableRippleEffect(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        content()
    }
}