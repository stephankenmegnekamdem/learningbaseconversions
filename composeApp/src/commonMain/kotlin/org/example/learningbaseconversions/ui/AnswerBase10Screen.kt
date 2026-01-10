package org.example.learningbaseconversions.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.systemBarsPadding

/**
 * Screen that shows Base 10 conversion options.
 * Works with App.kt to navigate to AnswerBase10ConversionScreen.
 */
@Composable
fun AnswerBase10Screen(
    userName: String,
    onConversionSelected: (String) -> Unit,
    onBack: () -> Unit
) {
    val scrollState = rememberScrollState()

    val answerinitialConversions = listOf(
        "Base10 → Base3", "Base10 → Base4", "Base10 → Base5", "Base10 → Base6",
        "Base10 → Base7", "Base10 → Base8", "Base10 → Base9", "Base10 → Base16"
    )
    val answerfinalConversions = listOf(
        "Base3 → Base10", "Base4 → Base10", "Base5 → Base10", "Base6 → Base10",
        "Base7 → Base10", "Base8 → Base10", "Base9 → Base10", "Base16 → Base10"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Hello $userName, choose a conversion:",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- Show all initial conversions ---
        Text("From Base 10:", fontSize = 18.sp)
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            answerinitialConversions.forEach { conv ->
                Button(
                    onClick = { onConversionSelected(conv) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Answer: $conv")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- Show all final conversions ---
        Text("To Base 10:", fontSize = 18.sp)
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            answerfinalConversions.forEach { conv ->
                Button(
                    onClick = { onConversionSelected(conv) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Answer: $conv")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Back button
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back")
        }
    }
}
