plugins {
    id("multiplatform-convention")
    id("com.android.library")
    id("kotlinx-serialization")
    id("dev.icerock.moko.kswift")
    id("com.squareup.sqldelight")
    id("dev.icerock.mobile.multiplatform.ios-framework")
    id("com.rickclephas.kmp.nativecoroutines")
}

val iosDeploymentTarget = "14.1"

val mppModules = listOf(
    projects.shared.features.profile
)

val kSwiftModules = mppModules + listOf(
    projects.shared
)

kswift {
    install(dev.icerock.moko.kswift.plugin.feature.SealedToSwiftEnumFeature)
    kSwiftModules.forEach { includeLibrary(it.name) }
    projectPodspecName.set("MultiPlatformLibrary")
    iosDeploymentTarget.set(iosDeploymentTarget)
}

dependencies {
    // Common Test
    commonTestImplementation(libs.kotlinTestJUnit)
    commonTestImplementation(libs.ktorClientMock)
    commonTestImplementation(libs.multiplatformSettingsTest)

    // Common Main
    commonMainImplementation(libs.ktorClient)
    commonMainImplementation(libs.ktorClientLogging)
    commonMainImplementation(libs.coroutines)

    commonMainImplementation(libs.kotlinSerialization)
    commonMainImplementation(libs.kotlinxDateTime)

    commonMainApi(libs.multiplatformSettings)
    commonMainApi(libs.multiplatformSettingsCoroutine)
    commonMainApi(libs.multiplatformSettingsSerialization)


    mppModules.forEach { commonMainApi(it) }
}

framework {
    mppModules.forEach { export(it) }
    export(libs.multiplatformSettingsCoroutine)
    export(libs.multiplatformSettings)
}
