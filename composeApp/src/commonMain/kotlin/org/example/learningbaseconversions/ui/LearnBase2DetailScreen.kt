package org.example.learningbaseconversions.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow

/**
 * LearnBase2DetailScreen
 * Handles Base2 ↔ Base10, Base8, Base16 and Base10 → Base2 conversions
 */
@Composable
fun LearnBase2DetailScreen(
    conversionType: String,
    onBack: () -> Unit
) {
    var inputNumber by remember { mutableStateOf("") }
    var outputText by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp) // consistent spacing
    ) {
        Text(
            text = "Convert number ($conversionType)  ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = inputNumber,
            onValueChange = { inputNumber = it },
            placeholder = { Text("Enter number here") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = {
                    outputText = try {
                        when (conversionType) {
                            "Base2ToBase10" -> learnConvertBase2ToBase10(inputNumber)
                            "Base2ToBase8" -> learnConvertBase2ToBase8(inputNumber)
                            "Base2ToBase16" -> learnConvertBase2ToBase16(inputNumber)
                            "Base10ToBase2" -> learnConvertBase10ToBase2(inputNumber)
                            else -> "Unknown conversion type"
                        }
                    } catch (e: Exception) {
                        "Invalid number!"
                    }
                }
            ) {
                Text("Convert")
            }

            Button(
                onClick = {
                    outputText = ""
                    inputNumber = ""
                    onBack()
                }
            ) {
                Text("Back")
            }
        }

        if (outputText.isNotEmpty()) {
            Text(
                text = outputText,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}



// ----------------- Conversion Functions -----------------

private fun learnConvertBase2ToBase10(binaryInput: String): String {
    val A = binaryInput.map { it.digitToInt() }.toIntArray()
    val N = A.size
    val out = StringBuilder()

    out.append("So we are now going to learn how to convert from base 2 to base 10\n")
    out.append("Note: The method is the same for converting a number in any base to base 10\n")
    out.append("You keep multiplying each digit by powers of the base starting with 0 from the right to the left\n")
    out.append("And adding all your multiplication results\n")
    out.append("Let's see this in practice\n")
    out.append("Our number is: $binaryInput\n\n")
    out.append("So we get:\n")

    // Show formula: digit * 2^position
    var index = 0
    var power = N - 1
    while (index < N) {
        out.append("${A[index]} * 2^$power")
        if (index != N - 1) out.append(" + ")
        index++
        power--
    }

    out.append("\n\nThis gives us:\n\n")

    // Step-by-step calculation from rightmost digit
    power = 0
    var digitIndex = N - 1
    var num10 = 0
    while (digitIndex >= 0) {
        val value = (A[digitIndex] * 2.0.pow(power)).toInt()
        out.append("${A[digitIndex]} * 2^$power = $value\n")
        out.append("$num10 + $value = ")
        num10 += value
        out.append("$num10\n\n")
        power++
        digitIndex--
    }

    out.append("$binaryInput base 2 is $num10 base 10")

    return out.toString()
}

private fun learnConvertBase2ToBase8(binaryInput: String): String {
    val A = binaryInput.map { it.digitToInt() }.toIntArray()
    val N = A.size
    val out = StringBuilder()

    out.append("So we are now going to learn how to convert from base 2 to base 8\n")
    out.append("Remember: 8 = 2^3, so we group the binary digits in 3s from right to left\n")
    out.append("Let's see this in practice\n")
    out.append("Our number is: $binaryInput\n\n")

    // Step 1: Pad to multiple of 3
    val pad = (3 - N % 3) % 3
    val padded = List(pad) { 0 } + A.toList()
    out.append("Step 1: Pad with zeros on the left to make groups of 3: ${padded.joinToString("")}\n\n")

    // Step 2: Split into groups of 3 from left (most significant first)
    val groups = padded.chunked(3)
    out.append("Step 2: Split into groups of 3 (left to right):\n")
    groups.forEachIndexed { index, group ->
        out.append("Group ${index + 1}: ${group.joinToString("")}\n")
    }

    out.append("\nStep 3: Convert each group to decimal:\n")
    val digits = mutableListOf<Int>()

    groups.forEachIndexed { index, group ->
        var groupValue = 0
        val additions = mutableListOf<Int>()
        out.append("Group ${index + 1} calculation:\n")

        for (i in group.indices) {
            val power = 2 - i
            val product = group[i] * 2.0.pow(power).toInt()
            out.append("${group[i]} * 2^$power = $product\n")
            additions.add(product)
            groupValue += product
        }
        // Show addition and group value
        out.append("Addition: ${additions.joinToString(" + ")} = $groupValue\n")
        out.append("Group ${index + 1} value: $groupValue\n\n")
        digits.add(groupValue)
    }

    // Step 4: Combine digits to form final base 8 number
    val num8 = digits.joinToString("") { it.toString() }
    out.append("Now you join back the values of each group in order\n")
    out.append("Result: $binaryInput base 2 is $num8 base 8")

    return out.toString()
}






private fun learnConvertBase2ToBase16(binaryInput: String): String {
    val A = binaryInput.map { it.digitToInt() }.toIntArray()
    val N = A.size
    val out = StringBuilder()

    // --- Intro ---
    out.append("So we are now going to learn how to convert from base 2 to base 16\n")
    out.append("Remember: 16 = 2^4, so we group the binary digits in 4s from right to left\n")
    out.append("Let's see this in practice\n")
    out.append("Our number is: $binaryInput\n\n")
    out.append("Hexadecimal mappings: \n10=A, \n11=B, \n12=C, \n13=D, \n14=E, \n15=F\n\n")

    // --- Step 1: Pad ---
    val pad = (4 - N % 4) % 4
    val padded = List(pad) { 0 } + A.toList()
    out.append("Step 1: Pad with zeros on the left to make groups of 4: ${padded.joinToString("")}\n\n")

    // --- Step 2: Split into groups ---
    val groups = padded.chunked(4)
    out.append("Step 2: Split into groups of 4 (left to right):\n")
    groups.forEachIndexed { index, group ->
        out.append("Group ${index + 1}: ${group.joinToString("")}\n")
    }

    out.append("\nStep 3: Convert each group to hexadecimal (show addition):\n")

    val hexDigits = mutableListOf<String>()

    for ((index, group) in groups.withIndex()) {
        var groupValue = 0
        val additions = mutableListOf<Int>()

        out.append("Group ${index + 1} calculation:\n")

        // Descending order: leftmost digit most significant
        for (i in group.indices) {
            val power = 3 - i
            val product = group[i] * 2.0.pow(power).toInt()
            additions.add(product)
            out.append("Digit ${group[i]} * 2^$power = $product\n")

            groupValue += product
        }

        // --- Explicit addition step ---
        out.append("Addition: ${additions.joinToString(" + ")} = $groupValue\n")

        // Convert to hex
        val hexChar = groupValue.toString(16).uppercase()
        if (groupValue in 10..15) {
            out.append("Remember: $groupValue is represented as '$hexChar'\n")
        }

        out.append("Group ${index + 1} value: $groupValue → $hexChar\n\n")
        hexDigits.add(hexChar)
    }

    val hexResult = hexDigits.joinToString("")
    out.append("Now you join back the values of each group in order\n")
    out.append("Result: $binaryInput base 2 is $hexResult base 16")

    return out.toString()
}






private fun learnConvertBase10ToBase2(decimalInput: String): String {
    val num10 = decimalInput.toInt()
    var temp = num10
    val out = StringBuilder()
    out.append("So we are now going to learn how to convert from base 10 to base 2\n")
    out.append("Converting $num10 from Base 10 to Base 2\n")
    out.append("Method: Divide by 2 repeatedly until quotient is 0,\n record remainders,\n read last to first\n\n")

    val remainders = mutableListOf<Int>()
    while (temp > 0) {
        val rem = temp % 2
        val q = temp / 2
        out.append("$temp / 2 = $q remainder $rem\n")
        remainders.add(rem)
        temp = q
    }

    out.append("\nBinary digits (from last to first): ${remainders.reversed().joinToString("")}\n")
    out.append("Result: $num10 base 10 = ${remainders.reversed().joinToString("")} base 2")
    return out.toString()
}
