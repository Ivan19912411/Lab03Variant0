package com.example.lab03variant0

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview // Добавлен импорт для Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab03variant0.ui.theme.Lab03Variant0Theme
import kotlin.math.cbrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab03Variant0Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LabScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LabScreen(modifier: Modifier = Modifier) {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var num3 by remember { mutableStateOf("") }
    var symbol by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Вариант 0. Введите данные",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Первое число") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Второе число") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = num3,
            onValueChange = { num3 = it },
            label = { Text("Третье число") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = symbol,
            onValueChange = { symbol = it },
            label = { Text("Символ (a или g)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val x1 = num1.toDoubleOrNull()
            val x2 = num2.toDoubleOrNull()
            val x3 = num3.toDoubleOrNull()
            val sym = symbol.trim().lowercase()

            if (x1 == null || x2 == null || x3 == null) {
                resultText = "Ошибка: введите корректные числа"
            } else {
                resultText = when (sym) {
                    "a" -> {
                        val avg = (x1 + x2 + x3) / 3.0
                        "Среднее арифметическое: $avg"
                    }
                    "g" -> {
                        val product = x1 * x2 * x3
                        if (product < 0) {
                            "Ошибка: среднее геометрическое только для неотрицательных чисел"
                        } else {
                            val geom = cbrt(product)
                            "Среднее геометрическое: $geom"
                        }
                    }
                    else -> "Ошибка: неизвестный символ. Введите a или g"
                }
            }
        }) {
            Text("OK")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = resultText,
            fontSize = 18.sp
        )
    }
}

// --- ИЗМЕНЕНИЕ НА ШАГЕ 6: Добавлена функция предпросмотра ---
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LabScreenPreview() {
    Lab03Variant0Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            LabScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}
// ----------------------------------------------------------
