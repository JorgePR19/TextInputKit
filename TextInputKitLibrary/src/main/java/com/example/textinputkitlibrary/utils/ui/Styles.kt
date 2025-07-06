package com.example.textinputkitlibrary.utils.ui

import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.textinputkitlibrary.R
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
object Styles {
    private val robotoFontFamily = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Companion.Normal),
        Font(R.font.roboto_bold, FontWeight.Companion.Bold),
        Font(R.font.roboto_light, FontWeight.Companion.Light),
        Font(R.font.roboto_medium, FontWeight.Companion.Medium)
    )


    var roboto16Medium = TextStyle(
        fontSize = DimensSp.Sp16,
        lineHeight = DimensSp.Sp24,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Companion.Medium,
    )
    var roboto10Medium = TextStyle(
        fontSize = DimensSp.Sp10,
        lineHeight = DimensSp.Sp16,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Companion.Medium,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
    var roboto16Regular = TextStyle(
        fontSize = DimensSp.Sp16,
        fontWeight = FontWeight.Companion.Normal,
        fontFamily = robotoFontFamily,
        lineHeight = DimensSp.Sp24,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
    var roboto14Regular = TextStyle(
        fontSize = DimensSp.Sp14,
        fontWeight = FontWeight.Companion.Normal,
        fontFamily = robotoFontFamily,
        lineHeight = DimensSp.Sp16
    )

}