package com.uniandes.wakey.ui.screens.reminder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uniandes.wakey.R
import com.uniandes.wakey.ui.components.WakeyCard
import com.uniandes.wakey.ui.components.WakeyFAButton
import com.uniandes.wakey.ui.theme.WakeyWakeyTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersScreen() {

    var reminders by remember {
        mutableStateOf(emptyList<Reminder>())
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var showBottomSheet by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

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
                if (reminders.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        items(reminders.size) { index ->
                            ReminderItem(reminders[index])
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.size(96.dp),
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Add"
                        )
                        Spacer(Modifier.size(16.dp))
                        Text(
                            stringResource(R.string.no_reminders),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                WakeyFAButton(
                    onClick = { showBottomSheet = true },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                }
            }
        }
    )

    if (showBottomSheet) {
        ModalAddReminderBottomSheet(
            sheetState = sheetState,
            onAddReminder = { reminder ->
                reminders = reminders + reminder
                showBottomSheet = false
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Recordatorio agregado")
                }
            },
            onDismissRequest = {
                showBottomSheet = false
            }
        )
    }

}

@Composable
private fun ReminderItem(
    reminder: Reminder
) {
    WakeyCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = reminder.title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = reminder.date,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
            Checkbox(
                checked = false,
                onCheckedChange = {}
            )
        }
    }
}

@Composable
@Preview
fun PreviewRemindersScreen() {
    WakeyWakeyTheme {
        RemindersScreen()
    }
}

data class Reminder(val title: String, val date: String)
