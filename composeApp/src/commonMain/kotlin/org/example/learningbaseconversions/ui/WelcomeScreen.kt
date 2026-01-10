package org.example.learningbaseconversions.ui

// commonMain/ui/WelcomeScreen.kt

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.learningbaseconversions.Greeting


/**
 * Screen to Welcome the User
 * Welcome message
 * Introducest the program
 * Works with App.kt to navigate to NameScreen.
 */

@Composable
fun WelcomeScreen(
    onContinue: () -> Unit
) {
    val greeting = remember { Greeting() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(1f))

        // Greeting text (centered)
        Text(
            text = greeting.greet(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Main welcome text (centered)
        Text(
            text = "Welcome to the Base Conversion Learning Program\nby Stephan Kenmegne Kamdem.\n\n"+
                   "This program aims to help people understand how to convert from one base to another.\n\n\n"+
                   "It mostly focuses on base2 and base10 as root bases but you can find bases3,4,5,6,7,8,9 and 16\n\n\n\n"+
                   "More Conversion options are coming!",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        // Continue button at bottom-right
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = onContinue) {
                Text("Get Started!")
            }
        }
    }
}
