package com.uniandes.wakey.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uniandes.wakey.R
import com.uniandes.wakey.ui.screens.alarm.AlarmListScreen
import com.uniandes.wakey.ui.screens.reminder.RemindersScreen
import com.uniandes.wakey.ui.theme.WakeyWakeyTheme

data class BottomNavItem(
    @StringRes val titleId: Int
)

private val homeSections = listOf(
    BottomNavItem(
        titleId = R.string.alarms
    ),
    BottomNavItem(
        titleId = R.string.reminders
    )
)

@Composable
fun HomeNavigation() {
    var selectedItem by remember { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            TopBar(title = stringResource(homeSections[selectedItem].titleId))
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                Box(modifier = Modifier.weight(1f)) {
                    when (selectedItem) {
                        0 -> AlarmListScreen()
                        1 -> RemindersScreen()
                    }
                }
                BottomNavigationBar(
                    onSelectedItemClick = { item ->
                        selectedItem = item
                    }
                )
            }
        }
    )
}


@Composable
fun TopBar(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = title,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}


@Composable
fun BottomNavigationBar(
    onSelectedItemClick: (Int) -> Unit
) {

    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar {

        homeSections.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Text(
                        stringResource(id = item.titleId),
                        style = MaterialTheme.typography.titleSmall,
                    )
                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onSelectedItemClick(index)
                }
            )
        }
    }
}

@Composable
@Preview
fun HomeNavigationPreview() {
    WakeyWakeyTheme {
        HomeNavigation()
    }
}