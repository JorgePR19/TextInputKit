package com.example.textinputkitlibrary.core.base


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.textinputkitlibrary.core.model.InputModel
import org.jetbrains.annotations.ApiStatus.Internal

@Internal
abstract class BaseTextInput {
    abstract var textInputModel: InputModel
    abstract var modifier: Modifier

    @Composable
    abstract fun RenderView(listeners: ListenerTextInput)
}