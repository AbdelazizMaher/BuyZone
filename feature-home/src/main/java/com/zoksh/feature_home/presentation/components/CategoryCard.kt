package com.zoksh.feature_home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zoksh.feature_home.presentation.model.CategoryUiModel

@Composable
fun CategoryCard(
    category: CategoryUiModel,
    onCategoryClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onCategoryClick(category.title) }
    ) {
        Image(
            painter = painterResource(id = category.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = category.infoIcon),
                contentDescription = null,
                tint = Color.White,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = category.title,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}