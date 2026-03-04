package com.zoksh.feature_details.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.zoksh.core_ui.util.shimmerEffect


@Composable
fun DetailsShimmer(
    modifier: Modifier = Modifier,
    topPadding: androidx.compose.ui.unit.Dp = 0.dp
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = topPadding)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .shimmerEffect()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(28.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()
            )
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()
            )

            Spacer(Modifier.height(32.dp))

            repeat(3) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(14.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmerEffect()
                )
                Spacer(Modifier.height(8.dp))
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(14.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()
            )

            Spacer(Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(20.dp)
                    .shimmerEffect()
            )
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                repeat(4) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .shimmerEffect()
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(20.dp)
                    .shimmerEffect()
            )
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .shimmerEffect()
                    )
                }
            }
        }
    }
}
