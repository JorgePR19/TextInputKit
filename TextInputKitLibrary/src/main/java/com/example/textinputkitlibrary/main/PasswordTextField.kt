package com.example.textinputkitlibrary.main

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import com.example.textinputkitlibrary.R
import com.example.textinputkitlibrary.core.base.ActionsListeners
import com.example.textinputkitlibrary.core.base.BaseTextInput
import com.example.textinputkitlibrary.core.base.ListenerTextInput
import com.example.textinputkitlibrary.core.model.InputModel
import com.example.textinputkitlibrary.core.uitextfield.BaseTextFieldComposeView
import com.example.textinputkitlibrary.utils.ui.PasswordTransformation

class PasswordTextField(
    override var textInputModel: InputModel,
    override var modifier: Modifier
) : BaseTextInput() {


    @Composable
    override fun RenderView(listeners: ListenerTextInput) {
        var showPass by rememberSaveable { mutableStateOf(false) }

        BaseTextFieldComposeView(
            inputModel = textInputModel,
            modifier = modifier,
            endIcon  = if (!showPass) R.drawable.eye_visible_icon else R.drawable.eye_invisible_icon,
            visualTransformation = if (showPass) VisualTransformation.None else PasswordTransformation(),
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = textInputModel.properties.keyboardType,
                imeAction = textInputModel.properties.imeAction
            )
        ) {
            when (it) {
                is ActionsListeners.OnValueChange -> {
                    if (it.text.text.all { it in textInputModel.properties.keyBoardPasswordPermit })
                        listeners.onAction(textInputModel.id, action = it)
                }
                ActionsListeners.OnEndIconAction -> {
                    showPass = !showPass
                }
                is ActionsListeners.OnFocusChange,
                is ActionsListeners.OnKeyBoardAction -> listeners.onAction(
                    textInputModel.id,
                    action = it
                )
            }
        }
    }
}