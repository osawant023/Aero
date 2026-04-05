# Aero

Aero is a modern Android application designed for monitoring market feeds and managing stock portfolios. Built with the latest Android technologies, it focuses on performance, scalability, and a seamless user experience.

## 🚀 Features

- **Market Feed**: Real-time or simulated market data listing.
- **Detailed Insights**: In-depth view of specific stocks or assets.
- **Connectivity Awareness**: Real-time monitoring of network status with user notifications.
- **Type-Safe Navigation**: Modern navigation implementation using Jetpack Compose Navigation.
- **Coming Soon**: Portfolio management, Order tracking, and User profiles.

## 🛠 Tech Stack

- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern declarative UI toolkit.
- **Navigation**: [Compose Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) - Type-safe navigation between screens.
- **Dependency Injection**: [Koin](https://insert-koin.io/) - Pragmatic lightweight dependency injection framework.
- **Networking**: [Ktor](https://ktor.io/) - Multiplatform asynchronous HTTP client.
- **Asynchronous**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html).
- **Architecture**: Clean Architecture with MVI.

## 📂 Project Structure

The project follows Clean Architecture principles, organized within the `app` module:

```text
com.app.aero
├── di              # Dependency Injection modules (Koin)
├── core            # Common utilities, theme, and navigation setup
│   ├── navigation  # Navigation routes and graph definitions
│   ├── ui          # Reusable UI components and Theme (Color, Type, Spacing)
│   └── util        # Helper classes and extensions
├── data            # Implementation of repositories and data sources (Network/Local)
├── domain          # Business logic: Models, Repository interfaces, and Use cases
├── presentation    # UI Layer
│   ├── component   # Feature-agnostic UI components (TopBar, Common Composable)
│   ├── feature_feed        # Feed list screen and ViewModels
│   └── feature_feed_details # Detailed view components and ViewModels
└── app             # Main application class and MainActivity
```

## 🏗 Getting Started

### Prerequisites
- Android Studio Ladybug or newer.
- JDK 17+.
- Android SDK 34+.

### Installation
1. Clone the repository.
2. Open the project in Android Studio.
3. Sync Gradle and run the `app` module on an emulator or physical device.

## 📥 Download APK

You can download the latest debug APK for testing:
[Download Aero Debug APK](info/app-debug.apk)

## 📱 Screenshots

<p align="center">
  <img src="info/Screenshot%202026-04-05%20at%207.58.16 PM.png" width="300" />
  <img src="info/Screenshot%202026-04-05%20at%207.58.25 PM.png" width="300" />
</p>

### 🎥 Demo
[Watch the Screen Recording](info/demo_video.gif)

---
Developed with ❤️ using Kotlin and Jetpack Compose.
