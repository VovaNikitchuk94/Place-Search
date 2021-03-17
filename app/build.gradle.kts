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
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    implementation(Config.Libs.timber)
    implementation(Config.Libs.rxAndroid)
    implementation(Config.Libs.stetho)
}