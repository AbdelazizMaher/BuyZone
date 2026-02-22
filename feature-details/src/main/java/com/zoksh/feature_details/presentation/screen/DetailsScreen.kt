package com.zoksh.feature_details.presentation.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.sp
import com.zoksh.core_common.R
import com.zoksh.core_common.presentation.component.ColorSelector
import com.zoksh.core_common.presentation.component.SizeSelector
import com.zoksh.core_common.presentation.model.ColorOption
import com.zoksh.core_ui.components.AppHeader
import com.zoksh.core_ui.components.PrimaryActionButton
import com.zoksh.core_ui.theme.BuyZoneTheme
import com.zoksh.feature_details.presentation.components.ProductImageHeader
import com.zoksh.feature_details.presentation.contract.DetailsContract
import com.zoksh.feature_details.presentation.model.DetailsUiModel

@Composable
fun DetailsScreen(
    state: DetailsContract.State,
    onIntent: (DetailsContract.Intent) -> Unit,
    innerPadding: PaddingValues
) {
    val scrollState = rememberScrollState()

    state.product?.let { product ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            AppHeader(
                title = product.category,
                onBackClick = { onIntent(DetailsContract.Intent.NavigateBack) },
                trailingDrawable = R.drawable.ic_cart,
                modifier = Modifier.fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
            ) {
                ProductImageHeader(
                    images = product.images,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    color = MaterialTheme.colorScheme.surfaceContainer
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = product.name,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = product.category,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(Modifier.height(24.dp))

                        Text(
                            text = product.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 20.sp
                        )

                        Spacer(Modifier.height(24.dp))

                        Text(
                            text = "Size :",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(12.dp))
                        SizeSelector(
                            options = product.sizes,
                            selectedSize = product.selectedSize,
                            onSelect = { onIntent(DetailsContract.Intent.SelectSize(it)) }
                        )

                        Spacer(Modifier.height(24.dp))

                        Text(
                            text = "Colors :",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(12.dp))
                        ColorSelector(
                            options = product.colors,
                            selectedId = product.selectedColorId,
                            onSelect = { onIntent(DetailsContract.Intent.SelectColor(it)) }
                        )

                        Spacer(Modifier.height(16.dp))
                    }
                }
            }

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceContainer,
                shadowElevation = 0.dp
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .padding(bottom = innerPadding.calculateBottomPadding())
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Price :",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = product.price,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Spacer(Modifier.weight(0.40f))
                    PrimaryActionButton(
                        text = "Add to cart",
                        onClick = { onIntent(DetailsContract.Intent.AddToCart) },
                        modifier = Modifier.weight(1f),
                        icon = R.drawable.ic_cart
                    )
                }
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
private fun DetailsScreenPreview() {
    val mockProduct = DetailsUiModel(
        id = "1",
        name = "Nike Air Max 270",
        category = "Men shoes",
        description = "The Nike Free Metcon 3 combines Nike Free flexibility around the forefoot with Metcon stability in the heel to help you get the most out of your training session.",
        price = "$290.00",
        images = listOf("https://picsum.photos/800/800"),
        sizes = listOf("6", "7", "8", "9", "10", "11"),
        colors = listOf(
            ColorOption("1", "Grey", Color(0xFF607D8B)),
            ColorOption("2", "Red", Color(0xFFFF7043)),
            ColorOption("3", "Yellow", Color(0xFFFFCA28))
        ),
        selectedSize = "8",
        selectedColorId = "1"
    )

    BuyZoneTheme {
        DetailsScreen(
            state = DetailsContract.State(product = mockProduct),
            onIntent = {},
            innerPadding = PaddingValues(0.dp)
        )
    }
}
