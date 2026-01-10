package org.example.learningbaseconversions.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding

/**
 * Screen that shows Base 2 conversion options.
 * Works with App.kt to navigate to AnswerBase2ConversionScreen.
 */
@Composable
fun AnswerBase2Screen(
    userName: String,
    onConversionSelected: (String) -> Unit,
    onBack: () -> Unit
) {
    var step by remember { mutableStateOf(0) } // 0 = choose Initial/Final, 1 = show conversion options
    var showBase2Conversions by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing) // safe area
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Greeting / title
        item {
            Text(
                text = "Choose conversion type:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        item { Spacer(modifier = Modifier.height(12.dp)) }

        // Step 0: Initial / Final buttons
        if (step == 0) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            showBase2Conversions = true
                            step = 1
                        }
                    ) { Text("From Base 2 Conversion") }

                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            showBase2Conversions = false
                            step = 1
                        }
                    ) { Text("To Base 2 Conversion") }
                }
            }
        }

        // Step 1: Conversion buttons
        if (step == 1) {
            if (showBase2Conversions) {
                val buttons = listOf(
                    "Base 2 to Base 10" to "Base2ToBase10",
                    "Base 2 to Base 8" to "Base2ToBase8",
                    "Base 2 to Base 16" to "Base2ToBase16"
                )
                items(buttons) { (label, type) ->
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onConversionSelected(type) }
                    ) { Text(label) }
                }
            } else {
                item {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onConversionSelected("Base10ToBase2") }
                    ) { Text("Base 10 to Base 2") }
                }
            }
        }

        // Back button at the bottom
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                TextButton(onClick = onBack) {
                    Text("Back")
                }
            }
        }
    }
}
