package com.example.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MeshBackground() {
    androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
        val maxR = size.maxDimension * 0.7f
        drawRect(
            brush = androidx.compose.ui.graphics.Brush.radialGradient(
                colors = listOf(com.example.ui.theme.MeshColor1.copy(alpha = 0.6f), androidx.compose.ui.graphics.Color.Transparent),
                center = androidx.compose.ui.geometry.Offset(0f, 0f),
                radius = maxR
            )
        )
        drawRect(
            brush = androidx.compose.ui.graphics.Brush.radialGradient(
                colors = listOf(com.example.ui.theme.MeshColor2.copy(alpha = 0.6f), androidx.compose.ui.graphics.Color.Transparent),
                center = androidx.compose.ui.geometry.Offset(size.width, 0f),
                radius = maxR
            )
        )
        drawRect(
            brush = androidx.compose.ui.graphics.Brush.radialGradient(
                colors = listOf(com.example.ui.theme.MeshColor3.copy(alpha = 0.6f), androidx.compose.ui.graphics.Color.Transparent),
                center = androidx.compose.ui.geometry.Offset(size.width, size.height),
                radius = maxR
            )
        )
        drawRect(
            brush = androidx.compose.ui.graphics.Brush.radialGradient(
                colors = listOf(com.example.ui.theme.MeshColor4.copy(alpha = 0.6f), androidx.compose.ui.graphics.Color.Transparent),
                center = androidx.compose.ui.geometry.Offset(0f, size.height),
                radius = maxR
            )
        )
    }
}
