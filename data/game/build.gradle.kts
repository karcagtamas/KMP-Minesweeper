plugins {
    alias(libs.plugins.karcags.dataModule)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.data.core)
        }
    }
}