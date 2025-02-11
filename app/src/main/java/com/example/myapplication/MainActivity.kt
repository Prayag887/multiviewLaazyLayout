package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TabScreen()
                }
            }
        }
    }
}

@Composable
fun TabScreen() {
    val tabs = listOf("Syllabus", "Live Class", "Videos", "Notes", "Saved Contents")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val insets =
        WindowInsets.systemBars
            .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)

    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            edgePadding = 0.dp,
            divider = {},
            indicator = { tabPositions ->
                SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                )
            },
            modifier =
                Modifier
                    .padding(insets.asPaddingValues()),
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 14.sp,
                            color =
                                if (selectedTabIndex == index) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onSurface
                                },
                        )
                    },
                    unselectedContentColor = Color.Gray,
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                )
            }
        }

        // Display content based on selected tab
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            when (selectedTabIndex) {
                0 -> SyllabusScreen()
                1 -> LiveClassScreen()
                2 -> VideosScreen()
                3 -> MessagesScreen()
                4 -> NotificationsScreen()
                5 -> MoreScreen()
            }
        }
    }
}

@Composable
fun MessagesScreen() {
    Text("Messages Content", fontSize = 24.sp)
}

@Composable
fun NotificationsScreen() {
    Text("Notifications Content", fontSize = 24.sp)
}

@Composable
fun MoreScreen() {
    Text("More Content", fontSize = 24.sp)
}

@Preview(showBackground = true)
@Composable
fun TabScreenPreview() {
    MyApplicationTheme {
        TabScreen()
    }
}
