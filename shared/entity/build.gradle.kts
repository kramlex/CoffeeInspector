plugins {
    id("multiplatform-convention")
    id("kotlinx-serialization")
    id("com.rickclephas.kmp.nativecoroutines")
}

dependencies {
    commonMainImplementation(libs.coroutines)
    commonMainImplementation(libs.kotlinxDateTime)
    commonMainImplementation(libs.kotlinSerialization)
}