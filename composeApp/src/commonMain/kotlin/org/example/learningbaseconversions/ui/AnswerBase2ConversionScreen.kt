package org.example.learningbaseconversions.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow

/**
 * AnswerBase2ConversionScreen
 * Handles getting answer of Base2 ↔ Base10, Base8, Base16 and Base10 → Base2 conversions
 */
@Composable
fun AnswerBase2ConversionScreen(
    conversionType: String,
    onBack: () -> Unit
) {
    var outputText by remember { mutableStateOf("") }
    var inputNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing) // Safe area for notch/home bar
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Base 2 Conversion: $conversionType",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Input field
        OutlinedTextField(
            value = inputNumber,
            onValueChange = { inputNumber = it.uppercase() },
            label = { Text("Enter number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Convert button
        Button(
            onClick = {
                outputText = try {
                    when (conversionType) {
                        "Base2ToBase10" -> answerConvertBase2ToBase10(inputNumber)
                        "Base2ToBase8" -> answerConvertBase2ToBase8(inputNumber)
                        "Base2ToBase16" -> answerConvertBase2ToBase16(inputNumber)
                        "Base10ToBase2" -> answerConvertBase10ToBase2(inputNumber)
                        else -> "Unknown conversion type"
                    }
                } catch (e: Exception) {
                    "Invalid input!"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convert")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Output display
        if (outputText.isNotEmpty()) {
            Text(
                text = outputText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Back button at the bottom
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(onClick = onBack) {
                Text("Back")
            }
        }
    }
}


/** Compose-friendly conversion functions */

private fun base2ExtractDigits(num: Long): LongArray {
    val digits = mutableListOf<Long>()
    var temp = num
    if (temp == 0L) digits.add(0)
    while (temp != 0L) {
        digits.add(0, temp % 10)
        temp /= 10
    }
    return digits.toLongArray()
}

// Convert Base2 → Base10
private fun answerConvertBase2ToBase10(input: String): String {
    return try {
        val digits = base2ExtractDigits(input.toLong())
        var num10 = 0
        var power = 0
        for (i in digits.indices.reversed()) {
            num10 += (digits[i] * 2.0.pow(power)).toInt()
            power++
        }
        "$input base 2 = $num10 base 10"
    } catch (e: Exception) {
        "Invalid binary input!"
    }
}

// Convert Base2 → Base8
private fun answerConvertBase2ToBase8(input: String): String {
    return try {
        val digits = base2ExtractDigits(input.toLong())
        var temp = 0
        var num8 = 0
        var power = 0
        var factor = 1
        for (i in digits.indices.reversed()) {
            temp += (digits[i] * 2.0.pow(power)).toInt()
            power++
            if (power % 3 == 0) {
                num8 += temp * factor
                factor *= 10
                temp = 0
                power = 0
            }
        }
        if (temp != 0) num8 += temp * factor
        "$input base 2 = $num8 base 8"
    } catch (e: Exception) {
        "Invalid binary input!"
    }
}

// Convert Base2 → Base16
private fun answerConvertBase2ToBase16(input: String): String {
    return try {
        val digits = base2ExtractDigits(input.toLong())
        val hexDigits = mutableListOf<String>()
        var temp = 0
        var power = 0
        for (i in digits.indices.reversed()) {
            temp += (digits[i] * 2.0.pow(power)).toInt()
            power++
            if (power % 4 == 0) {
                hexDigits.add(0, temp.toString(16).uppercase())
                temp = 0
                power = 0
            }
        }
        if (temp != 0) hexDigits.add(0, temp.toString(16).uppercase())
        "$input base 2 = ${hexDigits.joinToString("")} base 16"
    } catch (e: Exception) {
        "Invalid binary input!"
    }
}

// Convert Base10 → Base2 (fixed)
private fun answerConvertBase10ToBase2(input: String): String {
    return try {
        var num10 = input.toLong()
        if (num10 == 0L) return "0 base 10 = 0 base 2"

        var result = ""
        while (num10 > 0) {
            result = (num10 % 2).toString() + result
            num10 /= 2
        }
        "$input base 10 = $result base 2"
    } catch (e: Exception) {
        "Invalid decimal input!"
    }
}
