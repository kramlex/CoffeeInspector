plugins {
    id("com.rickclephas.kmp.nativecoroutines") version "0.11.2" apply false
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
    dependencies {
        classpath(libs.kotlinSerializationGradle)
        classpath(libs.kswiftGradle)
        classpath(libs.sqlDelightGradle)
        classpath(":build-logic")
        classpath("com.android.tools.build:gradle:7.1.0")
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}