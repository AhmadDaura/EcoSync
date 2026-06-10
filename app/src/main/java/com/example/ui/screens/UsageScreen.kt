package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalLaundryService
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UsageScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Card(
            colors = CardDefaults.cardColors(containerColor = com.example.ui.theme.GlassCardBg),
            border = androidx.compose.foundation.BorderStroke(1.dp, com.example.ui.theme.GlassCardBorder),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text("WEEKLY INSIGHT", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Your efficiency rose by 12% since last Monday.",
                    style = MaterialTheme.typography.headlineLarge,
                    lineHeight = 40.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                    Column {
                        Text("Avg. Daily Energy", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("14.2 kWh", style = MaterialTheme.typography.titleLarge)
                    }
                    Column {
                        Text("Avg. Daily Water", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("420 L", style = MaterialTheme.typography.titleLarge)
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
            border = androidx.compose.foundation.BorderStroke(1.dp, com.example.ui.theme.GlassCardBorder),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Icon(Icons.Filled.Bolt, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(32.dp))
                    Icon(Icons.Filled.ArrowOutward, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f))
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text("-\$42.00", style = MaterialTheme.typography.displaySmall, color = MaterialTheme.colorScheme.onPrimary)
                Text("Estimated Savings this month", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f))
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
            Column {
                Text("Usage Trends", style = MaterialTheme.typography.headlineSmall)
                Text("7-Day Resource Comparison", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            colors = CardDefaults.cardColors(containerColor = com.example.ui.theme.GlassCardBg),
            border = androidx.compose.foundation.BorderStroke(1.dp, com.example.ui.theme.GlassCardBorder),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            modifier = Modifier.fillMaxWidth().height(240.dp)
        ) {
            PulseChart()
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text("Top Consumers", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        
        TopConsumerCard(
            title = "HVAC System",
            icon = Icons.Filled.AcUnit,
            value = "84.2",
            unit = "kWh",
            stat = "+4% vs LW",
            statColor = MaterialTheme.colorScheme.error,
            progress = 0.75f,
            barColor = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        TopConsumerCard(
            title = "Dishwasher",
            icon = Icons.Filled.LocalLaundryService,
            value = "156",
            unit = "Liters",
            stat = "-12% vs LW",
            statColor = MaterialTheme.colorScheme.primary,
            progress = 0.45f,
            barColor = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(16.dp))
        TopConsumerCard(
            title = "EV Charger",
            icon = Icons.Filled.DirectionsCar,
            value = "52.0",
            unit = "kWh",
            stat = "Stable",
            statColor = MaterialTheme.colorScheme.onSurfaceVariant,
            progress = 0.6f,
            barColor = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Card(
            colors = CardDefaults.cardColors(containerColor = com.example.ui.theme.GlassCardBg),
            border = androidx.compose.foundation.BorderStroke(1.dp, com.example.ui.theme.GlassCardBorder),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier.size(64.dp).clip(RoundedCornerShape(32.dp)).background(MaterialTheme.colorScheme.onSurface.copy(alpha=0.1f)), contentAlignment = Alignment.Center) {
                    Icon(Icons.Filled.Lightbulb, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(32.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Smart Optimization Tip", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Your hot water usage peaks between 7 AM and 8 AM. Shifting your dishwasher cycle to 11 PM could save you \$14.50/month on off-peak rates.",
                    style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onSurface, contentColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Text("Automate Now", modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun PulseChart() {
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    val primaryDim = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
    val secondaryDim = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)

    Canvas(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        val days = 7
        val barWidth = 12.dp.toPx()
        val spacing = size.width / days
        
        val energyValues = listOf(0.4f, 0.55f, 0.7f, 0.85f, 0.4f, 0.3f, 0.5f)
        val waterValues = listOf(0.6f, 0.45f, 0.8f, 0.4f, 0.5f, 0.35f, 0.4f)
        
        for (i in 0 until days) {
            val isToday = i == 3 // THU
            
            val eColor = if (isToday) primary else primaryDim
            val wColor = if (isToday) secondary else secondaryDim
            
            val eHeight = energyValues[i] * size.height
            val wHeight = waterValues[i] * size.height
            
            val xCenter = spacing * i + spacing / 2
            
            // Draw energy bar
            drawRoundRect(
                color = eColor,
                topLeft = Offset(xCenter - barWidth - 4f, size.height - eHeight),
                size = Size(barWidth, eHeight),
                cornerRadius = CornerRadius(barWidth/2, barWidth/2)
            )
            
            // Draw water bar
            drawRoundRect(
                color = wColor,
                topLeft = Offset(xCenter + 4f, size.height - wHeight),
                size = Size(barWidth, wHeight),
                cornerRadius = CornerRadius(barWidth/2, barWidth/2)
            )
        }
    }
}

@Composable
fun TopConsumerCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    unit: String,
    stat: String,
    statColor: Color,
    progress: Float,
    barColor: Color
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = com.example.ui.theme.GlassCardBg),
        border = androidx.compose.foundation.BorderStroke(1.dp, com.example.ui.theme.GlassCardBorder),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = barColor)
                }
                Text(stat, style = MaterialTheme.typography.labelSmall, color = statColor, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(title, style = MaterialTheme.typography.titleLarge)
            Row(verticalAlignment = Alignment.Bottom) {
                Text(value, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.width(8.dp))
                Text(unit, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(bottom = 6.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(barColor)
                )
            }
        }
    }
}
