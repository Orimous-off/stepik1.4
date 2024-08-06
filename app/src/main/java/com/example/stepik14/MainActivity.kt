package com.example.stepik14

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stepik14.ui.theme.Stepik14Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var deposit by remember { mutableStateOf("") }
            var percentage by remember { mutableStateOf(0f) }
            var percentageInt = percentage.toInt()
            var commission by remember {
                mutableStateOf(0.0)
            }
            var depositValue by remember {
                mutableStateOf(0.0)
            }
            depositValue = deposit.toDoubleOrNull() ?: 0.0
            commission = ((depositValue / 100.0) * percentageInt)

            Stepik14Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 20.dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp, 0.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                                ) {
                                    Text(text = "Депозит")
                                    TextField(
                                        value = deposit,
                                        onValueChange = { deposit = it },
                                        placeholder = { Text(text = "1 000 000")},
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Number,
                                            imeAction = ImeAction.Done
                                        ),
                                        maxLines = 1,
                                    )
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                                ) {
                                    Text(text = "${percentageInt}%")
                                    Column {
                                        Slider(
                                            value = percentage,
                                            onValueChange = {
                                                percentage = it
                                                percentageInt = percentage.toInt()
                                                            },
                                            valueRange = 0f..50f,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                        Text(
                                            modifier = Modifier.fillMaxWidth(),
                                            text = getColorName(percentageInt),
                                            color = getColors(percentageInt),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = "Комиссия")
                                    Text(text = "${commission} ₽")
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    Text(text = "Итог")
                                    var result by remember {
                                        mutableStateOf(0.0)
                                    }
                                    result = depositValue + commission
                                    Text(
                                        text = "$result ₽",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Text(text = "Made in \uD83C\uDDF7\uD83C\uDDFA by Orimous")
                        }
                    }
                }
            }
        }
    }
}

fun getColorName(percentage: Int): String {
    return when (percentage) {
        in 0..9 -> "Пенсионный"
        in 10..14 -> "Оптимальный"
        in 15..19 -> "Комфорт"
        in 20..24 -> "Бизнесмен"
        else -> "Максимум"
    }
}

@Composable
fun getColors(percentage: Int): androidx.compose.ui.graphics.Color {
    return when (percentage) {
        in 0..9 -> colorResource(id = R.color.pension)
        in 10..14 -> colorResource(id = R.color.optimal)
        in 15..19 -> colorResource(id = R.color.comfort)
        in 20..24 -> colorResource(id = R.color.businessman)
        else -> colorResource(id = R.color.maximum)
    }
}