package com.uniandes.wakey.ui.screens.alarm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.uniandes.wakey.R
import com.uniandes.wakey.ui.components.DaysSelection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseAlarmModal(
    modifier: Modifier = Modifier,
    alarm: Alarm,
    onDismissRequest: (Alarm) -> Unit,
    sheetState: SheetState,
    isMorningAlarm: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    var repeat by remember { mutableStateOf(alarm.repeat) }
    var days by remember { mutableStateOf(alarm.days.toSet()) }

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
        modifier = modifier,
        content = {
           Scaffold(
               snackbarHost = {
                   SnackbarHost(
                       modifier = Modifier,
                       hostState = snackbarHostState
                   )
               },
               content = {
                   Column(
                       Modifier.padding(it).padding(
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

                       if (isMorningAlarm) {
                           AssistChip(
                               onClick = {},
                               label = {
                                   Text(stringResource(R.string.morning))
                               }
                           )
                           Spacer(modifier = Modifier.height(16.dp))
                       }

                       HorizontalDivider()

                       Spacer(modifier = Modifier.height(16.dp))
                       content()
                   }
               }
           )
        }
    )
}