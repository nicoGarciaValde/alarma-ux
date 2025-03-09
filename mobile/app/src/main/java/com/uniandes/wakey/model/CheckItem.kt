package com.uniandes.wakey.model

import androidx.compose.ui.graphics.vector.ImageVector

data class CheckItem(
    val icon: ImageVector,
    val title: String,
    var isChecked: Boolean
)