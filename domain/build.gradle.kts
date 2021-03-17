plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    api(project(":api"))

    implementation(Config.Libs.rxKotlin)
    implementation(Config.Libs.threeTenAbp)
}