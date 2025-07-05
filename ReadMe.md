# Recipe Finder Android App

Recipe Finder is an Android application designed to help users discover and manage recipes based on their preferences.  
The app allows users to search for recipes, filter them by dietary preferences (e.g., vegetarian, non-vegetarian), view detailed information.

## Features

- **Recipe Search**: Search for recipes using keywords.
- **Filter Options**: Filter recipes based on dietary preferences such as vegetarian or non-vegetarian.
- **Recipe Details**: View comprehensive details about each recipe, including ingredients and preparation steps.


## Architecture

The application follows the **MVVM (Model-View-ViewModel)** architecture pattern, leveraging Android Architecture Components for a robust and maintainable codebase.

- **ViewModel**: Handles UI-related data and business logic.
- **Repository**: Manages data operations and abstracts data sources (remote or local).
- **Data Layer**: Interacts with remote APIs and local databases.
- **Domain Layer**: Contains business logic and use cases.

## Technologies Used

- **Kotlin** – Primary programming language.
- **Android Jetpack Components** – ViewModel
- **Retrofit** – For network operations.
- **Kotlin Coroutines** – For asynchronous programming.
- **Hilt** – For dependency injection.

## Getting Started

### Prerequisites

- Android Studio Flamingo or later
- Android SDK 34
- Gradle 8.4
- JDK 17

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Marwamostafa-md/RecipeFinderAndroid.git

2. Open the project in Android Studio.

3. Sync the project with Gradle files.

4. Build and run the application on an emulator or physical device.
