package com.alextos.plankingtimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alextos.plankingtimer.ui.theme.PlankingTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlankingTimerTheme {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PlankingTimerTheme {

    }
}