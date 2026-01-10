package org.example.learningbaseconversions

import androidx.compose.runtime.*
import org.example.learningbaseconversions.ui.*

/**
 * App.kt kotlin file
 * Controls Navigation among Screens
 */

@Composable
fun App() {
    var userName by remember { mutableStateOf("") } // used to store the username
    var currentScreen: Screen by remember { mutableStateOf(Screen.Welcome) } // keeps track of which screen is been displayed
    var lastUserChoiceWasYes by remember { mutableStateOf(true) } // Used to keep track of whether user chose yes or no at namescreen
    var currentConversion: Pair<Int, Int>? by remember { mutableStateOf(null) }
    var lastConversionType by remember { mutableStateOf("") }
    var currentConversionType by remember { mutableStateOf("") }

    when (currentScreen) {
       // --- Welcome Screen --- //
        Screen.Welcome -> WelcomeScreen(onContinue = { currentScreen = Screen.Name })
       // --- Name Screen --- //
        Screen.Name -> NameScreen(
            initialName = userName,
            // --- call Yes version of YesNo Screen ---
            onYes = { name ->
                userName = name
                lastUserChoiceWasYes = true
                currentScreen = Screen.YesNo
            },
            // --- call No version of YesNo Screen ---
            onNo = { name ->
                userName = name
                lastUserChoiceWasYes = false
                currentScreen = Screen.YesNo
            },
            onBack = { currentScreen = Screen.Welcome }
        )
        /**
         * --- YesNo Screen ---
         * --- Navigates to either Learn Screen or Answer Screen
         */
        Screen.YesNo -> YesNoScreen(
            userName = userName,
            cameFromYes = lastUserChoiceWasYes,
            onLearn = { currentScreen = Screen.Learn },
            onAnswer = { currentScreen = Screen.Answer },
            onBack = { currentScreen = Screen.Name }
        )
       // --- Learn Screen ---
        Screen.Learn -> LearnScreen(
            userName = userName,
            onLearnBase2 = { currentScreen = Screen.LearnBase2Menu },
            onLearnBase10 = { currentScreen = Screen.LearnBase10Menu },
            onBack = { currentScreen = Screen.YesNo }
        )

        // --- Learn Base2 Conversion Menu ---
        Screen.LearnBase2Menu -> LearnBase2MenuScreen(
            userName = userName,
            onBack = { currentScreen = Screen.Learn },
            onConversionSelected = { conversion ->
                lastConversionType = conversion
                currentScreen = Screen.LearnBase2Detail
            }
        )

        // --- Learn Base2 Conversion Detail (Step-by-step) ---
        Screen.LearnBase2Detail -> LearnBase2DetailScreen(
            conversionType = lastConversionType.ifBlank { "Base2ToBase10" },
            onBack = { currentScreen = Screen.LearnBase2Menu }
        )

        // --- Learn Base10 Conversion Menu ---
        Screen.LearnBase10Menu -> LearnBase10MenuScreen(
            onConversionSelected = { from, to ->
                currentConversion = from to to
                currentScreen = Screen.LearnBase10Detail
            },
            onBack = { currentScreen = Screen.Learn }
        )

        // --- Learn Base10 Conversion Detail (Step-by-step) ---
        Screen.LearnBase10Detail -> currentConversion?.let { (from, to) ->
            LearnBase10DetailScreen(
                fromBase = from,
                toBase = to,
                onBack = { currentScreen = Screen.LearnBase10Menu }
            )
        }

       // --- Answer screen ---
        Screen.Answer -> AnswerScreen(
            userName = userName,
            onAnswerBase2 = { currentScreen = Screen.AnswerBase2 },
            onAnswerBase10 = { currentScreen = Screen.AnswerBase10 },
            onBack = { currentScreen = Screen.YesNo }
        )

        // --- Answer Menu Screen for Base2 ---
        Screen.AnswerBase2 -> AnswerBase2Screen(
            userName = userName,
            onConversionSelected = { conversion ->
                currentConversionType = conversion
                currentScreen = Screen.AnswerBase2Conversion
            },
            onBack = { currentScreen = Screen.Answer }
        )

        // --- Answer Screen for Base2 Conversion ---
        Screen.AnswerBase2Conversion -> AnswerBase2ConversionScreen(
            conversionType = currentConversionType.ifBlank { "Base2ToBase10" },
            onBack = { currentScreen = Screen.AnswerBase2 }
        )

        // --- Answer Menu Screen for Base10 ---
        Screen.AnswerBase10 -> AnswerBase10Screen(
            userName = userName,
            onConversionSelected = { conversion ->
                currentConversionType = conversion
                currentScreen = Screen.AnswerBase10Conversion
            },
            onBack = { currentScreen = Screen.Answer }
        )

        // --- Answer Screen for Base10 Conversion ---
        Screen.AnswerBase10Conversion -> currentConversionType.let { conversion ->
            AnswerBase10ConversionScreen(
                selectedConversion = conversion, // pass the choice from AnswerBase10Screen
                onBack = { currentScreen = Screen.AnswerBase10 } // back goes to selection screen
            )
        }
    }
}
