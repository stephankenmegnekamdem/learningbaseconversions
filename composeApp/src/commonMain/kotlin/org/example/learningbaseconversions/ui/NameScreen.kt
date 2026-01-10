package org.example.learningbaseconversions.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

/**
 * Screen to input the user's name
 * Works with App.kt to navigate to:
 * Yes version or No version of Yes/No Screen
 * Depending on Users Choice.
 */

@Composable
fun NameScreen(
    initialName: String = "",
    onYes: (String) -> Unit,
    onNo: (String) -> Unit,
    onBack: () -> Unit
) {
    // --- Local state ---
    var name by remember { mutableStateOf(initialName) }
    var message by remember { mutableStateOf("") }
    var showYesNo by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState) //  scrollable on small screens
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp) // consistent spacing
    ) {

        Spacer(Modifier.height(20.dp))

        // Instruction label
        Text(
            text = "Please enter your name:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        // Text field for name input
        TextField(
            value = name,
            onValueChange = {
                name = it
                message = ""
                showYesNo = false
            },
            singleLine = true,
            modifier = Modifier.width(220.dp)
        )

        // Submit button
        Button(
            onClick = {
                val trimmed = name.trim()
                if (trimmed.isNotEmpty()) {
                    message = "Ok $trimmed,\nHave you heard about base conversion and do you have a perfect mastery of the topic??"
                    showYesNo = true
                } else {
                    message = "Please enter your name."
                    showYesNo = false
                }
            }
        ) {
            Text("Submit")
        }

        // Message
        if (message.isNotEmpty()) {
            Text(
                text = message,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }

        // Yes/No buttons
        if (showYesNo) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { onYes(name.trim()) }) {
                    Text("Yes")
                }
                Button(onClick = { onNo(name.trim()) }) {
                    Text("No")
                }
            }
        }

        // Back button at the bottom
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
