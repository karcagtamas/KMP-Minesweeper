import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
}

val versionPropertiesInputsStream = FileInputStream("$rootDir/versions.properties")
val versionProperties = Properties().apply {
    load(versionPropertiesInputsStream)
}
val versionCodeProperty = versionProperties.getProperty("versionCode").toInt()
val versionMajorProperty = versionProperties.getProperty("versionMajor").toInt()
val versionMinorProperty = versionProperties.getProperty("versionMinor").toInt()
val versionPatchProperty = versionProperties.getProperty("versionPatch").toInt()

val versionNameProperty = "$versionMajorProperty.$versionMinorProperty.$versionPatchProperty"

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm()

    sourceSets {
        androidMain.dependencies {

        }
        commonMain.dependencies {
            implementation(projects.ui.core)
            implementation(projects.feature.menu)
            implementation(projects.feature.settings)
            implementation(projects.feature.highscores)
            implementation(projects.feature.play)

            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.koin.core)
            implementation(libs.bundles.koin.compose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {

        }
    }
}

android {
    namespace = "eu.karcags.minesweeper"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "eu.karcags.minesweeper"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = versionCodeProperty
        versionName = versionNameProperty
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "eu.karcags.minesweeper.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "eu.karcags.minesweeper"
            packageVersion = versionNameProperty
            description = "Minesweeper by KarcagS"
            copyright = "2025 KarcagS"
            licenseFile.set(project.file("../LICENSE.txt"))

            macOS {
                dockName = "Minesweeper"
                entitlementsFile.set(project.file("default.entitlements"))
            }
        }

        buildTypes.release {
            proguard {
                obfuscate.set(true)
                configurationFiles.from("proguard-rules.pro")
            }
        }
    }
}
