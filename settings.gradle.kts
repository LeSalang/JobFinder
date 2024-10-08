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

rootProject.name = "TestJobFinderApp"
include(":app")
include(":core:ui_kit")
include(":screens:login")
include(":screens:verification")
include(":core:navigation")
include(":screens:search")
include(":network")
include(":data")
include(":screens:vacancy")
include(":screens:favourites")
