package com.zoksh.feature_search.presentation.components.filter

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zoksh.core_ui.theme.BuyZoneTheme

@Composable
fun ColorSelector(
    options: List<ColorOption>,
    selectedId: String?,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(options, key = { it.id }) { colorOption ->
            val isSelected = selectedId == colorOption.id
            
            val outerRingColor by animateColorAsState(
                targetValue = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) 
                             else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f),
                label = "outerRingColor"
            )
            val borderColor by animateColorAsState(
                targetValue = if (isSelected) MaterialTheme.colorScheme.primary 
                             else Color.Transparent,
                label = "borderColor"
            )
            val innerPadding by animateDpAsState(
                targetValue = if (isSelected) 6.dp else 8.dp,
                label = "innerPadding"
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { onSelect(colorOption.id) }
                    )
                    .padding(vertical = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(outerRingColor)
                        .border(
                            width = 1.5.dp,
                            color = borderColor,
                            shape = CircleShape
                        )
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(colorOption.color)
                            .fillMaxHeight()
                            .background(colorOption.color)
                            .matchParentSize()
                    )
                }
                
                Spacer(modifier = Modifier.height(6.dp))
                
                Text(
                    text = colorOption.name,
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isSelected) MaterialTheme.colorScheme.primary 
                            else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
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
private fun ColorSelectorPreview() {
    BuyZoneTheme {
        Surface {
            ColorSelector(
                options = listOf(
                    ColorOption("1", "Grey", Color(0xFF607D8B)),
                    ColorOption("2", "Red", Color(0xFFFF7043)),
                    ColorOption("3", "Yellow", Color(0xFFFFCA28)),
                    ColorOption("4", "Blue", Color(0xFF42A5F5)),
                    ColorOption("5", "Black", Color.Black),
                ),
                selectedId = "4",
                onSelect = {}
            )
        }
    }
}
