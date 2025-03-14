package com.uniandes.wakey.ui.screens.alarm

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.uniandes.wakey.R
import com.uniandes.wakey.ui.components.DaysSelection
import kotlinx.coroutines.launch
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalAddAlarmBottomSheet(
    alarm: Alarm,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    onSaveAlarm: (Alarm) -> Unit
) {
    val currentTime = Calendar.getInstance()
    var days by remember { mutableStateOf(alarm.days.toSet()) }
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR).takeIf { it > 0 } ?: 12,
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = false,
    )
    var repeat by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = Modifier.fillMaxHeight(0.90f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cancelar",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { onDismissRequest() }
                )
                Text(
                    text = "Listo",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        onSaveAlarm(
                            Alarm(
                                timePickerState.hour,
                                timePickerState.minute,
                                true,
                                repeat,
                                days.toList()
                            )
                        )
                        coroutineScope.launch { sheetState.hide() }
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // TimeInput centrado con formato AM/PM
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                TimeInput(
                    state = timePickerState
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    stringResource(R.string.repeat),
                )
                Switch(
                    checked = repeat,
                    onCheckedChange = {
                        repeat = it
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (repeat) {
                DaysSelection(
                    onDaysSelected = {
                        days = it
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            HorizontalDivider()
        }
    }
}
