package com.uniandes.wakey.ui.screens.alarm
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uniandes.wakey.model.DialogButton
import com.uniandes.wakey.ui.components.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import kotlinx.coroutines.delay
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color


@Composable
fun VoiceAlarmDialog(
    onSaveAlarm: (Alarm) -> Unit,
    onDismiss: (isRecordingSuccessful: Boolean) -> Unit
) {
    var recordingState by remember { mutableStateOf<String>("idle") }

    // Efecto de cambio de estado después de 5 segundos
    LaunchedEffect(recordingState) {
        if (recordingState == "recording") {
            delay(3000)
            recordingState = "success"
        }
    }

    AlertDialog(
        onDismiss = {
            onDismiss(recordingState == "success")
        },
        positiveButton = if (recordingState == "success") "Guardar" else "Grabar",
        negativeButton = "Cancelar",
        onButtonClickListener = { button ->
            when (button) {
                DialogButton.Positive -> {
                    if (recordingState == "idle") {
                        recordingState = "recording"
                    } else if (recordingState == "success") {
                        onSaveAlarm(
                            Alarm(
                                hour = 7,
                                minute = 30,
                                enabled = true,
                                repeat = true,
                                days = listOf("Lunes", "Miércoles", "Viernes"),
                                isMorning = true,
                                isWeatherActive = true,
                                isCalendarActive = false,
                                isNewsActive = true
                            )
                        )
                        onDismiss(true)
                    }
                }
                DialogButton.Negative -> onDismiss(false)
            }
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Alarma por voz",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall
            )

            // Caja para centrar el icono
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Mic,
                    contentDescription = "Micrófono",
                    modifier = Modifier.size(64.dp),
                    tint = when (recordingState) {
                        "recording" -> Color.Red
                        "success" -> Color.Green
                        else -> MaterialTheme.colorScheme.onSurface
                    }
                )
            }
        }
    }
}