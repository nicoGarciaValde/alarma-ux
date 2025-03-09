package com.uniandes.wakey.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.uniandes.wakey.ui.theme.primary
import com.uniandes.wakey.ui.theme.surfaceContainerHigh

@Composable
fun WakeyFAButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = surfaceContainerHigh,
        contentColor = primary,
        elevation = elevation,
        interactionSource = interactionSource,
        content = content
    )
}