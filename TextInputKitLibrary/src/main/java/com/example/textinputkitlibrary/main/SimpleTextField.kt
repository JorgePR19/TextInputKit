package com.example.textinputkitlibrary.main

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.textinputkitlibrary.R
import com.example.textinputkitlibrary.core.base.BaseTextInput
import com.example.textinputkitlibrary.core.base.ListenerTextInput
import com.example.textinputkitlibrary.core.model.InputModel
import com.example.textinputkitlibrary.core.model.TypesIcons
import com.example.textinputkitlibrary.core.uitextfield.BaseTextFieldComposeView
import com.example.textinputkitlibrary.utils.getIconByStatus

class SimpleTextField(
    override var textInputModel: InputModel,
    override var modifier: Modifier,
) :
    BaseTextInput() {

    @Composable
    override fun RenderView(listeners: ListenerTextInput) {
        BaseTextFieldComposeView(
            inputModel = textInputModel,
            modifier = modifier,
            endIcon = getIconByStatus(textInputModel.textFieldStatus, textInputModel.typesIcons),
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = textInputModel.properties.keyboardType,
                imeAction = textInputModel.properties.imeAction
            )
        ) {
            listeners.onAction(textInputModel.id, action = it)
        }
    }
}