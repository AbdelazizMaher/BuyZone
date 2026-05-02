# 🛒 BuyZone - Premier E-Commerce Android Suite

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack_Compose-2024.09.00-green.svg?style=flat&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![Koin](https://img.shields.io/badge/Koin-4.0.2-orange.svg?style=flat&logo=koin)](https://insert-koin.io/)
[![Apollo](https://img.shields.io/badge/Apollo-GraphQL-purple.svg?style=flat&logo=apollographql)](https://www.apollographql.com/docs/kotlin/)
[![Firebase](https://img.shields.io/badge/Firebase-Auth%20%7C%20Firestore-ffca28.svg?style=flat&logo=firebase)](https://firebase.google.com/)

**BuyZone** is a high-performance, multi-module Android E-Commerce application. Engineered with **Clean Architecture** and **MVI**, it provides a seamless shopping experience powered by a GraphQL backend and Firebase infrastructure.

---

## 📱 App Experience

### ✨ Onboarding Journey
| Step 1 | Step 2 | Step 3 | Step 4 |
| :---: | :---: | :---: | :---: |
| ![Onboarding 1](https://via.placeholder.com/150x300?text=Welcome) | ![Onboarding 2](https://via.placeholder.com/150x300?text=Discover) | ![Onboarding 3](https://via.placeholder.com/150x300?text=Shop) | ![Onboarding 4](https://via.placeholder.com/150x300?text=Enjoy) |

### 🌗 Light & Dark Mode
| Screen | Light Mode | Dark Mode |
| :--- | :---: | :---: |
| **Login** | ![Login Light](https://via.placeholder.com/150x300?text=Login+Light) | ![Login Dark](https://via.placeholder.com/150x300?text=Login+Dark) |
| **Register** | ![Register Light](https://via.placeholder.com/150x300?text=Register+Light) | ![Register Dark](https://via.placeholder.com/150x300?text=Register+Dark) |
| **Home** | ![Home Light](https://via.placeholder.com/150x300?text=Home+Light) | ![Home Dark](https://via.placeholder.com/150x300?text=Home+Dark) |
| **Search & Filter**| ![Search Light](https://via.placeholder.com/150x300?text=Search+Light) | ![Search Dark](https://via.placeholder.com/150x300?text=Search+Dark) |
| **Categories** | ![Categories Light](https://via.placeholder.com/150x300?text=Cat+Light) | ![Categories Dark](https://via.placeholder.com/150x300?text=Cat+Dark) |
| **Product Details**| ![Details Light](https://via.placeholder.com/150x300?text=Details+Light) | ![Details Dark](https://via.placeholder.com/150x300?text=Details+Dark) |
| **Shopping Cart** | ![Cart Light](https://via.placeholder.com/150x300?text=Cart+Light) | ![Cart Dark](https://via.placeholder.com/150x300?text=Cart+Dark) |
| **Settings** | ![Settings Light](https://via.placeholder.com/150x300?text=Settings+Light) | ![Settings Dark](https://via.placeholder.com/150x300?text=Settings+Dark) |

---

## 🚀 Core Features

### 🏪 Dynamic Storefront
- **Intelligent Home**: Multi-layered dashboard featuring dynamic banners, category quick-links, and trending product grids.
- **Deep Search**: Advanced filtering system (Price, Brand, Category) with real-time feedback.
- **Rich Product Discovery**: Detailed product pages with high-res imagery (via Coil), specifications, and related items.

### 🔐 Secure Identity
- **Unified Authentication**: Integrated support for Google One Tap, Facebook Login, and standard Email/Password.
- **Guest Experience**: Anonymous browsing allowing users to explore before committing to an account.
- **Validation Chains**: Real-time UI feedback for secure and valid user input.

### 🛒 Commerce Engine
- **Stateful Cart**: Synchronized shopping cart across sessions.
- **Optimistic UI**: Fast, responsive interactions with loading feedback via custom shimmer effects.
- **Seamless Checkout**: Streamlined flow from selection to order placement.

---

## 🛠 Technical Excellence

### 🏗 Modular Architecture
The project is decoupled into **15 specialized modules** to achieve maximum build performance and code clarity:
- **Feature Isolation**: Each screen is its own module (e.g., `:feature-home`), preventing "God-module" bloat.
- **Core Abstraction**: Infrastructure like `:core-navigation` and `:core-session` ensures features stay agnostic of implementation details.

### 📡 Data & Networking
- **Apollo GraphQL**: Leveraging strongly-typed queries to interact with a Shopify-based backend.
- **Firebase Infrastructure**: Using Firestore for real-time document storage and Firebase Auth for identity.
- **Reactive Streams**: Utilizing `Kotlin Flow` and `StateFlow` to propagate data from the network layer directly to the UI.

### 🎨 UI & UX
- **Custom Design System**: A unified theme built on **Material 3**, centralized in `:core-ui`.
- **Performance Focused**: Lazy layouts and smart recomposition strategies for 60FPS scrolling.
- **Shimmer System**: A custom-built `Modifier` that provides high-quality skeleton loading across the app.

---

## 📂 Module Breakdown

| Tier | Module | Responsibility |
| :--- | :--- | :--- |
| **Feature** | `:feature-home` | Composable dashboard with dynamic promos and product sections. |
| | `:feature-details` | High-fidelity product information and review display. |
| | `:feature-cart` | Complex state management for item additions and quantity updates. |
| | `:feature-auth` | Multi-provider authentication logic and UI. |
| **Network**| `:network-apollo` | Centralized GraphQL client and auto-generated data models. |
| **Core** | `:core-navigation`| Type-safe navigator defining all app entry points. |
| | `:core-session` | Manages user identity, tokens, and active session state. |
| | `:core-ui` | Shared design tokens, typography, and reusable components. |

---

## 🛠 Local Setup

1. **Android Studio**: Version Ladybug (2024.2.1) or newer.
2. **Firebase**: Place your `google-services.json` in the `app/` folder.
3. **GraphQL**: Sync project to generate Apollo classes via `./gradlew generateApolloSources`.
4. **Run**: Select `:app` and click Run.

---

Developed with a focus on **Clean Code**, **Scalability**, and **Performance**.
