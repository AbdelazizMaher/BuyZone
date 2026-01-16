package com.zoksh.feature_home.presentation.preview

import com.zoksh.feature_home.R
import com.zoksh.feature_home.presentation.contract.HomeContract
import com.zoksh.feature_home.presentation.model.BrandsUiModel
import com.zoksh.feature_home.presentation.model.CategoryUiModel
import com.zoksh.feature_home.presentation.model.HeaderUiModel
import com.zoksh.feature_home.presentation.model.PromosUiModel
import com.zoksh.feature_home.presentation.model.TrendingUiModel

object HomePreviewData {

    val header = HeaderUiModel(
        image = "https://i.pravatar.cc/150?img=3",
        message = "Good Morning 😊",
        name = "Abdelaziz",
        notificationCount = 3
    )

    val promos = listOf(
        PromosUiModel(
            image = "https://picsum.photos/800/300?1"
        ),
        PromosUiModel(
            image = "https://picsum.photos/800/300?2"
        ),
        PromosUiModel(
            image = "https://picsum.photos/800/300?3"
        )
    )

    val categories = listOf(
        CategoryUiModel(R.drawable.males_category, "Male", "👨"),
        CategoryUiModel(R.drawable.women_category,"Female", "👩"),
        CategoryUiModel(R.drawable.kids_category,"Kids", "👶"),
    )

    val brands = listOf(
        BrandsUiModel("https://logo.clearbit.com/apple.com", "Apple"),
        BrandsUiModel("https://logo.clearbit.com/samsung.com", "Samsung"),
        BrandsUiModel("https://logo.clearbit.com/sony.com", "Sony"),
        BrandsUiModel("https://logo.clearbit.com/nike.com", "Nike"),
        BrandsUiModel("https://logo.clearbit.com/adidas.com", "Adidas"),
        BrandsUiModel("https://logo.clearbit.com/mi.com", "Xiaomi")
    )

    val trending = listOf(
        TrendingUiModel(
            name = "iPhone 15 Pro",
            image = "https://picsum.photos/300/300?1",
            price = "$999",
            oldPrice = "$1099",
            discountPercent = 5,
            isFavorite = false
        ),
        TrendingUiModel(
            name = "Galaxy S24",
            image = "https://picsum.photos/300/300?2",
            price = "$899",
            oldPrice = "$1099",
            discountPercent = 5,
            isFavorite = true
        ),
        TrendingUiModel(
            name = "iPhone 17 Pro",
            image = "https://picsum.photos/300/300?1",
            price = "$999",
            oldPrice = "$1099",
            discountPercent = 5,
            isFavorite = false
        ),
        TrendingUiModel(
            name = "Galaxy S20",
            image = "https://picsum.photos/300/300?2",
            price = "$899",
            oldPrice = "$1099",
            discountPercent = 5,
            isFavorite = true
        )

    )

    val state = HomeContract.State(
        header = header,
        promos = promos,
        categories = categories,
        brands = brands,
        trending = trending
    )
}
