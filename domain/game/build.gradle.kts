plugins {
    alias(libs.plugins.karcags.domainModule)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.data.game)
            implementation(projects.data.settings)
        }
    }
}