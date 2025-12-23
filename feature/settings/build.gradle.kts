plugins {
    alias(libs.plugins.karcags.featureModule)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.ui.core)
            implementation(projects.domain.settings)
        }
    }
}