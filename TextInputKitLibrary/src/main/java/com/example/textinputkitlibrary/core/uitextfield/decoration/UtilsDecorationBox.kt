package com.example.textinputkitlibrary.core.uitextfield.decoration

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.example.textinputkitlibrary.core.base.TextFieldStatus
import com.example.textinputkitlibrary.utils.ui.Styles.roboto10Medium
import com.example.textinputkitlibrary.utils.ui.Styles.roboto16Medium
import com.example.textinputkitlibrary.utils.ui.Styles.roboto16Regular
import com.example.textinputkitlibrary.utils.ui.blueSecondaryColor
import com.example.textinputkitlibrary.utils.ui.blueSecondaryColorBox
import com.example.textinputkitlibrary.utils.ui.gray500Color
import com.example.textinputkitlibrary.utils.ui.gray500ColorBox
import com.example.textinputkitlibrary.utils.ui.gray700Color
import com.example.textinputkitlibrary.utils.ui.gray700ColorBox
import com.example.textinputkitlibrary.utils.ui.gray800Color
import com.example.textinputkitlibrary.utils.ui.green500Color
import com.example.textinputkitlibrary.utils.ui.green500ColorBox
import com.example.textinputkitlibrary.utils.ui.red800Color
import com.example.textinputkitlibrary.utils.ui.red800ColorBox
import com.example.textinputkitlibrary.utils.ui.yellow500Color
import com.example.textinputkitlibrary.utils.ui.yellow500ColorBox
import org.jetbrains.annotations.ApiStatus.Internal

private fun labelUnnFocus(texColor: Color) = roboto16Medium.copy(color = texColor)

private fun labelOnFocus(texColor: Color) = roboto10Medium.copy(color = texColor)

private fun placeHolderStyle(texColor: Color) = roboto16Regular.copy(texColor)

private fun setLabelStyleStatus(placeholder: String, value: String, texColor: Color): TextStyle {
    return if (placeholder.isNotEmpty() || value.isNotEmpty()) {
        labelOnFocus(texColor)
    } else {
        labelUnnFocus(texColor)
    }
}

@Internal
fun setLabelStyle(
    focusState: Boolean,
    placeholder: String,
    value: String,
    textFieldStatus: TextFieldStatus
): TextStyle {
    return when (focusState) {
        true -> {
            when (textFieldStatus) {
                TextFieldStatus.ERROR -> labelOnFocus(red800Color)
                TextFieldStatus.SUCCESS -> labelOnFocus(green500Color)
                TextFieldStatus.WARNING -> labelOnFocus(yellow500Color)
                else -> labelOnFocus(blueSecondaryColor)
            }

        }

        false -> {
            when (textFieldStatus) {
                TextFieldStatus.ENABLE -> {
                    if (placeholder.isNotEmpty() || value.isNotEmpty()) {
                        labelOnFocus(gray700Color)
                    } else {
                        labelUnnFocus(gray700Color)
                    }
                }

                TextFieldStatus.ERROR -> {
                    setLabelStyleStatus(placeholder, value, red800Color)
                }

                TextFieldStatus.SUCCESS -> {
                    setLabelStyleStatus(placeholder, value, green500Color)
                }

                TextFieldStatus.WARNING -> {
                    setLabelStyleStatus(placeholder, value, yellow500Color)
                }

                TextFieldStatus.DISABLE -> {
                    setLabelStyleStatus(placeholder, value, gray500Color)
                }

                else -> {
                    setLabelStyleStatus(placeholder, value, gray700Color)

                }
            }
        }
    }
}

@Internal
fun getUnderLineBox(focusState: Boolean, textFieldStatus: TextFieldStatus): Color {
    return when (focusState) {
        true -> {
            when (textFieldStatus) {
                TextFieldStatus.ERROR -> red800ColorBox
                TextFieldStatus.SUCCESS -> green500ColorBox
                TextFieldStatus.WARNING -> yellow500ColorBox
                else -> blueSecondaryColorBox
            }
        }

        false -> {
            when (textFieldStatus) {
                TextFieldStatus.ERROR -> red800ColorBox
                TextFieldStatus.SUCCESS -> green500ColorBox
                TextFieldStatus.WARNING -> yellow500ColorBox
                TextFieldStatus.DISABLE -> gray500ColorBox
                else -> gray700ColorBox
            }
        }
    }
}


@Internal
fun getUnderLineHeightBox(
    focusState: Boolean,
    placeholder: String,
    value: String,
): Dp {
    return when (focusState) {
        true -> {
            Dp(1.0F)
        }

        false -> {
            if (placeholder.isNotEmpty() || value.isNotEmpty()) {
                Dp(1.0F)
            } else {
                Dp(0.5F)
            }
        }
    }
}

@Internal
fun getPlaceHolderStyle(textFieldStatus: TextFieldStatus): TextStyle {
    return when (textFieldStatus) {
        TextFieldStatus.DISABLE -> placeHolderStyle(gray800Color)
        else -> placeHolderStyle(gray800Color)
    }
}