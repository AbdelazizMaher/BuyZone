# 🛒 BuyZone - Premier E-Commerce Android Suite

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack_Compose-2024.09.00-green.svg?style=flat&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![Koin](https://img.shields.io/badge/Koin-4.0.2-orange.svg?style=flat&logo=koin)](https://insert-koin.io/)
[![Apollo](https://img.shields.io/badge/Apollo-GraphQL-purple.svg?style=flat&logo=apollographql)](https://www.apollographql.com/docs/kotlin/)

**BuyZone** is a production-grade, multi-module Android application engineered with modern architectural patterns. It serves as a comprehensive demonstration of high-performance E-Commerce functionality, utilizing a robust tech stack including Jetpack Compose, Apollo GraphQL, and Firebase.

---

## 🏗 High-Level Architecture

BuyZone follows a **Modular Clean Architecture** approach, ensuring high decoupling and scalability. The project is divided into layers: **Core Infrastructure**, **Feature Modules**, and the **App Orchestrator**.

### Module Dependency Graph

```mermaid
graph TD
    subgraph App_Layer [App Orchestrator]
        App[":app"]
    end

    subgraph Feature_Layer [Feature Modules]
        Auth[":feature-authentication"]
        Home[":feature-home"]
        Cart[":feature-cart"]
        Details[":feature-details"]
        Search[":feature-search"]
        Cat[":feature-categories"]
        Settings[":feature-settings"]
        Onboarding[":feature-onboarding"]
        Splash[":feature-splash"]
    end

    subgraph Core_Layer [Core Infrastructure]
        Nav[":core-navigation"]
        UI[":core-ui"]
        Session[":core-session"]
        Common[":core-common"]
        Apollo[":network-apollo"]
    end

    App --> Feature_Layer
    Feature_Layer --> Core_Layer
    App --> Core_Layer
```

---

## 🚦 Design Pattern: MVI (Model-View-Intent)

The presentation layer utilizes the **MVI pattern** to ensure a predictable state and unidirectional data flow (UDF).

### MVI Workflow

```mermaid
sequenceDiagram
    participant User as User / UI
    participant VM as ViewModel
    participant UC as UseCase
    participant Repo as Repository

    User->>VM: Sends Intent (e.g., Click Login)
    VM->>VM: Updates State (Loading = true)
    VM->>UC: Execute Business Logic
    UC->>Repo: Fetch Data (GraphQL/Firebase)
    Repo-->>UC: Return Result
    UC-->>VM: Return Data/Error
    VM->>VM: Updates State (Loading = false, Data)
    VM-->>User: Emits State (UI Renders)
    VM-->>User: Emits Side-Effect (e.g., Navigate, Toast)
```

---

## 🛠 Tech Stack Details

### Core Technologies
- **Jetpack Compose**: 100% declarative UI with Material 3.
- **Koin**: Dependency injection throughout all modules for efficient lifecycle management.
- **Coroutines & Flow**: Handling asynchronous data streams and reactive state updates.

### Data Layer
- **Apollo GraphQL**: Integration with Shopify/custom GraphQL backends, providing type-safe query generation and optimized networking.
- **Firebase Suite**: 
    - **Authentication**: Social login (Google, Facebook) and traditional email/password.
    - **Firestore**: NoSQL real-time database for user profiles and session data.
- **Coil**: Advanced image loading with custom shimmer placeholders.

### Navigation
- **Navigation Compose**: Type-safe navigation routes across modules, managed centrally in `:core-navigation`.

---

## 📂 Deep Dive: Module Responsibilities

| Module | Purpose | Key Components |
| :--- | :--- | :--- |
| **`:app`** | Orchestrator | Dependency wiring, Main Activity, App-wide navigation host. |
| **`:core-ui`** | Design System | Theme (Typography, Colors), Custom Modifiers (Shimmer), Base Components. |
| **`:core-common`** | Shared Business | Reusable UI (Product Cards, Section Headers), Domain Error handling. |
| **`:network-apollo`**| Data Access | GraphQL schema definitions, Apollo Client setup, Generated data models. |
| **`:feature-home`** | Discovery | Multi-section dashboard (Promos, Trending, Brands). |
| **`:feature-auth`** | Identity | Login/Signup flows, Validation chains, Social auth handlers. |

---

## 🚀 Key Technical Features

### 🧩 Custom Validation Chains
Implemented a robust validation system for authentication:
- **`ValidationHandler`**: Decouples validation logic from ViewModels.
- **`Email/PasswordValidationChain`**: Fluent API for complex input requirements.

### ✨ Shimmer Loading System
Custom-built shimmer modifiers in `:core-ui` integrated with `:core-common` components, providing a seamless loading experience during GraphQL fetches.

### 🌐 Social Auth Integration
Built-in support for multiple identity providers:
- **Google One Tap API** integration.
- **Facebook SDK** integration.
- **Guest Access** mode for low-friction exploration.

---

## 🛠 Local Setup

1. **Environment**: Ensure you have Android Studio Ladybug+ installed.
2. **Firebase**:
   - Add `google-services.json` to the `app/` directory.
   - Configure SHA-1/SHA-256 for Social Login in Firebase Console.
3. **GraphQL**:
   - The project uses Apollo. Schema is located in `:network-apollo`.
   - Run `./gradlew generateApolloSources` to generate types.
4. **Build**:
   ```bash
   ./gradlew assembleDebug
   ```

---

Developed with a focus on **Clean Code**, **SOLID Principles**, and **Performance**.
