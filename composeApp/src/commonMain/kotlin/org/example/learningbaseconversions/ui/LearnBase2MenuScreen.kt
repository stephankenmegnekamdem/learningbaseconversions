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
 * Screen that shows Base 2 conversion options.
 * Works with App.kt to navigate to LearnBase2DetailScreen.
 */
@Composable
fun LearnBase2MenuScreen(
    userName: String,
    onConversionSelected: (String) -> Unit,
    onBack: () -> Unit
) {
    val scrollState = rememberScrollState() // ✅ scrollable

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // allow scrolling
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp) // consistent spacing
    ) {

        if (userName.isNotEmpty()) {
            Text(
                text = "Great! $userName, Let's Learn!",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Text(
            text = "Choose a conversion type:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = { onConversionSelected("Base2ToBase10") }) {
                    Text("Base 2 → Base 10")
                }
                Button(onClick = { onConversionSelected("Base2ToBase8") }) {
                    Text("Base 2 → Base 8")
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = { onConversionSelected("Base2ToBase16") }) {
                    Text("Base 2 → Base 16")
                }
                Button(onClick = { onConversionSelected("Base10ToBase2") }) {
                    Text("Base 10 → Base 2")
                }
            }
        }

        // --- Back button always accessible ---
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
