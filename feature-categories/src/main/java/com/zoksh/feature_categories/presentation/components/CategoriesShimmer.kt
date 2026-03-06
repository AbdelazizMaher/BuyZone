package com.zoksh.feature_categories.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoksh.core_ui.theme.BuyZoneTheme
import com.zoksh.core_ui.util.shimmerEffect


@Composable
fun CategoriesRowShimmer(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(3) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .shimmerEffect()
            )
        }
    }
}


@Composable
fun DependentContentShimmer() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(4) {
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(32.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .shimmerEffect()
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            repeat(2) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(220.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .shimmerEffect()
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Categories Row Shimmer - Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Categories Row Shimmer - Dark")
@Composable
private fun CategoriesRowShimmerPreview() {
    BuyZoneTheme {
        Surface {
            CategoriesRowShimmer(modifier = Modifier.padding(16.dp))
        }
    }
}

@Preview(showBackground = true, name = "Dependent Content Shimmer - Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dependent Content Shimmer - Dark")
@Composable
private fun DependentContentShimmerPreview() {
    BuyZoneTheme {
        Surface {
            DependentContentShimmer()
        }
    }
}
