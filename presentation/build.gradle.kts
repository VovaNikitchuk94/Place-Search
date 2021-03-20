plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

dependencies {
    implementation(project(":domain"))

    implementation(Config.Libs.ktx)

    // DI
    implementation(Config.Libs.koin)
    implementation(Config.Libs.koinScope)
    implementation(Config.Libs.koinViewModel)

    // LiveData & ViewModel
    implementation(Config.Libs.lifecycle)
    implementation(Config.Libs.lifecycleViewmodel)
    implementation(Config.Libs.lifecycleLivedata)
    implementation(Config.Libs.lifecycleRuntime)

    // Navigation
    implementation(Config.Libs.navigationFragment)
    implementation(Config.Libs.navigationUi)

    // UI
    implementation(Config.Libs.constraintLayout)
    implementation(Config.Libs.materialComponents)
    implementation(Config.Libs.swipeRefreshLayout)
    implementation(Config.Libs.appcompat)
    implementation(Config.Libs.lottie)

    // Glide
    implementation(Config.Libs.glide)
    kapt(Config.Libs.glideCompiler)

    // Utils
    implementation(Config.Libs.timber)
}