package com.uniandes.wakey.model

sealed class DialogButton {
    data object Positive : DialogButton()
    data object Negative : DialogButton()
}
