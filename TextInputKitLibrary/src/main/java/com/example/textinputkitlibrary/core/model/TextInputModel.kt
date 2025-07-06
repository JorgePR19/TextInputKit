package com.example.textinputkitlibrary.core.model

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.example.textinputkitlibrary.core.base.TextFieldStatus

data class InputModel(
    val id: String,
    val textFieldStatus: TextFieldStatus = TextFieldStatus.ENABLE,
    val textValue: TextFieldValue,
    val placeHolder: String = "",
    val typesIcons: TypesIcons = TypesIcons(),
    val helperText: String = "",
    val label: String = "",
    val properties: InputProperties = InputProperties()
)

data class InputProperties(
    val maxLength: Int = 0,
    val containsRegex: String = "",
    val keyBoardPermit: String = "",
    val keyboardType: KeyboardType = KeyboardType.Text,
    val imeAction: ImeAction = ImeAction.Done,
    val countLength:Boolean = false,
)

data class TypesIcons(
    val startIcon: Any? = null,
    val endIcon: Int? = null,
    val successIcon: Int? = null,
    val errorIcon: Int? = null,
    val warning: Int? = null,
)