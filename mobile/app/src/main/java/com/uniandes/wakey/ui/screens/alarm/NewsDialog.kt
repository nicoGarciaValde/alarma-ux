package com.uniandes.wakey.ui.screens.alarm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.uniandes.wakey.R
import com.uniandes.wakey.model.NewsItem
import com.uniandes.wakey.ui.components.AlertDialog


@Composable
fun NewsSelectionDialog(
    onDismiss: (active: Boolean) -> Unit
) {

    val list = listOf(
        NewsItem("Deportes"),
        NewsItem("Tecnología"),
        NewsItem("Entretenimiento"),
        NewsItem("Economía"),
        NewsItem("Salud"),
        NewsItem("Ciencia"),
        NewsItem("Otros")
    )

    var listSelected by remember { mutableStateOf(listOf<NewsItem>()) }

    AlertDialog(
        onDismiss = {
            onDismiss(listSelected.isNotEmpty())
        },
        positiveButton = stringResource(R.string.accept),
        onButtonClickListener = {
            onDismiss(listSelected.isNotEmpty())
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                stringResource(R.string.news),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                stringResource(R.string.news_selection_message),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )

            for (item in list) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = item.title,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Checkbox(
                        checked = item in listSelected,
                        onCheckedChange = {
                            if (it) {
                                listSelected = listSelected.plus(item)
                            } else {
                                listSelected = listSelected.minus(item)
                            }
                        }
                    )
                }
            }
        }
    }
}