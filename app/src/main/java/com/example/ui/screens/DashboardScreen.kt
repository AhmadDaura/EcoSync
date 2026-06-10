package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.network.GeminiApiService

@Composable
fun DashboardScreen(modifier: Modifier = Modifier, onNavigateToBuyPower: () -> Unit = {}) {
    val scrollState = rememberScrollState()
    var ecoTip by remember { mutableStateOf("Analyzing your home usage...") }

    LaunchedEffect(Unit) {
        val prompt = "Based on an energy usage of 12.4 kWh today with active appliances including HVAC and EV Charger, give me one short, practical, and highly specific tip to save energy or optimise usage. Keep it under 2 sentences. Don't use markdown formatting like asterisks or bold tags."
        ecoTip = GeminiApiService.generateContent(prompt)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EnergyDial()
        
        Spacer(modifier = Modifier.height(32.dp))

        // AI Eco-Tip
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f)),
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f)),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.EnergySavingsLeaf, contentDescription = null, tint = MaterialTheme.colorScheme.tertiary, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("AI Eco-Tip", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.tertiary)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(ecoTip, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        
        Spacer(modifier = Modifier.height(40.dp))

        // Prepaid Meter Card
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha=0.2f)),
            border = androidx.compose.foundation.BorderStroke(1.dp, com.example.ui.theme.GlassCardBorder),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Bolt, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Prepaid Balance", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                    }
                    Text("45.2 Units", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onSurface)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text("Updated 2 hours ago. Estimated 3 days remaining.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(24.dp))
                androidx.compose.material3.Button(
                    onClick = onNavigateToBuyPower,
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Top Up / Buy Power Units", fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))


        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Text("ENERGY EFFICIENCY", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("88%", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
                    Icon(Icons.Filled.TrendingUp, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Text("RESOURCE HEALTH", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline)
                Text("Optimal", style = MaterialTheme.typography.headlineMedium)
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Text("Active Appliances", style = MaterialTheme.typography.headlineSmall)
                Text("3 systems drawing power", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text("View All", fontWeight = FontWeight.Bold)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            items(activeAppliances) { appliance ->
                ApplianceCard(appliance)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = com.example.ui.theme.GlassCardBg),
            border = androidx.compose.foundation.BorderStroke(1.dp, com.example.ui.theme.GlassCardBorder),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text("Peak Saving Window", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Lower rates available between 11 PM and 5 AM. Schedule your EV charge now.", 
                    style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text("Schedule Now")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f)),
            border = androidx.compose.foundation.BorderStroke(1.dp, com.example.ui.theme.GlassCardBorder),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Icon(Icons.Filled.WaterDrop, contentDescription = null, tint = MaterialTheme.colorScheme.onSecondary, modifier = Modifier.size(32.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Text("Water Leak Alert", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSecondary)
                Spacer(modifier = Modifier.height(8.dp))
                Text("No abnormal flow detected in the main line today.", 
                    style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.8f))
                Spacer(modifier = Modifier.height(24.dp))
                Text("Secure", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onSecondary)
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun EnergyDial() {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val primaryTrack = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
    val secondaryTrack = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)

    Box(
        modifier = Modifier.size(280.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 14.dp.toPx()
            
            // Outer Track (Energy)
            drawArc(
                color = primaryTrack,
                startAngle = 135f,
                sweepAngle = 270f,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
            // Outer Progress
            drawArc(
                color = primaryColor,
                startAngle = 135f,
                sweepAngle = 190f,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
            
            // Inner Track (Water)
            val innerStrokeWidth = 12.dp.toPx()
            val padding = 28.dp.toPx()
            drawArc(
                color = secondaryTrack,
                startAngle = 135f,
                sweepAngle = 270f,
                useCenter = false,
                topLeft = androidx.compose.ui.geometry.Offset(padding, padding),
                size = androidx.compose.ui.geometry.Size(size.width - padding * 2, size.height - padding * 2),
                style = Stroke(width = innerStrokeWidth, cap = StrokeCap.Round)
            )
            // Inner Progress
            drawArc(
                color = secondaryColor,
                startAngle = 135f,
                sweepAngle = 210f,
                useCenter = false,
                topLeft = androidx.compose.ui.geometry.Offset(padding, padding),
                size = androidx.compose.ui.geometry.Size(size.width - padding * 2, size.height - padding * 2),
                style = Stroke(width = innerStrokeWidth, cap = StrokeCap.Round)
            )
        }
        
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "12.4",
                fontWeight = FontWeight.Bold,
                fontSize = 56.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "KWH TODAY",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.WaterDrop,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "240L",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

data class ApplianceInfo(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val valueLabel: String,
    val value: String,
    val progress: Float,
    val isPrimaryColor: Boolean = true
)

val activeAppliances = listOf(
    ApplianceInfo(
        title = "HVAC",
        icon = Icons.Filled.AcUnit,
        valueLabel = "Current Draw",
        value = "1.2 kW",
        progress = 0.6f,
        isPrimaryColor = true
    ),
    ApplianceInfo(
        title = "EV Charger",
        icon = Icons.Filled.DirectionsCar,
        valueLabel = "Current Draw",
        value = "7.4 kW",
        progress = 0.85f,
        isPrimaryColor = true
    )
)

@Composable
fun ApplianceCard(appliance: ApplianceInfo) {
    val iconTint = if (appliance.isPrimaryColor) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
    Card(
        colors = CardDefaults.cardColors(containerColor = com.example.ui.theme.GlassCardBg),
        border = androidx.compose.foundation.BorderStroke(1.dp, com.example.ui.theme.GlassCardBorder),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.width(240.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(iconTint.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(appliance.icon, contentDescription = null, tint = iconTint)
                }
                Switch(checked = true, onCheckedChange = {})
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(appliance.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(appliance.valueLabel, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(appliance.value, style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(appliance.progress)
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(iconTint)
                )
            }
        }
    }
}
