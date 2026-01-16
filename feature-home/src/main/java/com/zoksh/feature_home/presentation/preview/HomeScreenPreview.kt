package com.zoksh.feature_home.presentation.preview

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.zoksh.core_ui.theme.BuyZoneTheme
import com.zoksh.feature_home.presentation.screen.HomeScreen

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    BuyZoneTheme {
        HomeScreen(
            state = HomePreviewData.state,
            onIntent = {},
            innerPadding = PaddingValues()
        )
    }
}
