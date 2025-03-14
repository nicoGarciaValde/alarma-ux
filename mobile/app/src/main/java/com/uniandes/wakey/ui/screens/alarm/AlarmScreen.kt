package com.uniandes.wakey.ui.screens.alarm

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.FloatingActionButton
import com.uniandes.wakey.ui.components.WakeyCard
import androidx.compose.material3.Switch

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
    var showAddAlarmBottomSheet by remember { mutableStateOf(false) }
    val editNormalSheetState = rememberModalBottomSheetState()
    val addAlarmSheetState = rememberModalBottomSheetState()
    var showVoiceAlarmPopup by remember { mutableStateOf(false) }

    Scaffold(
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
                            AlarmItem(alarms[index])
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
            alarm = Alarm(6, 10, true),
            sheetState = editNormalSheetState,
            onDismissRequest = {
                showEditNormalBottomSheet = false
            },
            onDeleteAlarm = {

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
                alarms = alarms + newAlarm
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
}
@Composable
private fun AlarmItem(
    alarm: Alarm
) {
    var isEnabled by remember { mutableStateOf(alarm.enabled) } // Estado interno del Switch

    WakeyCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
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
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
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

// Función para formatear la hora en 12 horas con AM/PM
private fun formatTime(hour: Int, minute: Int): String {
    val isAM = hour < 12
    val formattedHour = if (hour == 0) 12 else if (hour > 12) hour - 12 else hour
    val formattedMinute = minute.toString().padStart(2, '0')
    val amPm = if (isAM) "AM" else "PM"
    return "$formattedHour:$formattedMinute $amPm"
}