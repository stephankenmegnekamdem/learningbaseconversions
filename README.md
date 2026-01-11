# Learning Base Conversions (A GAME OF LOGIC)
## Table of Contents

- [Project Description](#project-description)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Installation Instructions](#installation-instructions)
- [Demo](#demo)
- [License](#license)
- [Key Features](#key-features)
## Project Description

Learning Base Conversions is a Kotlin Multiplatform (KMP) application targeting Android, iOS, Desktop (JVM) and designed to help users learn and practice number base conversions, taking  **base 2 (binary)** and **base 10 (decimal)** as the fundamental bases. It helps develop a logical thinking to be used in all daily activities and bridges the gap between users and their devices which fundamentally use binary number systems  

The application provides two main modes:  
- **Learn Mode**, where users are guided step by step through base conversion concepts. It's divide into base 2 and base 10 conversions
- **Answer Mode**, where users can get a direct answer to the conversion without any explanation/steps.  It's divide into base 2 and base 10 conversions

The project was originally implemented as a class-based Java/Kotlin program and later refactored into a modern, screen-based **Compose Multiplatform UI**, making it cross-platform for Android, iOS, and Desktop.

---
## Technologies Used

- Kotlin Multiplatform (KMP)  
- Jetpack Compose   
- Gradle  
- IntelliJ IDEA / Android Studio
- Git  

---

## Project Structure


This is a Kotlin Multiplatform project targeting Android, iOS, Desktop (JVM).

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
      Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
      folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


  We have the App.kt file controlling navigation between screens and Screen keeping the declarations for each sealed object
  - **Main Screens** include:  
  - WelcomeScreen  
  - NameScreen  to enter your name and proceed to yes/no screen based on your choice
  - YesNoScreen to proceed to either LearnScreen or AnswerScreen
  - LearnScreen subdivided into :
    - LearnBase2MenuScreen unfolding to LearnBase2DetailScreen where actual conversion is made for base 2 
    - LearnBase10MenuScreen unfolding to LearnBase10DetailScreen where actual conversion is made for base 10
  - AnswerScreen subdivided into :  
    - AnswerBase2Screen unfolding to AnswerBase2ConversionScreen where actual conversion is made for base 2 
    - AnswerBase10Screen unfolding to AnswerBase10ConversionScreen where actual conversion is made for base 10

---


## Installation Instructions

### Prerequisites

- JDK 17 or newer  
- IntelliJ IDEA
- Android Studio
- KotlinMultiPlatform plugin 
- Git
- X-Code 

---

### Clone the Repository

```bash
git clone  https://github.com/stephankenmegnekamdem/learningbaseconversions.git
cd learningbaseconversions



### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run Desktop (JVM) Application

To build and run the development version of the desktop app, use the run configuration from the run widget
in your IDE’s toolbar or run it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:run
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:run
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---
### Key Features
- Interactive learning flow for base conversions
- Separate Learn and Answer modes
- Cross-platform using Kotlin Multiplatform
- Shared logic across platforms
  

---
### Demo



---

### License
This project is licensed under the MIT License. See the [License](./LICENSE) file for details.
---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…# leaningbaseconversionstest
# leaningbaseconversionstest
# leaningbaseconversionstest
# leaningbaseconversionstest
# leaningbaseconversionstest
# learningbaseconversionstest
