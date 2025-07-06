package com.example.textinputkitlibrary.main

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.textinputkitlibrary.core.base.BaseTextInput
import com.example.textinputkitlibrary.core.base.ListenerTextInput
import com.example.textinputkitlibrary.core.model.InputModel
import com.example.textinputkitlibrary.core.uitextfield.BaseTextFieldComposeView

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