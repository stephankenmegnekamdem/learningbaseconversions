package org.example.learningbaseconversions.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Screen that shows Base 10 conversion options.
 * Works with App.kt to navigate to LearnBase10DetailScreen.
 */
@Composable
fun LearnBase10MenuScreen(
    onConversionSelected: (fromBase: Int, toBase: Int) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text =  "Perfect choice. Lets get some knowledge\n" +
                    "Select Conversion",
            style = MaterialTheme.typography.titleMedium
        )

        val conversions = listOf(
            3 to 10, 10 to 3, 4 to 10, 10 to 4, 5 to 10, 10 to 5,
            6 to 10, 10 to 6, 7 to 10, 10 to 7, 8 to 10, 10 to 8,
            9 to 10, 10 to 9, 16 to 10, 10 to 16
        )

        for (i in conversions.indices step 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val left = conversions[i]
                Button(
                    onClick = { onConversionSelected(left.first, left.second) }
                ) {
                    Text("${left.first} → ${left.second}")
                }

                if (i + 1 < conversions.size) {
                    val right = conversions[i + 1]
                    Button(
                        onClick = { onConversionSelected(right.first, right.second) }
                    ) {
                        Text("${right.first} → ${right.second}")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back")
        }
    }
}
