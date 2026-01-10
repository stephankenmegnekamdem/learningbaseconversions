package org.example.learningbaseconversions.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding

/**
 * Answer Screen for user to choose between:
 * Getting answer of base 2 Conversions and works with App.kt to navigate to AnswerBase2Screen
 * Getting answer of  base 10 Conversions and works with App.kt to navigate to AnswerBase10Screen
 */
@Composable
fun AnswerScreen(
    userName: String,
    onAnswerBase2: () -> Unit,
    onAnswerBase10: () -> Unit,
    onBack: () -> Unit
) {
    // Add vertical scroll in case content overflows
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing) // ensures text/buttons are below notch
            .verticalScroll(rememberScrollState()) // scrollable
            .padding(horizontal = 16.dp, vertical = 24.dp), // add extra vertical padding
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(8.dp))

        // Greeting
        if (userName.isNotEmpty()) {
            Text(
                text = "Still there $userName!",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp) // extra spacing below greeting
            )
        }

        // Instruction text
        Text(
            text =  "Smart choice so you just need an answer ok Let's go,\n" +
                    "Since base 2 and base 10 are our basic common bases,\n" +
                    "please choose among the following options:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp) // spacing below instruction
        )

        // Buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onAnswerBase2) {
                Text("Answer Base 2 Conversions")
            }

            Button(onClick = onAnswerBase10) {
                Text("Answer Base 10 Conversions")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Back button
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
