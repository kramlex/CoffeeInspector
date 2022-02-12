enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}


val modules = listOf(
    // android
    ":androidApp",

    // shared
    ":shared",

    // features
    ":shared:features:profile",
)

rootProject.name = "CoffeeInspector"

includeBuild("build-logic")
modules.forEach { include(it)}