plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(Config.Libs.ktx)
    implementation(Config.Libs.maps)

    implementation(Config.Libs.rxAndroid)
    implementation(Config.Libs.rxKotlin)
    implementation(Config.Libs.rxBinding)
    implementation(Config.Libs.rxRelay)

    implementation(Config.Libs.koin)
    implementation(Config.Libs.koinScope)
    implementation(Config.Libs.koinViewModel)

    implementation(Config.Libs.lifecycle)
    implementation(Config.Libs.lifecycleViewmodel)
    implementation(Config.Libs.lifecycleLivedata)
    implementation(Config.Libs.lifecycleRuntime)
    implementation(Config.Libs.navigationFragment)
    implementation(Config.Libs.navigationUi)

    implementation(Config.Libs.constraintLayout)
    implementation(Config.Libs.materialComponents)
    implementation(Config.Libs.swipeRefreshLayout)
    implementation(Config.Libs.appcompat)
    implementation(Config.Libs.lottie)
    implementation(Config.Libs.viewBindingDelegate)
    implementation(Config.Libs.picasso)
    implementation(Config.Libs.picassoTransformations)
    implementation(Config.Libs.quickPermissions)

    implementation(Config.Libs.timber)
}