package com.uniandes.wakey.ui.screens.alarm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uniandes.wakey.R
import com.uniandes.wakey.model.CheckItem
import com.uniandes.wakey.ui.components.CheckItemView
import com.uniandes.wakey.ui.theme.WakeyWakeyTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalMorningAlarmSheet(
    modifier: Modifier = Modifier,
    alarm: Alarm,
    onDismissRequest: (Alarm) -> Unit,
    sheetState: SheetState,
    onChangeWeather: (Boolean) -> Unit = {},
    onChangeCalendar: (Boolean) -> Unit = {},
    onChangeNews: (Boolean) -> Unit = {},
    onDeleteAlarm: () -> Unit = {},
    snackbarHostState: SnackbarHostState
) {

    val showErrorPermission = remember { mutableStateOf(false) }

    BaseAlarmModal(
        modifier = modifier,
        alarm,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        isMorningAlarm = true,
        snackbarHostState = snackbarHostState,
        content = {
            Column(
                verticalArrangement = Arrangement.spacedBy(22.dp)
            ) {
                CheckItemView(
                    item = CheckItem(
                        icon = Icons.Outlined.Cloud,
                        title = stringResource(R.string.weather),
                        isChecked = alarm.isWeatherActive
                    ),
                    onCheckChange = {
                        onChangeWeather(alarm.isWeatherActive.not())
                        if(!alarm.isWeatherActive) {
                            showErrorPermission.value = true
                        }
                    }
                )

                CheckItemView(
                    item = CheckItem(
                        icon = Icons.Outlined.DateRange,
                        title = stringResource(R.string.calendar),
                        isChecked = alarm.isCalendarActive
                    ),
                    onCheckChange = {
                        onChangeCalendar(alarm.isCalendarActive.not())
                    }
                )

                CheckItemView(
                    item = CheckItem(
                        icon = Icons.Outlined.Newspaper,
                        title = stringResource(R.string.news),
                        isChecked = alarm.isNewsActive
                    ),
                    onCheckChange = {
                        onChangeNews(alarm.isNewsActive.not())
                    }
                )

               Row(
                   Modifier.fillMaxWidth()
               ) {
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
fun ModalMorningAlarmSheetPreview() {
    WakeyWakeyTheme {

        var showPermissionDialog by remember { mutableStateOf(false) }
        var showCalendarDialog by remember { mutableStateOf(false) }
        var showNewsDialog by remember { mutableStateOf(false) }
        val snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
        val coroutineScope = rememberCoroutineScope()
        val sheetState = rememberModalBottomSheetState()
        val context = LocalContext.current

        val alarm by remember { mutableStateOf(Alarm(6, 10, true)) }

        ModalMorningAlarmSheet(
            modifier = Modifier
                .fillMaxHeight(0.90f),
            alarm = alarm,
            onDismissRequest = {

            },
            sheetState = sheetState,
            onChangeWeather = {
                showPermissionDialog = true
            },
            onChangeCalendar = {
                showCalendarDialog = true
            },
            onChangeNews = {
                showNewsDialog = true
            },
            onDeleteAlarm = {
                coroutineScope.launch {
                    sheetState.hide()
                    snackbarHostState.showSnackbar(message = context.getString(R.string.alarm_deleted))
                }
            },
            snackbarHostState = snackbarHostState
        )

        if (showPermissionDialog) {
            RequestPermissionDialog(
                onDismiss = {
                    showPermissionDialog = false
                    alarm.isWeatherActive = it
                    if (!alarm.isWeatherActive) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(message = context.getString(R.string.weather_permission_required))
                        }
                    }
                }
            )
        }

        if (showCalendarDialog) {
            CalendarPickerDialog(
                onDismiss = {
                    showCalendarDialog = false
                    alarm.isCalendarActive = it
                }
            )
        }

        if (showNewsDialog) {
            NewsSelectionDialog(
                onDismiss = {
                    showNewsDialog = false
                    alarm.isNewsActive = it
                }
            )
        }
    }
}