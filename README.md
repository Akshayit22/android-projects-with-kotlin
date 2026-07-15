# Android Projects with Kotlin & Jetpack Compose

A collection of Android applications built with Kotlin, Jetpack Compose and Android Studio — ranging from full-featured apps to focused concept demos. Projects are listed from most complex to simplest.

## Apps

### OffVault
An offline-first, encrypted personal vault for storing login credentials, cards, documents and other sensitive notes. Features biometric authentication, encrypted local storage (Room + key management), and encrypted backup/restore. Organised into sections with dedicated screens for each data type. *(~5.5k LOC — most complex)*

### HotNews
A news reader app that fetches top headlines and lets users search, read and bookmark articles. Uses a Redux-style architecture (actions, reducers, middleware), Retrofit for networking, Room for offline bookmark caching, bottom-navigation, and a settings screen for country/language preferences.

### NotesApp
A note-taking app with full create/edit/delete support backed by a Room database. Includes a biometric lock screen to protect notes and a clean Compose UI with navigation.

### NavigatorApp
A demo app exploring Jetpack Compose Navigation patterns — simple navigation, nested graphs (auth/app flows) and type-safe navigation.

### reduxApp
A playground demonstrating Redux-style state management in Compose, covering a simple counter, action-based user state, and a modular Kotlin Redux setup with auth and counter slices.

### UserList
Fetches and displays a list of users from a REST API using Retrofit, with a detail screen and navigation between list and details.

### WeatherApp
Fetches live weather data for a searched location from a weather API and displays current conditions using Retrofit and a Compose UI.

### shoppingList
A simple shopping list app with add, edit and delete functionality demonstrating Compose state handling.

### UnitCovertor
A basic unit converter that converts between measurement units using a ViewModel and Compose UI.

### MyFirstApplication
An introductory app covering Compose fundamentals — lists, data models and basic UI composition.
