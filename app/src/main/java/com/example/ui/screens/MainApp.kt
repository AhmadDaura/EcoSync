package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SettingsInputComponent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Dashboard : Screen("dashboard", "Dashboard", Icons.Filled.Dashboard)
    object Usage : Screen("usage", "Usage", Icons.Filled.BarChart)
    object Automations : Screen("automations", "Automations", Icons.Filled.SettingsInputComponent)
    object Settings : Screen("settings", "Settings", Icons.Filled.Settings)
}

val bottomNavItems = listOf(
    Screen.Dashboard,
    Screen.Usage,
    Screen.Automations,
    Screen.Settings
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EcoSyncApp(onNavigateToBuyPower: () -> Unit = {}) {
    var selectedScreen by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize().background(androidx.compose.ui.graphics.Color.Transparent)) {

        Scaffold(
            containerColor = androidx.compose.ui.graphics.Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.surfaceVariant),
                                contentAlignment = Alignment.Center
                            ) {
                                AsyncImage(
                                    model = "https://lh3.googleusercontent.com/aida-public/AB6AXuBjxMOnCACfRz2yL9-MeupUG2jc3soaE1lMMF0X1j54ZanW6JW8cLcHhOkSV5VKMrKgIN89XvBG1mbIAVyEHKl5Wzc1lZeXPWa9ddm3Chlk7EjZk176UR8AMqBqeS47TWRODGtA3rKIKL8j0_lCIS7I3pZb19a0A74fZ19qfRdj6nfY3Ho04BaPY7xxMz1SQzqqhgfPhIEPYn_UKl20kCOGv5iOOoJ11OpRmYeJHywfmWBO8lsRpo1XoVE9dM6S8E3BIsH520HkDOmH",
                                    contentDescription = "Profile",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("EcoSync", fontWeight = FontWeight.Bold)
                        }
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = androidx.compose.ui.graphics.Color.Transparent
                    )
                )
            },
            bottomBar = {
                Box(
                    modifier = Modifier.background(com.example.ui.theme.GlassNavBg)
                ) {
                    androidx.compose.foundation.layout.Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(com.example.ui.theme.GlassNavBorder)
                            .align(Alignment.TopCenter)
                    )
                    NavigationBar(
                        containerColor = androidx.compose.ui.graphics.Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ) {
                        bottomNavItems.forEachIndexed { index, screen ->
                            NavigationBarItem(
                                selected = selectedScreen == index,
                                onClick = { selectedScreen = index },
                                icon = {
                                    Icon(
                                        imageVector = screen.icon,
                                        contentDescription = screen.title
                                    )
                                },
                                label = { Text(screen.title) }
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            when (selectedScreen) {
                0 -> DashboardScreen(Modifier.padding(innerPadding), onNavigateToBuyPower = onNavigateToBuyPower)
                1 -> UsageScreen(Modifier.padding(innerPadding))
                2 -> AutomationsScreen(Modifier.padding(innerPadding))
                3 -> SettingsScreen(Modifier.padding(innerPadding))
            }
        }
    }
}
