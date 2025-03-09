package com.uniandes.wakey.ui.screens.alarm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.uniandes.wakey.R
import com.uniandes.wakey.model.CheckItem
import com.uniandes.wakey.ui.components.AlertDialog
import com.uniandes.wakey.ui.components.CheckItemView


@Composable
fun CalendarPickerDialog(
    onDismiss: (calendarSelected: Boolean) -> Unit
) {
    val calendarList = listOf(
        "Calendar 1",
        "Calendar 2",
        "Calendar 3"
    )
    var selectedCalendar by remember { mutableStateOf(emptyList<String>()) }
    AlertDialog(
        onDismiss = {onDismiss(selectedCalendar.isNotEmpty())},
        positiveButton = stringResource(R.string.accept)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                stringResource(R.string.select_calendar),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall
            )
            for (calendar in calendarList) {
                CheckItemView(
                    item = CheckItem(
                        icon = Icons.Outlined.CalendarToday,
                        title = "${stringResource(R.string.calendar)} 1",
                        isChecked = calendar in selectedCalendar
                    ),
                    onCheckChange = {
                        selectedCalendar = selectedCalendar.plus(calendar)
                    }
                )
            }
        }
    }
}
