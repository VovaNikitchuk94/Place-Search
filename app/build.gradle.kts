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
        testInstrumentationRunner =  "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("Boolean", "IS_MONITORING_ENABLED", "true")
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    api(project(":data"))
    api(project(":domain"))
    api(project(":presentation"))

    // Development
    implementation(Config.Libs.stetho)
}