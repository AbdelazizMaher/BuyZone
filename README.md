# 🛒 BuyZone - Modern E-Commerce Android App

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack_Compose-2024.09.00-green.svg?style=flat&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![Koin](https://img.shields.io/badge/Koin-4.0.2-orange.svg?style=flat&logo=koin)](https://insert-koin.io/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Android-brightgreen.svg?logo=android)](https://www.android.com)

BuyZone is a feature-rich, modular E-Commerce Android application built with modern Android development tools and best practices. It showcases a clean architecture approach, multi-module setup, and a polished user interface using Jetpack Compose.

---

## 📱 Showcase

| Onboarding | Login | Home Screen |
| :---: | :---: | :---: |
| ![Onboarding](https://via.placeholder.com/200x400?text=Onboarding) | ![Login](https://via.placeholder.com/200x400?text=Login) | ![Home](https://via.placeholder.com/200x400?text=Home) |

| Categories | Product Details | Cart |
| :---: | :---: | :---: |
| ![Categories](https://via.placeholder.com/200x400?text=Categories) | ![Details](https://via.placeholder.com/200x400?text=Details) | ![Cart](https://via.placeholder.com/200x400?text=Cart) |

---

## 🚀 Features

- **Multi-method Authentication**: Sign in via Email/Password, Google, Facebook, or explore as a Guest.
- **Dynamic Home Screen**: Featured promos, categories, popular brands, and trending products.
- **Advanced Search & Filtering**: Find exactly what you're looking for with ease.
- **Category Browsing**: Explore products organized by categories.
- **Shopping Cart & Checkout**: Seamless experience from adding items to placing orders.
- **Personalized Experience**: User profiles and settings management.
- **Real-time Data**: Powered by Apollo GraphQL and Firebase for a responsive experience.

---

## 🛠 Tech Stack

- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern declarative UI.
- **Design System**: [Material 3](https://m3.material.io/) - Google's latest design language.
- **Dependency Injection**: [Koin](https://insert-koin.io/) - Pragmatic and lightweight DI for Kotlin.
- **Networking**: [Apollo GraphQL](https://www.apollographql.com/docs/kotlin/) - Strongly-typed GraphQL client.
- **Backend Services**: [Firebase](https://firebase.google.com/) (Auth, Firestore) - Scalable cloud infrastructure.
- **Asynchronous Programming**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html).
- **Architecture**: Modular Clean Architecture (Domain, Data, Presentation layers).
- **Navigation**: Type-safe Navigation Compose.

---

## 📂 Project Structure

The project is highly modularized to ensure scalability, testability, and separation of concerns.

### Core Modules
- `:core-common`: Common utilities, extensions, and base classes.
- `:core-ui`: Reusable UI components, themes, and design tokens.
- `:core-navigation`: Centralized navigation logic and destination definitions.
- `:core-session`: User session management and identity providers.

### Feature Modules
- `:feature-splash`: App entry point and branding.
- `:feature-onboarding`: First-time user experience.
- `:feature-authentication`: Login, Sign up, and Social auth flows.
- `:feature-home`: Main dashboard with dynamic content.
- `:feature-categories`: Product categorization and browsing.
- `:feature-search`: Product search and discovery.
- `:feature-details`: Detailed product information and reviews.
- `:feature-cart`: Cart management and checkout flow.
- `:feature-settings`: User preferences and account management.

### Data & Network
- `:network-apollo`: Apollo GraphQL client configuration and generated code.

---

## 🛠 Installation & Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/BuyZone.git
   ```

2. **Firebase Setup**:
   - Create a project on [Firebase Console](https://console.firebase.google.com/).
   - Add an Android app and download the `google-services.json`.
   - Place `google-services.json` in the `app/` directory.

3. **Apollo GraphQL Setup**:
   - Ensure your GraphQL schema is synced. The project uses Apollo for type-safe queries.

4. **Build & Run**:
   - Open the project in **Android Studio (Ladybug or newer)**.
   - Sync Gradle and run the `:app` module.

---

## 🏗 Architecture

The app follows **Clean Architecture** principles combined with **MVI (Model-View-Intent)** in the presentation layer:

- **Presentation Layer**: UI (Compose), ViewModels (State management), and Contracts (Intent, State, Effect).
- **Domain Layer**: Business logic via UseCases and Repository interfaces.
- **Data Layer**: Implementation of Repositories, handling data sources (Remote & Local).

---

## 🤝 Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📬 Contact

Project Link: [https://github.com/yourusername/BuyZone](https://github.com/yourusername/BuyZone)

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Developed with ❤️ by [Your Name/Team]
