package com.zoksh.feature_categories.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoksh.core_common.presentation.component.FilterChip
import com.zoksh.core_common.presentation.ui_state.UiState
import com.zoksh.core_ui.theme.BuyZoneTheme

@Composable
fun ProductTypeFilter(
    state: UiState<List<String>>,
    selectedType: String?,
    onTypeSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        is UiState.Success -> {
            LazyRow(
                modifier = modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.data) { type ->
                    val isSelected = type == selectedType
                    FilterChip(
                        label = type,
                        isSelected = isSelected,
                        onClick = { onTypeSelect(type) }
                    )
                }
            }
        }
        else -> {}
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ProductTypeFilterPreview() {
    BuyZoneTheme {
        Surface {
            ProductTypeFilter(
                state = UiState.Success(listOf("All", "Shoes", "Clothing", "Accessories")),
                selectedType = "Shoes",
                onTypeSelect = {}
            )
        }
    }
}
