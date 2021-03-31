import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${Config.Versions.gradlePlugin}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Config.Versions.acNavigation}")
        classpath(kotlin("gradle-plugin", Config.Versions.kotlinLanguage))
        classpath("com.google.gms:google-services:${Config.Versions.googlePlayServices}")
        classpath("org.koin:koin-gradle-plugin:${Config.Versions.koin}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }
}

subprojects {
    project.plugins.configure(project)

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

fun PluginContainer.configure(project: Project) {
    whenPluginAdded {
        when (this) {
            is AppPlugin -> project.extensions.getByType<AppExtension>().applyCommons()
            is LibraryPlugin -> project.extensions.getByType<LibraryExtension>().applyCommons()
        }
    }
}

fun AppExtension.applyCommons() {
    compileSdkVersion(Config.Android.compileSdkVersion)

    defaultConfig.apply {
        minSdkVersion(Config.Android.minSdkVersion)
        targetSdkVersion(Config.Android.targetSdkVersion)
        versionCode = Config.Android.versionCode
        versionName = Config.Android.versionName
    }

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

fun LibraryExtension.applyCommons() {
    compileSdkVersion(Config.Android.compileSdkVersion)

    defaultConfig.apply {
        minSdkVersion(Config.Android.minSdkVersion)
        targetSdkVersion(Config.Android.targetSdkVersion)
        versionCode = Config.Android.versionCode
        versionName = Config.Android.versionName

        consumerProguardFiles("proguard-rules.pro")
    }

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(rootProject.buildDir)
    }
}