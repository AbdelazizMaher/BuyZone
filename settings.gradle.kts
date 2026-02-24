pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BuyZone"
include(":app")
include(":feature-home")
include(":feature-onboarding")
include(":feature-authentication")
include(":core-ui")
include(":core-navigation")
include(":network-apollo")
include(":core-session")
include(":core-common")
include(":feature-search")
include(":feature-splash")
include(":feature-details")
include(":feature-categories")
