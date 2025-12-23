plugins {
    alias(libs.plugins.karcags.featureModule)
}

kotlin {
    sourceSets {
        androidMain.dependencies {

        }

        commonMain.dependencies {
            implementation(projects.ui.core)
        }

        jvmMain.dependencies {

        }
    }
}