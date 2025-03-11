package com.uniandes.wakey.ui.screens.alarm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MobileFriendly
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.uniandes.wakey.R
import com.uniandes.wakey.model.DialogButton
import com.uniandes.wakey.ui.components.AlertDialog


@Composable
fun RequestPermissionDialog(
    onDismiss: (acceptPermission: Boolean) -> Unit
){
    AlertDialog(
        onDismiss = {
            onDismiss(false)
        },
        positiveButton = stringResource(R.string.accept),
        negativeButton = stringResource(R.string.cancel),
        onButtonClickListener = {
            onDismiss(it == DialogButton.Positive)
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Outlined.MobileFriendly,
                contentDescription = "icon",
                tint = MaterialTheme.colorScheme.onBackground
            )
            Text(
                stringResource(R.string.need_location),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            Text(
                stringResource(R.string.location_rational_message),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}