plugins {
    `kotlin-dsl`
}

dependencies {

    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.android.gradle.plugin)

}

gradlePlugin {
    plugins {
        register("kotlinMultipaltform") {
            id = libs.plugins.karcags.kotlinMultiplatform.get().pluginId
            implementationClass = "KotlinMultiplatformConventionPlugin"
        }

        register("androidLibrary") {
            id = libs.plugins.karcags.androidLibrary.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("domainModule") {
            id = libs.plugins.karcags.domainModule.get().pluginId
            implementationClass = "DomainModuleConventionPlugin"
        }

        register("dataModule") {
            id = libs.plugins.karcags.dataModule.get().pluginId
            implementationClass = "DataModuleConventionPlugin"
        }

        register("featureModule") {
            id = libs.plugins.karcags.featureModule.get().pluginId
            implementationClass = "FeatureModuleConventionPlugin"
        }
    }
}