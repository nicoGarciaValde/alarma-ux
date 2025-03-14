package com.uniandes.wakey.ui.screens.alarm

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import com.uniandes.wakey.ui.components.WakeyCard
import androidx.compose.material3.Switch
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.uniandes.wakey.R
import com.uniandes.wakey.formatTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class Alarm(
    val hour: Int,
    val minute: Int,
    var enabled: Boolean,
    var repeat: Boolean = false,
    var days: List<String> = emptyList(),
    var isMorning: Boolean = false,
    var isWeatherActive: Boolean = false,
    var isCalendarActive: Boolean = false,
    var isNewsActive: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmListScreen() {
    var alarms by remember { mutableStateOf(emptyList<Alarm>()) }
    var showEditNormalBottomSheet by remember { mutableStateOf(false) }
    var showMorningBottomSheet by remember { mutableStateOf(false) }
    var showAddAlarmBottomSheet by remember { mutableStateOf(false) }
    val editNormalSheetState = rememberModalBottomSheetState()
    val addAlarmSheetState = rememberModalBottomSheetState()
    var showVoiceAlarmPopup by remember { mutableStateOf(false) }
    var selectedAlarm by remember { mutableStateOf<Alarm?>(null) }

    var showPermissionDialog by remember { mutableStateOf(false) }
    var showCalendarDialog by remember { mutableStateOf(false) }
    var showNewsDialog by remember { mutableStateOf(false) }
    val snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    val context = LocalContext.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier,
                hostState = snackbarHostState
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                if (alarms.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.size(96.dp),
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = "No tienes alarmas",
                            tint = Color.LightGray
                        )
                        Spacer(Modifier.size(16.dp))
                        Text(
                            text = "No tienes Alarmas",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.LightGray
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        items(alarms.size) { index ->
                            AlarmItem(
                                alarm = alarms[index],
                                onClick = {
                                    selectedAlarm = alarms[index]
                                    if (selectedAlarm!!.isMorning) {
                                        showMorningBottomSheet = true
                                    } else {
                                        showEditNormalBottomSheet = true
                                    }
                                }
                            )
                        }
                    }
                }
                // Botón flotante para agregar una alarma
                FloatingActionButton(
                    onClick = { showAddAlarmBottomSheet = true },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp),
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar alarma")
                }

                // Botón flotante para entrada de voz
                FloatingActionButton(
                    onClick = { showVoiceAlarmPopup = true },
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp),
                ) {
                    Icon(imageVector = Icons.Default.Mic, contentDescription = "Agregar con voz")
                }
            }
        }
    )

    if (showEditNormalBottomSheet) {
        ModalEditNormalSheet(
            alarm = selectedAlarm!!,
            sheetState = editNormalSheetState,
            onDismissRequest = { newAlarm ->
                showEditNormalBottomSheet = false
                alarms = alarms.map {
                    if (selectedAlarm == it) {
                        newAlarm
                    } else it
                }
            },
            onDeleteAlarm = {
                alarms = alarms.filter { it != selectedAlarm }
                coroutineScope.launch {
                    showEditNormalBottomSheet = false
                    sheetState.hide()
                    snackbarHostState.showSnackbar(message = context.getString(R.string.alarm_deleted))
                }
            }
        )
    }
    if (showAddAlarmBottomSheet) {
        ModalAddAlarmBottomSheet(
            alarm = Alarm(
                hour = 0,
                minute = 0,
                enabled = true
            ),
            sheetState = addAlarmSheetState,
            onDismissRequest = { showAddAlarmBottomSheet = false },
            onSaveAlarm = { newAlarm ->
                alarms = alarms + newAlarm.copy(isMorning = alarms.isEmpty())
                showAddAlarmBottomSheet = false
            }
        )
    }
    if (showVoiceAlarmPopup) {
        VoiceAlarmDialog(
            onDismiss = { showVoiceAlarmPopup = false },
            onSaveAlarm = { newAlarm ->
                alarms = alarms + newAlarm
                showVoiceAlarmPopup = false
            }
        )
    }

    if (showMorningBottomSheet) {
        ModalMorningAlarmSheet(
            modifier = Modifier
                .fillMaxHeight(0.90f),
            alarm = selectedAlarm!!,
            onDismissRequest = { newAlarm ->
                showMorningBottomSheet = false
                alarms = alarms.map {
                    if (selectedAlarm == it) {
                        newAlarm
                    } else it
                }
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
                alarms = alarms.filter { it != selectedAlarm }
                showMorningBottomSheet = false
                coroutineScope.launch {
                    sheetState.hide()
                    snackbarHostState.showSnackbar(message = context.getString(R.string.alarm_deleted))
                }
            },
            snackbarHostState = snackbarHostState
        )
    }

    if (showPermissionDialog) {
        RequestPermissionDialog(
            onDismiss = {
                showPermissionDialog = false
                selectedAlarm!!.isWeatherActive = it
                if (!selectedAlarm!!.isWeatherActive) {
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
                selectedAlarm!!.isCalendarActive = it
            }
        )
    }

    if (showNewsDialog) {
        NewsSelectionDialog(
            onDismiss = {
                showNewsDialog = false
                selectedAlarm!!.isNewsActive = it
            }
        )
    }
}

@Composable
private fun AlarmItem(
    alarm: Alarm,
    onClick: () -> Unit
) {
    var isEnabled by remember { mutableStateOf(alarm.enabled) } // Estado interno del Switch

    WakeyCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Obtener si es AM o PM
            val isAM = alarm.hour < 12
            val timeIcon = if (isAM) Icons.Default.Brightness7 else Icons.Default.DarkMode
            val iconDescription = if (isAM) "Morning Icon" else "Night Icon"
            // Fila superior: Hora y estado AM/PM + Ícono de sol
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = formatTime(alarm.hour, alarm.minute),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Icon(
                    imageVector = timeIcon,
                    contentDescription = "Sun Icon",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Fila intermedia: Switch para activar/desactivar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (alarm.isMorning) {
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(stringResource(R.string.morning))
                        }
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
                Switch(
                    checked = isEnabled,
                    onCheckedChange = { isEnabled = it }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
                ){
                // Días seleccionados
                Text(
                    text = alarm.days.joinToString(" ") { it.first().toString().uppercase() }, // Muestra solo iniciales
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium,
                    )

                Spacer(modifier = Modifier.height(8.dp))

                // Flecha desplegable
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand",
                )
            }

        }
    }
}