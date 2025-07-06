package com.example.textinputkitlibrary.main

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.example.textinputkitlibrary.core.base.ActionsListeners
import com.example.textinputkitlibrary.core.base.BaseTextInput
import com.example.textinputkitlibrary.core.base.ListenerTextInput
import com.example.textinputkitlibrary.core.model.InputModel
import com.example.textinputkitlibrary.core.uitextfield.BaseTextFieldComposeView
import com.example.textinputkitlibrary.utils.formatMoney

class MoneyTextFieldWhitOutDecimal(
    override var textInputModel: InputModel,
    override var modifier: Modifier
) : BaseTextInput() {


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
            when (it) {
                is ActionsListeners.OnValueChange -> {
                    if(it.text.text.all { it in textInputModel.properties.keyBoardPermit }){
                        var formattedText = it.text.text.replace("$", "").replace(",", "")
                        formattedText = formattedText.ifEmpty { "0" }
                        val newValueMoney = formattedText.toDouble().formatMoney()
                        if (formattedText.length in 0..textInputModel.properties.maxLength) {
                            listeners.onAction(
                                id = textInputModel.id,
                                action = ActionsListeners.OnValueChange(
                                    TextFieldValue(
                                        text = newValueMoney,
                                        selection = TextRange(newValueMoney.length)
                                    )
                                )
                            )
                        }
                    }
                }

                ActionsListeners.OnEndIconAction,
                is ActionsListeners.OnFocusChange,
                is ActionsListeners.OnKeyBoardAction -> listeners.onAction(
                    textInputModel.id,
                    action = it
                )
            }
        }
    }
}