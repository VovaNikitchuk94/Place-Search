plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":api"))

    implementation(Config.Libs.rxKotlin)
    implementation(Config.Libs.threeTenAbp)
}