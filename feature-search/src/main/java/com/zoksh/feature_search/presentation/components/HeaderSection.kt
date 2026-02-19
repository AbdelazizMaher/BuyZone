package com.zoksh.feature_search.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoksh.core_ui.components.AppSearchBar
import com.zoksh.core_ui.theme.BuyZoneTheme
import com.zoksh.feature_search.R

@Composable
fun HeaderSection(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    onClearQuery: () -> Unit,
    onFilterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppSearchBar(
            value = value,
            onValueChange = onValueChange,
            hint = hint,
            onClearQuery = onClearQuery,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(
            onClick = onFilterClick,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(18.dp)
                )
        ) {
            Icon(
                painter = painterResource(R.drawable.filter),
                contentDescription = "Filter",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(18.dp),
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
private fun HeaderSectionPreview() {
    BuyZoneTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            HeaderSection(
                value = "",
                onValueChange = {},
                hint = "Search products...",
                onClearQuery = {},
                onFilterClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}
