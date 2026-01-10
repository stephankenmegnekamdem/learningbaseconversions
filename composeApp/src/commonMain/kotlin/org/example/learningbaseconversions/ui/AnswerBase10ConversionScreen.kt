package org.example.learningbaseconversions.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.systemBarsPadding
import kotlin.math.pow

/**
 * AnswerBase2ConversionScreen
 * Handles getting answer of Base3,Base4,Base5,Base6,Base7,Base8,Base9 and Base16 ↔ Base10 conversions and
 * Base10 ↔ Base3,Base4,Base5,Base6,Base7,Base8,Base9 and Base16 conversions
 */
@Composable
fun AnswerBase10ConversionScreen(
    selectedConversion: String, // pass the conversion chosen in AnswerBase10Screen
    onBack: () -> Unit
) {
    var inputText by remember { mutableStateOf("") }
    var outputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()          // <-- safe area padding
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Selected: Answer: $selectedConversion", fontSize = 20.sp)

        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter number") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                outputText = try {
                    when (selectedConversion) {
                        // Base10 → Other Bases
                        "Base10 → Base3" -> answerBase10ToBaseN(inputText, 3)
                        "Base10 → Base4" -> answerBase10ToBaseN(inputText, 4)
                        "Base10 → Base5" -> answerBase10ToBaseN(inputText, 5)
                        "Base10 → Base6" -> answerBase10ToBaseN(inputText, 6)
                        "Base10 → Base7" -> answerBase10ToBaseN(inputText, 7)
                        "Base10 → Base8" -> answerBase10ToBaseN(inputText, 8)
                        "Base10 → Base9" -> answerBase10ToBaseN(inputText, 9)
                        "Base10 → Base16" -> answerBase10ToBase16(inputText)

                        // Other Bases → Base10
                        "Base3 → Base10" -> answerBaseNToBase10(inputText, 3)
                        "Base4 → Base10" -> answerBaseNToBase10(inputText, 4)
                        "Base5 → Base10" -> answerBaseNToBase10(inputText, 5)
                        "Base6 → Base10" -> answerBaseNToBase10(inputText, 6)
                        "Base7 → Base10" -> answerBaseNToBase10(inputText, 7)
                        "Base8 → Base10" -> answerBaseNToBase10(inputText, 8)
                        "Base9 → Base10" -> answerBaseNToBase10(inputText, 9)
                        "Base16 → Base10" -> answerBase16ToBase10(inputText)

                        else -> "Unknown conversion type"
                    }
                } catch (e: Exception) {
                    "Invalid input!"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Answer")
        }

        if (outputText.isNotEmpty()) {
            Text(outputText, modifier = Modifier.fillMaxWidth())
        }

        // Option to choose another conversion
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Answer: Select another conversion")
        }
    }
}




// --- Helper: Base10 to base N ---
fun answerBase10ToBaseN(input: String, base: Int): String {
    val num10 = input.toInt()
    var temp = num10
    var result = 0L
    var c = 0

    while (temp > 0) {
        result += (temp % base) * 10.0.pow(c).toLong()
        temp /= base
        c++
    }

    return "$num10 base 10 is $result base $base"
}

// --- Helper: BaseN to Base10 ---
fun answerBaseNToBase10(input: String, base: Int): String {
    val numN = input.toLong()
    val digits = mutableListOf<Int>()
    var temp = numN
    while (temp > 0) {
        digits.add((temp % 10).toInt())
        temp /= 10
    }

    var num10 = 0
    for (i in digits.indices) {
        num10 += digits[i] * base.toDouble().pow(i).toInt()
    }

    return "$numN base $base is $num10 base 10"
}

// --- Helper: Base10 to Base16 ---
fun answerBase10ToBase16(input: String): String {
    val num10 = input.toInt()
    val hex = num10.toString(16).uppercase()
    return "$num10 base 10 is $hex base 16"
}

// --- Helper: Base16 to Base10 ---
fun answerBase16ToBase10(input: String): String {
    val num10 = input.toInt(16)
    return "$input base 16 is $num10 base 10"
}
