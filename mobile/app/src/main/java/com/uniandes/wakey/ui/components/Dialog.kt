package com.uniandes.wakey.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.uniandes.wakey.model.DialogButton
import com.uniandes.wakey.ui.theme.surfaceContainerHigh

@Composable
fun AlertDialog(
    onDismiss: () -> Unit,
    positiveButton: String? = null,
    negativeButton: String? = null,
    onButtonClickListener: (DialogButton) -> Unit = {},
    content: @Composable () -> Unit
){
    Dialog(
        onDismiss
    ) {
        Box (
            Modifier
                .background(
                    surfaceContainerHigh,
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(24.dp)
        ) {
            Column {
                content()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    negativeButton?.let {
                        Button(
                            onClick = {
                                onButtonClickListener(DialogButton.Negative )
                            },
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = surfaceContainerHigh
                            )
                        ) {
                            Text(it)
                        }
                    }
                    positiveButton?.let {
                        Button(
                            onClick = {
                                onButtonClickListener(DialogButton.Positive)
                            },
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = surfaceContainerHigh
                            )
                        ) {
                            Text(it)
                        }
                    }
                }
            }
        }
    }
}