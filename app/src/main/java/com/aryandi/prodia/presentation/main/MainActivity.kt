package com.aryandi.prodia.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aryandi.prodia.ui.theme.ProdiaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProdiaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.fillMaxSize()) {
                        Greeting(
                            time = "Morning",
                            name = "User",
                            modifier = Modifier.padding(innerPadding)
                        )
                        NewsScreen(onArticleClick = { articleUrl ->
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(time: String, name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Good $time, $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProdiaTheme {
        Greeting("Morning","User")
    }
}