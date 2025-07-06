package com.example.textinputkitlibrary.core.uitextfield


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.example.textinputkitlibrary.core.base.ActionsListeners
import com.example.textinputkitlibrary.core.base.TextFieldStatus
import com.example.textinputkitlibrary.core.model.InputModel
import com.example.textinputkitlibrary.core.uitextfield.decoration.DecorationBoxComposeView
import com.example.textinputkitlibrary.utils.StarImage
import com.example.textinputkitlibrary.utils.buildKeyboardActions
import com.example.textinputkitlibrary.utils.getStyleState
import com.example.textinputkitlibrary.utils.ui.DimensDp
import com.example.textinputkitlibrary.utils.ui.DimensDp.Dp4
import com.example.textinputkitlibrary.utils.ui.DimensDp.Dp8
import com.example.textinputkitlibrary.utils.ui.DimensSp.Sp10
import com.example.textinputkitlibrary.utils.ui.Styles.roboto16Regular
import com.example.textinputkitlibrary.utils.ui.gray800Color
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
@Composable
fun BaseTextFieldComposeView(
    inputModel: InputModel,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onChangeStateUi: (ActionsListeners) -> Unit,
) {
    var focusState by rememberSaveable {
        mutableStateOf(false)
    }

    var currentLength by rememberSaveable {
        mutableIntStateOf(0)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            StarImage(image = inputModel.typesIcons.startIcon, Modifier.size(DimensDp.Dp27))
            BasicTextField(
                value = inputModel.textValue,
                modifier = modifier
                    .padding(start = DimensDp.Dp10)
                    .height(DimensDp.Dp47)
                    .weight(1f)
                    .onFocusChanged {
                        focusState = it.isFocused
                        onChangeStateUi(ActionsListeners.OnFocusChange(it.isFocused))
                    },
                enabled = inputModel.textFieldStatus != TextFieldStatus.DISABLE,
                onValueChange = {
                    currentLength = it.text.length
                    onChangeStateUi(
                        ActionsListeners.OnValueChange(
                            TextFieldValue(
                                text = it.text,
                                selection = it.selection
                            )
                        )
                    )
                },
                decorationBox = { innerTextField ->
                    DecorationBoxComposeView(
                        innerTextField,
                        value = inputModel.textValue.text,
                        placeholder = inputModel.placeHolder,
                        endIcon = inputModel.typesIcons.endIcon,
                        endIconAction = {
                            onChangeStateUi(ActionsListeners.OnEndIconAction)
                        },
                        focusState = focusState,
                        textFieldStatus = inputModel.textFieldStatus,
                        label = inputModel.label,
                    )
                },
                textStyle = roboto16Regular.copy(color = gray800Color),
                maxLines = 1,
                keyboardOptions = keyboardOptions,
                keyboardActions = buildKeyboardActions {
                    onChangeStateUi(ActionsListeners.OnKeyBoardAction(it))
                },
                visualTransformation = visualTransformation,
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            if (inputModel.helperText.isNotEmpty()) {
                Text(
                    inputModel.helperText,
                    modifier = Modifier.padding(top = Dp4, start = Dp8),
                    style = getStyleState(inputModel.textFieldStatus)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (inputModel.properties.countLength) {
                Text(
                    "$currentLength/${inputModel.properties.maxLength}",
                    modifier = Modifier.padding(top = Dp4, start = Dp8),
                    style = getStyleState(inputModel.textFieldStatus).copy(fontSize = Sp10)
                )
            }
        }
    }
}