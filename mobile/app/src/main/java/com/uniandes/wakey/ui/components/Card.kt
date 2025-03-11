package com.uniandes.wakey.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.uniandes.wakey.ui.theme.surfaceContainerLow


@Composable
fun WakeyCard(
    modifier: Modifier = Modifier,
    border: BorderStroke? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(),
        border = border,
        content = content,
        colors = CardDefaults.cardColors(
            containerColor = surfaceContainerLow
        )
    )
}