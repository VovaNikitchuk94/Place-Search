object Config {

    object Android {

        const val versionName = "1.0.0"
        const val versionCode = 1
        const val applicationId = "com.vnykyt.placesearch"

        const val minSdkVersion = 24
        const val targetSdkVersion = 30
        const val compileSdkVersion = 30
    }

    object Versions {

        // Base
        const val gradlePlugin = "4.1.1"
        const val kotlinLanguage = "1.4.31"
        const val googlePlayServices = "4.3.5"

        // DI
        const val koin = "2.2.2"

        // RxJava
        const val rxJava = "3.0.11"
        const val rxAndroid = "3.0.0"
        const val rxBinding = "4.0.0"
        const val rxKotlin = "3.0.1"
        const val rxRelay = "3.0.0"

        // Arch components
        const val acLifecycle = "2.2.0"
        const val acNavigation = "2.3.4"

        // UI
        const val materialComponents = "1.2.0"
        const val swipeRefreshLayout = "1.0.0"
        const val appcompat = "1.2.0"
        const val fragmentKtx = "1.3.0"
        const val constraintLayout = "2.0.4"
        const val lottie = "3.6.1"
        const val viewBindingDelegate = "1.4.4"
        const val picasso = "2.71828"
        const val picassoTransformations = "2.2.1"

        // Network
        const val gson = "2.8.6"
        const val loggingInterceptor = "3.1.0"
        const val okHttp = "4.7.2"
        const val retrofit = "2.9.0"

        // Room
        const val room = "2.2.5"

        // Utils
        const val ktx = "1.3.2"
        const val threeTenAbp = "1.2.1"
        const val timber = "4.7.1"
        const val quickPermissions = "0.4.1"

        // Google
        const val locationService = "18.0.0"
        const val maps = "17.0.0"

        // Development
        const val stetho = "1.5.1"

        // Test
        const val jUnit = "5.6.2"
        const val mockitoCore = "3.3.3"
        const val mockitoKotlin = "2.2.0"
    }

    object Libs {

        // Kotlin
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinLanguage}"
        const val ktx = "androidx.core:core-ktx:${Versions.ktx}"

        // DI
        const val koin = "org.koin:koin-android:${Versions.koin}"
        const val koinCore = "org.koin:koin-core:${Versions.koin}"
        const val koinScope = "org.koin:koin-android-scope:${Versions.koin}"
        const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"

        // Arch components
        const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.acLifecycle}"
        const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.acLifecycle}"
        const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata:${Versions.acLifecycle}"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.acLifecycle}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.acNavigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.acNavigation}"

        // UI
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val materialComponents = "com.google.android.material:material:${Versions.materialComponents}"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
        const val viewBindingDelegate = "com.github.kirich1409:viewbindingpropertydelegate:${Versions.viewBindingDelegate}"
        const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
        const val picassoTransformations = "jp.wasabeef:picasso-transformations:${Versions.picassoTransformations}"

        // RxJava
        const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroid}"
        const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:${Versions.rxKotlin}"
        const val rxBinding = "com.jakewharton.rxbinding4:rxbinding:${Versions.rxBinding}"
        const val rxRelay = "com.jakewharton.rxrelay3:rxrelay:${Versions.rxRelay}"

        // Network
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
        const val loggingInterceptor = "com.github.ihsanbal:LoggingInterceptor:${Versions.loggingInterceptor}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
        const val okHtttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val retrofitRxJava = "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit}"

        // Room
        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

        // Utils
        const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTenAbp}"
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        const val quickPermissions = "com.github.quickpermissions:quickpermissions-kotlin:${Versions.quickPermissions}"

        // Google
        const val locationService = "com.google.android.gms:play-services-location:${Versions.locationService}"
        const val maps = "com.google.android.gms:play-services-maps:${Versions.maps}"

        // Development
        const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
        const val stethoOkHttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"

        // Tests
        const val jUnitApi = "org.junit.jupiter:junit-jupiter-api:${Versions.jUnit}"
        const val jUnitEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.jUnit}"
        const val jUnitParams = "org.junit.jupiter:junit-jupiter-params:${Versions.jUnit}"
        const val mockitoCore = "org.mockito:mockito-inline:${Versions.mockitoCore}"
    }
}