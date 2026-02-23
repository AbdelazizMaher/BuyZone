package com.zoksh.feature_details.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zoksh.core_common.presentation.component.ColorSelector
import com.zoksh.core_common.presentation.component.SizeSelector
import com.zoksh.core_common.presentation.model.ColorOption

@Composable
fun ProductSpecSection(
    sizes: List<String>,
    selectedSize: String?,
    onSizeSelect: (String) -> Unit,
    colors: List<ColorOption>,
    selectedColorId: String?,
    onColorSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Size :",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))
        SizeSelector(
            options = sizes,
            selectedSize = selectedSize,
            onSelect = onSizeSelect
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Colors :",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))
        ColorSelector(
            options = colors,
            selectedId = selectedColorId,
            onSelect = onColorSelect
        )
    }
}
