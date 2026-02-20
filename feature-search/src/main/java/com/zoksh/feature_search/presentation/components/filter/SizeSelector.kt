package com.zoksh.feature_search.presentation.components.filter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoksh.core_ui.theme.BuyZoneTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SizeSelector(
    options: List<String>,
    selectedSize: String?,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        options.forEach { size ->
            val isSelected = selectedSize == size
            Surface(
                onClick = { onSelect(size) },
                shape = RoundedCornerShape(8.dp),
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                modifier = Modifier.size(56.dp, 44.dp),
                border = if (!isSelected) BorderStroke(1.dp, Color.LightGray.copy(0.5f)) else null
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = size,
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Size Selector")
@Composable
private fun SizeSelectorPreview() {
    BuyZoneTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            SizeSelector(
                options = listOf("XS", "S", "M", "L", "XL", "XXL"),
                selectedSize = "L",
                onSelect = {}
            )
        }
    }
}
