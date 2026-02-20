package com.zoksh.feature_search.presentation.components.filter

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zoksh.core_ui.theme.BuyZoneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceRangeSelector(
    range: ClosedFloatingPointRange<Float>,
    currencySymbol: String,
    onRangeChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Price Range",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "$currencySymbol${range.start.toInt()} - $currencySymbol${range.endInclusive.toInt()}",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }

        RangeSlider(
            modifier = Modifier.padding(top = 8.dp),
            value = range,
            onValueChange = onRangeChange,
            valueRange = 0f..1000f,
            startThumb = {
                PriceSliderThumb()
            },
            endThumb = {
                PriceSliderThumb()
            },
            track = { rangeSliderState ->
                SliderDefaults.Track(
                    rangeSliderState = rangeSliderState,
                    modifier = Modifier.height(8.dp),
                    thumbTrackGapSize = 0.dp,
                    colors = SliderDefaults.colors(
                        activeTrackColor = MaterialTheme.colorScheme.primary,
                        inactiveTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                    )
                )
            }
        )
    }
}

@Composable
private fun PriceSliderThumb() {
    Surface(
        modifier = Modifier.size(22.dp),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(3.dp, MaterialTheme.colorScheme.primary),
        shadowElevation = 4.dp
    ) {}
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PriceRangeSelectorPreview() {
    BuyZoneTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            PriceRangeSelector(
                range = 420f..1000f,
                currencySymbol = "$",
                onRangeChange = {}
            )
        }
    }
}
