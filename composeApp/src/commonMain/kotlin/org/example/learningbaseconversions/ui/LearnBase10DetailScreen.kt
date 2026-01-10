package org.example.learningbaseconversions.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.pow

/**
 * LearnBase2DetailScreen
 * Handles Base3,Base4,Base5,Base6,Base7,Base8,Base9 and Base16 ↔ Base10 conversions and
 * Base10 ↔ Base3,Base4,Base5,Base6,Base7,Base8,Base9 and Base16 conversions
 */
@Composable
fun LearnBase10DetailScreen(
    fromBase: Int,
    toBase: Int,
    onBack: () -> Unit
) {
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding() // avoid notch/camera
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp)) // fixed spacing, safe

        Text(
            text = "Step-by-Step: Base $fromBase → Base $toBase",
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Enter a number") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                output = try {
                    learnBaseConversion.learnConvertNumberWithSteps(input, fromBase, toBase)
                } catch (e: Exception) {
                    "Invalid input: ${e.message}"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convert")
        }

        Spacer(modifier = Modifier.height(16.dp)) // fixed spacing

        Text(
            text = output,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp)) // fixed spacing

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth() // keep back button full width
        ) {
            Text("Back")
        }
    }
}







object learnBaseConversion {

    /** Convert any number from fromBase → toBase with unified step-by-step output */
    fun learnConvertNumberWithSteps(input: String, fromBase: Int, toBase: Int): String {
        if (input.isEmpty()) throw Exception("Input cannot be empty")
        val normalizedInput = input.uppercase().trim()
        val out = StringBuilder()

        // --- Base N → Decimal ---
        val decimalValue = if (fromBase != 10) {
            out.append("So we are now going to learn how to convert from base $fromBase  to base 10\n")
            out.append("Converting $normalizedInput from base $fromBase to decimal\n")
            out.append("Step 1: Multiply each digit by $fromBase^power (starting from right, power=0)\n\n")

            val digits = normalizedInput.map { it.toString() }
            val length = digits.size

            // Show multiplication expressions
            for (i in digits.indices) {
                val power = length - 1 - i
                val value = charToDigit(digits[i])
                out.append("$value * $fromBase^$power")
                if (i != digits.lastIndex) out.append(" + ")
            }

            out.append("\n\nStep 2: Calculate each term from right to left\n\n")

            var total = 0
            for (i in digits.indices.reversed()) {
                val power = digits.lastIndex - i
                val value = charToDigit(digits[i])
                val term = (value * fromBase.toDouble().pow(power)).toInt()
                out.append("$value * $fromBase^$power = $term\n")
                out.append("$total + $term = ${total + term}\n\n")
                total += term
            }

            out.append("Decimal value: $total\n\n")
            total
        } else {
            input.toInt()
        }

        // --- Decimal → Base M ---
        if (toBase == 10) {
            out.append("Result: $decimalValue base 10")
            return out.toString()
        }
        out.append("So we are now going to learn how to convert from base 10  to base $toBase \n")
        out.append("Converting $decimalValue from decimal to base $toBase\n")
        out.append("Step: Divide by $toBase repeatedly and record remainders until your quotient is 0\n\n")

        if (decimalValue == 0) {
            out.append("Step 1: 0 → remainder 0\n")
            out.append("\nResult: 0 base $toBase")
            return out.toString()
        }

        val digitsOut = mutableListOf<String>()
        var temp = decimalValue
        var step = 1
        while (temp > 0) {
            val remainder = temp % toBase
            val digitChar = digitToChar(remainder)
            out.append("Step $step: $temp / $toBase → quotient: ${temp / toBase}, remainder: $remainder")
            if (remainder >= 10) out.append(" → $digitChar")
            out.append("\n")
            digitsOut.add(digitChar)
            temp /= toBase
            step++
        }

        digitsOut.reverse()
        out.append("\nNow, Joining the remainders from last to first gives: ${digitsOut.joinToString("")}\n")
        out.append("Result: ${digitsOut.joinToString("")} base $toBase")

        return out.toString()
    }

    /** Convert character to integer value (supports 0-9, A-F) */
    private fun charToDigit(c: String): Int {
        return when (c.uppercase()) {
            "A" -> 10
            "B" -> 11
            "C" -> 12
            "D" -> 13
            "E" -> 14
            "F" -> 15
            else -> c.toIntOrNull() ?: throw Exception("Invalid digit '$c'")
        }
    }

    /** Convert integer to character for digits >= 10 */
    private fun digitToChar(d: Int): String {
        return if (d >= 10) ('A'.code + d - 10).toChar().toString() else d.toString()
    }
}
