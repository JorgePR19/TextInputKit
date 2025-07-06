package com.example.textinputkitlibrary.core.base

import androidx.compose.ui.text.input.TextFieldValue

sealed class ActionsListeners {
    data class OnValueChange(val text: TextFieldValue) : ActionsListeners()
    data class OnFocusChange(val onFocus: Boolean) : ActionsListeners()
    data class OnKeyBoardAction(val keyboardActions: KeyBoardActions) : ActionsListeners()
    data object OnEndIconAction : ActionsListeners()
}

enum class TextFieldStatus {
    ENABLE, DISABLE, ERROR, SUCCESS, WARNING
}


enum class KeyBoardActions {
    ON_DONE, ON_NEXT, ON_GO, ON_SEND, ON_PREVIOUS, ON_SEARCH
}