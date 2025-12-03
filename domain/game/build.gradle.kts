plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "DomainGame"
            isStatic = true
        }
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.data.game)
            implementation(projects.data.settings)

            implementation(libs.koin.core)
        }
    }
}