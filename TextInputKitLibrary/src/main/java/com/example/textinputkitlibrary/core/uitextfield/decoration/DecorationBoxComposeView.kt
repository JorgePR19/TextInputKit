package com.example.textinputkitlibrary.core.uitextfield.decoration

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.textinputkitlibrary.core.base.TextFieldStatus
import com.example.textinputkitlibrary.utils.ui.DimensDp
import com.example.textinputkitlibrary.utils.ui.DimensDp.Dp16
import com.example.textinputkitlibrary.utils.ui.DimensDp.Dp2
import com.example.textinputkitlibrary.utils.ui.DimensDp.Dp46
import com.example.textinputkitlibrary.utils.ui.DimensDp.Dp8

@Composable
fun DecorationBoxComposeView(
    innerTextField: @Composable () -> Unit,
    value: String,
    placeholder: String = "",
    @DrawableRes endIcon: Int? = null,
    endIconAction: (() -> Unit)? = null,
    focusState: Boolean,
    textFieldStatus: TextFieldStatus,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .wrapContentHeight()
        ) {
            val (textContainer) = createRefs()

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(Dp8))
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(Dp46)
                    .drawBehind {
                        val strokeWidth = getUnderLineHeightBox(focusState, placeholder, value).toPx()
                        val y = size.height - strokeWidth / 2
                        drawLine(
                            color = getUnderLineBox(focusState, textFieldStatus),
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = strokeWidth
                        )
                    }
                    .constrainAs(textContainer) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = Dp8)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .padding(start = Dp16, top = Dp2, bottom = Dp2),
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (label.isNotEmpty()) Text(
                            label,
                            style = setLabelStyle(focusState, placeholder, value, textFieldStatus)
                        )
                        if (focusState || value.isNotEmpty() || placeholder.isNotEmpty()) {
                            if (value.isEmpty() && placeholder.isNotEmpty()) {
                                Text(
                                    text = placeholder,
                                    style = getPlaceHolderStyle(textFieldStatus)
                                )
                            } else {
                                Spacer(modifier = Modifier.weight(1f))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = Dp8)
                                ) {
                                    innerTextField()
                                }
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }

                    if (endIcon != null) {
                        IconButton(onClick = {
                            if (endIconAction != null) endIconAction()
                        }, modifier = Modifier.size(DimensDp.Dp24)) {
                            Image(
                                painter = painterResource(endIcon),
                                contentDescription = "",
                            )
                        }
                    }
                }
            }
        }
    }
}