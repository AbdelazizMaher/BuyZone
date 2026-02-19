package com.zoksh.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zoksh.core_ui.theme.BuyZoneTheme
import com.zoksh.feature_home.R
import com.zoksh.feature_home.presentation.model.TrendingUiModel

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: TrendingUiModel,
    onClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onClick(product.id) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Box {
                AsyncImage(
                    model = product.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.addidas_logo),
                    error = painterResource(R.drawable.addidas_logo),
                    modifier = Modifier
                        .height(160.dp)
                        .fillMaxWidth()
                )

                product.discountPercent?.let {
                    DiscountBadge(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp),
                        percent = it
                    )
                }

                IconButton(
                    onClick = { onFavoriteClick(product.id) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .size(28.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                            shape = RoundedCornerShape(18.dp)
                        )
                ) {
                    Icon(
                        painter = painterResource(
                            if (product.isFavorite) R.drawable.ic_favourite_filled
                            else R.drawable.ic_favourite
                        ),
                        contentDescription = null,
                        tint = if (product.isFavorite)
                            MaterialTheme.colorScheme.error
                        else
                            Color.Unspecified
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            Column(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.price,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    product.oldPrice?.let {
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall.copy(
                                textDecoration = TextDecoration.LineThrough
                            ),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, name = "Light Mode")
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ProductCardPreview() {
    val sampleProduct = TrendingUiModel(
        id = "1",
        name = "Adidas Ultraboost",
        image = "",
        price = "$180.00",
        isFavorite = false,
        discountPercent = null,
        oldPrice = null
    )

    val favoriteSaleProduct = TrendingUiModel(
        id = "2",
        name = "Nike Air Max Pro",
        image = "",
        price = "$150.00",
        isFavorite = true,
        discountPercent = 25,
        oldPrice = "$200.00"
    )

    BuyZoneTheme {
        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ProductCard(
                    modifier = Modifier.width(180.dp),
                    product = sampleProduct,
                    onClick = {},
                    onFavoriteClick = {}
                )
            }
            item {
                ProductCard(
                    modifier = Modifier.width(180.dp),
                    product = favoriteSaleProduct,
                    onClick = {},
                    onFavoriteClick = {}
                )
            }
        }
    }
}