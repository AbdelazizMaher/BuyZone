package com.zoksh.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import coil.compose.AsyncImage
import com.zoksh.feature_home.R
import com.zoksh.feature_home.presentation.model.HeaderUiModel

@Composable
fun HeaderSection(
    modifier: Modifier = Modifier,
    header: HeaderUiModel,
    onNotificationClick: () -> Unit,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = header.image,
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.avatar),
                error = painterResource(R.drawable.avatar),
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = header.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = header.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = onNotificationClick,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(48.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
            ) {
                BadgedBox(
                    badge = {
                        if (header.notificationCount > 0) {
                            Badge {
                                Text(
                                    text = header.notificationCount.toString(),
                                    color = Color.White
                                )
                            }
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.notification),
                        contentDescription = "Notifications",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
        HorizontalDivider(
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
        )
    }
}