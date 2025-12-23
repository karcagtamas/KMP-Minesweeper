plugins {
    alias(libs.plugins.karcags.dataModule)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.datastore.preferences.core)
        }
    }
}