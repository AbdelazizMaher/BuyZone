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
            id = "1",
            image = "https://picsum.photos/800/300?1"
        ),
        PromosUiModel(
            id = "2",
            image = "https://picsum.photos/800/300?2"
        ),
        PromosUiModel(
            id = "3",
            image = "https://picsum.photos/800/300?3"
        )
    )

    val categories = listOf(
        CategoryUiModel(id = "1",R.drawable.males_category, "Male", "👨"),
        CategoryUiModel(id = "2",R.drawable.women_category,"Female", "👩"),
        CategoryUiModel(id = "3",R.drawable.kids_category,"Kids", "👶"),
    )

    val brands = listOf(
        BrandsUiModel(id = "1","https://logo.clearbit.com/apple.com", "Apple"),
        BrandsUiModel(id = "2","https://logo.clearbit.com/samsung.com", "Samsung"),
        BrandsUiModel(id = "3","https://logo.clearbit.com/sony.com", "Sony"),
        BrandsUiModel(id = "4","https://logo.clearbit.com/nike.com", "Nike"),
        BrandsUiModel(id = "5","https://logo.clearbit.com/adidas.com", "Adidas"),
        BrandsUiModel(id = "6","https://logo.clearbit.com/mi.com", "Xiaomi")
    )

    val trending = listOf(
        TrendingUiModel(
            id = "1",
            name = "iPhone 15 Pro",
            image = "https://picsum.photos/300/300?1",
            price = "$999",
            oldPrice = "$1099",
            discountPercent = 5,
            isFavorite = false
        ),
        TrendingUiModel(
            id = "2",
            name = "Galaxy S24",
            image = "https://picsum.photos/300/300?2",
            price = "$899",
            oldPrice = "$1099",
            discountPercent = 5,
            isFavorite = true
        ),
        TrendingUiModel(
            id = "3",
            name = "iPhone 17 Pro",
            image = "https://picsum.photos/300/300?1",
            price = "$999",
            oldPrice = "$1099",
            discountPercent = 5,
            isFavorite = false
        ),
        TrendingUiModel(
            id = "4",
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
