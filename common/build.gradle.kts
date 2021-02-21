import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "0.3.0-build152"
    id("com.android.library")
    id("kotlin-parcelize")
}

group = "com.darkrockstudios.apps"
version = "1.0"

repositories {
    google()
    jcenter()
}

dependencies {
    implementation("com.arkivanov.decompose:decompose:0.1.8")
}

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "14"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.ui)

                implementation("com.arkivanov.decompose:decompose:0.1.8")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:0.1.8")
            }
        }
        val commonTest by getting {

        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.appcompat:appcompat:1.2.0")
                implementation("androidx.core:core-ktx:1.3.2")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.1")
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:0.1.8")
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
    }
}