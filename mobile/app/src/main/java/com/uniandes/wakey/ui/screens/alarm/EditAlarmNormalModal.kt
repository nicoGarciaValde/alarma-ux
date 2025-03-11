package com.uniandes.wakey.ui.screens.alarm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uniandes.wakey.R
import com.uniandes.wakey.ui.components.DaysSelection
import com.uniandes.wakey.ui.theme.WakeyWakeyTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalEditNormalSheet(
    alarm: Alarm,
    onDismissRequest: (Alarm) -> Unit,
    onDeleteAlarm: () -> Unit,
    sheetState: SheetState,
) {
    var repeat by remember { mutableStateOf(alarm.repeat) }
    var days by remember { mutableStateOf(alarm.days.toSet()) }
    var isFileAdded by remember { mutableStateOf(false) }
    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest(
                Alarm(
                    alarm.hour,
                    alarm.minute,
                    alarm.enabled,
                    repeat,
                    days.toList()
                )
            )
        },
        sheetState = sheetState,
        modifier = Modifier.fillMaxHeight(0.90f),
        content = {
            Column(
                Modifier.padding(
                    horizontal = 16.dp
                )
            ) {
                Row {
                    Text(
                        text = "${alarm.hour}:${alarm.minute} ${if (alarm.hour > 12) "PM" else "AM"}",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.Bold
                        )
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

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Button(
                        onClick = {
                            isFileAdded = isFileAdded.not()
                        },
                        colors = ButtonDefaults.elevatedButtonColors()
                    ) {
                        Row {
                            Icon(
                                imageVector = if(isFileAdded) {
                                    Icons.Default.Close
                                } else {
                                    Icons.Default.AttachFile
                                },
                                contentDescription = ""
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                if (isFileAdded) {
                                    stringResource(R.string.file_name)
                                } else {
                                    stringResource(R.string.add_file)
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = {
                        onDeleteAlarm()
                    }) {
                        Icon(
                            imageVector = Icons.TwoTone.Delete,
                            contentDescription = ""
                        )
                    }
                }

            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun ModalEditNormalSheetPreview() {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    WakeyWakeyTheme {
        ModalEditNormalSheet(
            alarm = Alarm(6, 10, true),
            onDismissRequest = {
                coroutineScope.launch {
                    sheetState.hide()
                }
            },
            onDeleteAlarm = {
                coroutineScope.launch {
                    sheetState.hide()
                }
            },
            sheetState = sheetState
        )
    }
}