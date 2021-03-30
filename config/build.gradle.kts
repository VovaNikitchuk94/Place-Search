plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {

    defaultConfig {
        buildConfigField("String", "FOURSQUARE_BASE_URL", "\"${Secret.FOURSQUARE_BASE_URL}\"")
        buildConfigField("String", "FOURSQUARE_CLIENT_ID", "\"${Secret.FOURSQUARE_CLIENT_ID}\"")
        buildConfigField("String", "FOURSQUARE_SECRET", "\"${Secret.FOURSQUARE_SECRET}\"")

        buildConfigField("String", "GOOGLE_MAPS_BASE_URL", "\"${Secret.GOOGLE_MAPS_BASE_URL}\"")
        buildConfigField("String", "GOOGLE_MAPS_API_KEY", "\"${Secret.GOOGLE_MAPS_KEY}\"")
    }
}

dependencies {
    implementation(Config.Libs.kotlin)
}