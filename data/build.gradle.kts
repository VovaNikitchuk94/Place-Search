plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    implementation(project(":api"))
    api(project(":config"))

    implementation(Config.Libs.koin)
    implementation(Config.Libs.koinCore)
    implementation(Config.Libs.rxKotlin)
    implementation(Config.Libs.locationService)
    implementation(Config.Libs.threeTenAbp)
    implementation(Config.Libs.timber)
    implementation(Config.Libs.stethoOkHttp)

    implementation(Config.Libs.retrofit)
    implementation(Config.Libs.retrofitGson)
    implementation(Config.Libs.retrofitRxJava)
    implementation(Config.Libs.okHtttp)
    implementation(Config.Libs.gson)
    implementation(Config.Libs.loggingInterceptor)

    implementation(Config.Libs.room)
    kapt(Config.Libs.roomCompiler)
    implementation(Config.Libs.roomKtx)

    testRuntimeOnly(Config.Libs.jUnitEngine)
    testImplementation(Config.Libs.mockWebServer)
    testImplementation(Config.Libs.jUnitApi)
    testImplementation(Config.Libs.jUnitParams)
    testImplementation(Config.Libs.mockitoCore)
}