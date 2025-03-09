package com.uniandes.wakey.ui.screens.reminder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.uniandes.wakey.R
import com.uniandes.wakey.ui.components.WakeyFAButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalAddReminderBottomSheet(
    onAddReminder: (Reminder) -> Unit,
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = Modifier.fillMaxHeight(0.90f)
    ) {
        var title by remember { mutableStateOf("") }
        var date by remember { mutableStateOf("") }
        var hour by remember { mutableStateOf("") }

        var isTitleError by remember { mutableStateOf(false) }
        var isDateError by remember { mutableStateOf(false) }
        var isHourError by remember { mutableStateOf(false) }


        Box {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp)
            ) {

                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it
                        isTitleError = false
                    },
                    label = { Text(stringResource(R.string.reminder_name)) },
                    modifier = Modifier.fillMaxWidth(),
                    isError = isTitleError,
                    trailingIcon = {
                        if (isTitleError) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Error",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = date,
                    onValueChange = {
                        date = it
                        isDateError = false
                    },
                    label = { Text(stringResource(R.string.date)) },
                    modifier = Modifier.fillMaxWidth(),
                    isError = isDateError,
                    trailingIcon = {
                        if (isDateError) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Error",
                                tint = MaterialTheme.colorScheme.error
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = "Date"
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = hour,
                    onValueChange = {
                        hour = it
                        isHourError = false
                    },
                    label = { Text(stringResource(R.string.hour)) },
                    modifier = Modifier.fillMaxWidth(),
                    isError = isHourError,
                    trailingIcon = {
                        if (isHourError) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Error",
                                tint = MaterialTheme.colorScheme.error
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Hour"
                            )
                        }
                    }
                )
            }

            WakeyFAButton (
                onClick = {
                    if (title.isBlank() || date.isBlank() || hour.isBlank()) {
                        isTitleError = true
                        isDateError = true
                        isHourError = true
                    } else {
                        onAddReminder(Reminder(title, "$date $hour"))
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Done"
                )
            }
        }
    }
}