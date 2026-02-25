package com.zoksh.buyzone.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zoksh.buyzone.navigation.handlers.cart.CartNavHandler
import com.zoksh.feature_cart.presentation.navigation.CartDestination
import com.zoksh.feature_cart.presentation.screen.CartScreen
import com.zoksh.feature_cart.presentation.viewmodel.CartViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.cartGraph(
    navController: NavController,
    innerPadding: PaddingValues,
    onShowBottomBar: (Boolean) -> Unit
) {
    composable<CartDestination.Cart> {
        onShowBottomBar(true)
        val viewModel: CartViewModel = koinViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        
        CartNavHandler(navController, viewModel)
        
        CartScreen(
            state = state,
            onIntent = viewModel::handleIntent,
            innerPadding = innerPadding
        )
    }
}
