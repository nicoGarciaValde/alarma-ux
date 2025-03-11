package com.uniandes.wakey.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uniandes.wakey.ui.theme.WakeyWakeyTheme
import com.uniandes.wakey.ui.theme.onBackground
import com.uniandes.wakey.ui.theme.onPrimary
import com.uniandes.wakey.ui.theme.primary

@Composable
fun DaysSelection(
    onDaysSelected: (Set<String>) -> Unit
) {
    val days = listOf("L", "M", "X", "J", "V", "S", "D")
    val selectedDays = remember { mutableStateOf(setOf<String>()) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        days.forEach { day ->
            CircularDayCheckbox(
                day = day,
                isSelected = selectedDays.value.contains(day),
                onToggle = {
                    selectedDays.value = if (selectedDays.value.contains(day)) {
                        selectedDays.value - day
                    } else {
                        selectedDays.value + day
                    }
                    onDaysSelected(selectedDays.value)
                }
            )
        }
    }
}

@Composable
fun CircularDayCheckbox(
    day: String,
    isSelected: Boolean,
    onToggle: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(if (isSelected) primary else onBackground.copy(alpha = 0.3f))
            .clickable { onToggle() }
    ) {
        Text(
            text = day,
            color = if (isSelected) onPrimary else onBackground        )
    }
}


@Preview
@Composable
fun DayPickerPreview() {
    WakeyWakeyTheme {
        Box(
            Modifier.fillMaxSize()
        ) {
            DaysSelection(onDaysSelected = {

            })
        }
    }
}