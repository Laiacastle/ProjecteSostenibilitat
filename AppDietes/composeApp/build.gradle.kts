import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("app.cash.sqldelight") version "2.0.2"
    kotlin("plugin.serialization") version "2.0.0"
}

kotlin {
    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.lifecycle.runtimeCompose)
                implementation("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0-beta01")
                implementation("com.russhwolf:multiplatform-settings-no-arg:1.3.0")
                implementation("com.russhwolf:multiplatform-settings-serialization:1.3.0")
                implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.2")
                implementation("io.ktor:ktor-client-core:3.0.2")
                implementation("io.ktor:ktor-client-cio:3.0.2")
                implementation("io.ktor:ktor-client-content-negotiation:3.0.2")
                implementation("io.coil-kt.coil3:coil-compose:3.1.0")
                implementation("io.coil-kt.coil3:coil-network-ktor3:3.1.0")
                implementation("app.cash.sqldelight:coroutines-extensions:2.0.2")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "org.project.dietes"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.project.dietes"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation("androidx.compose.runtime:runtime-android:1.8.1")
    implementation("androidx.navigation:navigation-compose-jvmstubs:2.9.0")
    implementation("androidx.compose.material3:material3:1.2.0") // versión corregida
    debugImplementation(compose.uiTooling)
}
