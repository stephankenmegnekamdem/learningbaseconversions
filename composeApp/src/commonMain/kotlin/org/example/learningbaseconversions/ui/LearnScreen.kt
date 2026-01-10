package org.example.learningbaseconversions.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

/**
 * Learn Screen for user to choose between:
 * Learning base 2 Conversions and works with App.kt to navigate to LearnBase2MenuScreen
 * Learning base 10 Conversions and works with App.kt to navigate to LearnBase10MenuScreen
 */
@Composable
fun LearnScreen(
    userName: String,
    onLearnBase2: () -> Unit,
    onLearnBase10: () -> Unit,
    onBack: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState) //  scrollable on small screens
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp) // consistent spacing
    ) {

        if (userName.isNotEmpty()) {
            Text(
                text = "Whats up! $userName!",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Text(
            // --- Introductory message ---
            text =  "So you have decided to learn, that's great keep up!,\n" +
                    "Since base 2 and base 10 are our basic common bases,\n\n" +
                    "Remember, base 2consists of 0's and 1's only like 11101101100\n\n" +
                    "Base 10 has digits(0-9) like 240167598\n\n" +
                    "Base 3 has digits(0-2) like 12022\n\n" +
                    "Base 4 has digits(0-3) like 231200\n\n" +
                    "Base 5 has digits(0-4) like 240312\n\n" +
                    "Base 6 has digits(0-5) like 2340105\n\n" +
                    "Base 7 has digits(0-6) like 240165523\n\n" +
                    "Base 8 has digits(0-7) like 240167\n\n" +
                    "Base 9 has digits(0-9) like 2401678\n\n" +
                    "Base 16 has digits(0-9 and A-F) like AF134\n\n\n" +
                    "Please choose among the following:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onLearnBase2) {
                Text("Learn Base 2 Conversions")
            }

            Button(onClick = onLearnBase10) {
                Text("Learn Base 10 Conversions")
            }
        }

        // Back button always accessible
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
