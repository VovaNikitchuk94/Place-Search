plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Config.Android.compileSdkVersion)

    defaultConfig {
        applicationId = Config.Android.applicationId
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        manifestPlaceholders(
            mapOf("GOOGLE_MAPS_API_KEY" to Secret.GOOGLE_MAPS_KEY)
        )
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    implementation(Config.Libs.koin)
    implementation(Config.Libs.timber)
    implementation(Config.Libs.rxAndroid)
    implementation(Config.Libs.stetho)
    implementation(Config.Libs.appcompat)
}