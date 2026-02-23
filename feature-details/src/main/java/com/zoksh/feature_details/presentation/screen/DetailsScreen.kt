package com.zoksh.feature_details.presentation.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.zoksh.core_common.R
import com.zoksh.core_common.presentation.model.ColorOption
import com.zoksh.core_ui.components.AppHeader
import com.zoksh.core_ui.theme.BuyZoneTheme
import com.zoksh.feature_details.presentation.components.DetailsBottomBar
import com.zoksh.feature_details.presentation.components.ProductImageHeader
import com.zoksh.feature_details.presentation.components.ProductInfoSection
import com.zoksh.feature_details.presentation.components.ProductSpecSection
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
                trailingDrawable = R.drawable.ic_cart_plus,
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
                    .background(MaterialTheme.colorScheme.background)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
            ) {
                ProductImageHeader(
                    images = product.images,
                    modifier = Modifier.fillMaxWidth()
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    ProductInfoSection(
                        name = product.name,
                        category = product.category,
                        description = product.description
                    )

                    Spacer(Modifier.height(32.dp))

                    ProductSpecSection(
                        sizes = product.sizes,
                        selectedSize = product.selectedSize,
                        onSizeSelect = { onIntent(DetailsContract.Intent.SelectSize(it)) },
                        colors = product.colors,
                        selectedColorId = product.selectedColorId,
                        onColorSelect = { onIntent(DetailsContract.Intent.SelectColor(it)) }
                    )
                    
                    Spacer(Modifier.height(32.dp))
                }
            }

            DetailsBottomBar(
                price = product.price,
                onAddToCartClick = { onIntent(DetailsContract.Intent.AddToCart) },
                bottomPadding = innerPadding.calculateBottomPadding()
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
