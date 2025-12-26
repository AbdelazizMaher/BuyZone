package com.zoksh.feature_authentication.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.zoksh.feature_authentication.presentation.model.PasswordRequirement

@Composable
fun PasswordRequirementsSection(
    requirements: List<PasswordRequirement>
) {
    val colors = MaterialTheme.colorScheme

    Surface(
        shape = RoundedCornerShape(14.dp),
        color = colors.surfaceContainer,
        tonalElevation = 1.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Password must contain",
                style = MaterialTheme.typography.titleSmall,
                color = colors.onSurface
            )

            Spacer(modifier = Modifier.height(12.dp))

            requirements.forEach {
                RequirementRow(it)
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}


@Composable
fun RequirementRow(
    requirement: PasswordRequirement
) {
    val colors = MaterialTheme.colorScheme

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(
                    if (requirement.isSatisfied)
                        colors.primary
                    else
                        colors.onSurfaceVariant
                )
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = requirement.text,
            style = MaterialTheme.typography.bodySmall.copy(
                textDecoration =
                    if (requirement.isSatisfied)
                        TextDecoration.LineThrough
                    else
                        TextDecoration.None
            ),
            color =
                if (requirement.isSatisfied)
                    colors.onSurface
                else
                    colors.onSurfaceVariant
        )
    }
}



