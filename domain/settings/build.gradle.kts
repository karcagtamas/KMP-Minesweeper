plugins {
    alias(libs.plugins.karcags.domainModule)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.data.settings)

            implementation(libs.bundles.kotlin)
        }
    }
}