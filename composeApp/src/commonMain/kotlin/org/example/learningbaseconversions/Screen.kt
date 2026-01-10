package org.example.learningbaseconversions

sealed class Screen {
    object Welcome : Screen()
    object Name : Screen()
    object YesNo : Screen()
    object Learn : Screen()

    // --- Learn Base2 screens ---
    object LearnBase2Menu : Screen()          // For the Base2 menu screen with conversion choices
    object LearnBase2Detail : Screen()        // For the Base2 detailed conversion screen

    // --- Learn Base10 screens ---
    object LearnBase10Menu : Screen()         // For the Base10  menu screen with conversion choices

    object LearnBase10Detail : Screen()       // For the Base10 detailed conversion screen

    object Answer : Screen()

    // --- Answer Base2 screens ---
    object AnswerBase2 : Screen()             // For the Base2 menu screen with conversion choices

    object AnswerBase2Conversion : Screen()   // For the Base2 answer conversion screen

    // --- Answer Base10 screens ---
    object AnswerBase10 : Screen()            // For the Base10  menu screen with conversion choices
    object AnswerBase10Conversion : Screen()  // For the Base10 answer conversion screen
}




