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

/**
 * YesNo Screen gives more details to a beginner(No version) in Number Systems/ Base Conversion
 * or an Advanced user(Yes version) in Number Systems/ Base Conversion
 * Works with App.kt to navigate to LearnScreen or AnswerScreen.
 */

@Composable
fun YesNoScreen(
    userName: String,
    cameFromYes: Boolean,
    onLearn: () -> Unit,
    onAnswer: () -> Unit,
    onBack: () -> Unit
) {
    val scrollState = rememberScrollState()

    // --- AI-like prompt ---
    val prompt = if (!cameFromYes) {
        "Explain base conversion to a beginner in a fun, short, and friendly way. " +
                "Explain what it is and why it is important in everyday technology."
    } else {
        "Motivate someone who already knows base conversion to use an app that helps " +
                "practice and quickly solve base conversion problems."
    }

    // --- Generate answer ONCE when screen opens ---
    val generatedAnswer by remember {
        mutableStateOf(generateChatLikeResponse(prompt))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing) // iOS safe area
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

       // --- Greets User once more
        Text(
            text = "Hello $userName 👋",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        // --- Generated Answer
        Text(
            text = generatedAnswer,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // -- Buttons navigating to Learn Screen or Answer Screen ---
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = onLearn) {
                Text("Learn")
            }
            Button(onClick = onAnswer) {
                Text("Get answer to conversion")
            }
        }

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

/**
 *Generates a Response based on User Choice
 */

fun generateChatLikeResponse(prompt: String): String {

    // Beginner's list of Responses
    val beginnerResponses = listOf(
        "Base conversion is just a way of writing numbers differently 🔢. \n" +
                "Number systems are used to represent numbers, and in base N we use digits from 0 to (N−1). \n" +
                "Computers use binary (base 2) instead of base 10 — that’s how phones and apps work behind the scenes!",

        "Think of base conversion like changing languages 🌍. \n" +
                "Number systems define how numbers are represented, and in base N we count using digits 0 to (N−1). \n" +
                "Humans speak base 10, computers speak base 2, and learning both helps you understand how technology thinks!",

        "Every click, tap, and message on your machine relies on base conversion ⚙️. \n" +
                "Number systems(Bases) represent numbers using specific digits, and any base N always uses digits from 0 to (N−1). \n" +
                "Once you learn this, computer science becomes much easier!"
    )

    // Advanced user's list of Responses
    val advancedResponses = listOf(
        "You already know base conversion — now master it 💪. \n" +
                "Use this app to practice faster, avoid mistakes, and sharpen your skills anytime.",

        "Base conversion is a core skill in computing 🚀. \n" +
                "This app helps you stay sharp with quick answers and focused practice.",

        "You know the theory — now apply it efficiently ⚡. \n" +
                "Solve conversions quickly and confidently with this app."
    )

    return if (prompt.contains("beginner", ignoreCase = true)) {
        beginnerResponses.random()
    } else {
        advancedResponses.random()
    }
}
