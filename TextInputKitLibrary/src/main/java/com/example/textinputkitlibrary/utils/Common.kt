package com.example.textinputkitlibrary.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import coil.compose.rememberAsyncImagePainter
import com.example.textinputkitlibrary.core.base.KeyBoardActions
import com.example.textinputkitlibrary.core.base.TextFieldStatus
import com.example.textinputkitlibrary.utils.ui.DimensDp
import com.example.textinputkitlibrary.utils.ui.Styles.roboto14Regular
import com.example.textinputkitlibrary.utils.ui.gray800Color
import com.example.textinputkitlibrary.utils.ui.gray900Color
import com.example.textinputkitlibrary.utils.ui.green500Color
import com.example.textinputkitlibrary.utils.ui.red800Color
import com.example.textinputkitlibrary.utils.ui.yellow500Color
import org.jetbrains.annotations.ApiStatus.Internal
import java.math.RoundingMode.DOWN
import java.text.DecimalFormat
import java.util.Locale

@Internal
fun Double.formatMoney(): String {
    val format = DecimalFormat.getCurrencyInstance(Locale.forLanguageTag("es-MX"))
    format.minimumFractionDigits = 0
    format.roundingMode = DOWN
    return format.format(this).trim()
}

private fun getHelperTextStyle(texColor: Color) = roboto14Regular.copy(color = texColor)

@Internal
@Composable
fun getStyleState(textFieldStatus: TextFieldStatus): TextStyle {
    return when (textFieldStatus) {
        TextFieldStatus.SUCCESS -> getHelperTextStyle(green500Color)
        TextFieldStatus.ERROR -> getHelperTextStyle(red800Color)
        TextFieldStatus.WARNING -> getHelperTextStyle(yellow500Color)
        else -> getHelperTextStyle(gray800Color)
    }
}


fun buildKeyboardActions(
    callback: (KeyBoardActions) -> Unit
): KeyboardActions {
    return KeyboardActions(
        onDone = { callback(KeyBoardActions.ON_DONE) },
        onNext = { callback(KeyBoardActions.ON_NEXT) },
        onGo = { callback(KeyBoardActions.ON_GO) },
        onSend = { callback(KeyBoardActions.ON_SEND) },
        onPrevious = { callback(KeyBoardActions.ON_PREVIOUS) },
        onSearch = { callback(KeyBoardActions.ON_SEARCH) }
    )
}


@Composable
fun StarImage(
    image: Any? = null,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    if (image != null) {
        Image(
            painter = rememberAsyncImagePainter(model = image),
            contentDescription = null,
            modifier = modifier.clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    } else {
        Box(
            modifier = modifier
                .clip(CircleShape)
                .background(Color.White)
                .border(width = DimensDp.Dp1, color = gray900Color, shape = CircleShape)
        )
    }
}