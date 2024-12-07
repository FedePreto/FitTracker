# FitTracker - Your Personal Wellness Companion 🚀

## Table of Contents 📖

1. [Introduction](#introduction)
2. [Features](#features)
3. [Technology Stack](#technology-stack)
4. [Architecture](#architecture)
5. [Screens and User Flow](#screens-and-user-flow)
6. [Testing and Quality Assurance](#testing-and-quality-assurance)
7. [Future Developments](#future-developments)
8. [Contributors](#contributors)

---

## Introduction 🌟

**FitTracker** is a mobile application designed to enhance your physical and mental well-being. It serves as a digital dietitian, fitness coach, and nutritionist, all in the palm of your hand. Track your meals, monitor your fitness routines, and achieve your health goals seamlessly.

FitTracker is not just another restrictive dieting app. It helps you understand your habits, make healthier choices, and find motivation to pursue your wellness journey.

### Key Objectives:

- **Holistic Wellness**: 🧘‍♂️ Track food, water intake, and physical activity in one place.
- **Personalized Diets**: 🍴 Customize your dietary plan based on personal preferences and goals.
- **User-Friendly Interface**: 🖥️ Designed for simplicity and ease of use.

---

## Features 🎯

### Core Functionalities

1. **User Authentication** 🔒
   - Login, logout, and registration functionalities.
2. **Daily Nutrition Tracking** 🥗
   - Log meals, calories, and macronutrients (proteins, carbs, fats).
3. **Exercise Monitoring** 🏋️‍♂️
   - Track physical activities and calories burned.
4. **Water Intake Tracker** 💧
   - Daily progress toward hydration goals.
5. **Custom Diet Plans** 🍳
   - Choose from various diet options (Mediterranean, Keto, etc.).
6. **Barcode Scanning** 📸
   - Add food items by scanning their barcodes.
7. **Statistics Dashboard** 📊
   - View comprehensive stats filtered by date ranges.

### Additional Features

- ⭐ Favorite meals and exercises.
- 📝 Custom recipes and personalized food items.
- ☁️ Secure data storage via cloud services.

---

## Technology Stack 🛠️

**Android (Kotlin)**

- Language: Kotlin
- Architecture: MVVM (Model-View-ViewModel)
- Backend: Firebase (Firestore & Firebase Auth)
- APIs:
  - Edamam (Food Database) 🥑
  - Ninjas API (Exercise Data) 🥋

**Flutter (Dart)**

- Framework: Flutter for cross-platform development.
- Backend: Firebase integration for data management.
- Language: Dart for seamless development.

---

## Architecture 🏗️

### Android

FitTracker employs the **MVVM Architecture**, ensuring a clear separation of concerns:

- **Model**: Manages application data and business logic.
- **View**: Displays UI components.
- **ViewModel**: Acts as a bridge between Model and View.

### Backend

- **Cloud-Based Storage**: ☁️ Firebase Firestore handles persistent data storage.
- **APIs**: 🔗 Edamam and Ninjas provide food and exercise information.

---

## Screens and User Flow 📱

### User Registration and Login 🔑

- Register or log in to access personalized features.
- Secure authentication through Firebase.

### Home Screen 🏠

- **Diary View**: Displays calorie intake, burned calories, and macronutrient breakdown.
- **Quick Add**: Log meals, water, and exercises.

### Detailed Views 🧾

- **Diet Section**: Select and customize diet plans.
- **Statistics Section**: Filter and view historical data.

---

## Testing and Quality Assurance ✅

### Unit Testing 🧪

- **JUnit**: Verifies core calculations like daily caloric needs.
- **Functional UI Tests**: Ensures the smooth flow of user interactions using Espresso.

### Key Scenarios Tested:

1. Accurate caloric requirement calculations.
2. Smooth navigation and UI responsiveness.
3. Proper data saving and retrieval.

---

## Future Developments 🔮

- **AI Integration**: 🤖 Predictive analysis for dietary and fitness recommendations.
- **Offline Mode**: 📶 Track your data without an internet connection.
- **Enhanced Features**:
  - 🌐 Social sharing options.
  - 📲 Additional integrations for wearables (e.g., Fitbit, Garmin).

---

## Contributors 🤝

- **Alessandro Rongoni** 🧑‍💻
- **Federico Pretini** 🧑‍💻
- **Gregorio Vecchiola** 🧑‍💻

---

FitTracker is your go-to app for achieving balance and improving overall health. Join us in redefining wellness through technology! 🌟
