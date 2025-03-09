package com.uniandes.wakey.ui.screens.alarm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

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
    var showEditNormalBottomSheet by remember { mutableStateOf(false) }
    val editNormalSheetState = rememberModalBottomSheetState()
    Scaffold(
        content = {
            Box(
                modifier = Modifier.padding(it)
            ) {

                Button(onClick = {
                    showEditNormalBottomSheet = true
                }) {
                    Text("editar alarma")
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
            }
        )
    }
}