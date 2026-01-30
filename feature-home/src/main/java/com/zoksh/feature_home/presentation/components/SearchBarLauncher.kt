package com.zoksh.feature_home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun SearchBarLauncher(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(modifier) {
        AppSearchBar(
            value = "",
            hint = "Search for products",
            onValueChange = {},
            requestFocus = false,
            onClearQuery = {}
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable(
                    indication = ripple(),
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onClick()
                }
        )
    }
}
