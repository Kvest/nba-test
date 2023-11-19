package com.kvest.nba.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.kvest.nba.ui.navigation.NBANavHost
import com.kvest.nba.ui.theme.NBATestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NBATestTheme {
                val navController = rememberNavController()

                NBANavHost(
                    navController = navController,
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}