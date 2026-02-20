package com.zoksh.feature_search.presentation.components.filter

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoksh.core_ui.theme.BuyZoneTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MultiSelectChips(
    options: List<String>,
    selectedOptions: Set<String>,
    onOptionClick: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        options.forEach { option ->
            val isSelected = selectedOptions.contains(option)
            FilterChip(
                label = option,
                isSelected = isSelected,
                onClick = { onOptionClick(option) }
            )
        }
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun MultiSelectChipsPreview() {
    BuyZoneTheme {
        Surface {
            MultiSelectChips(
                options = listOf("Electronics", "Fashion", "Home & Living", "Sports", "Beauty", "Books"),
                selectedOptions = setOf("Fashion", "Beauty"),
                onOptionClick = {}
            )
        }
    }
}
