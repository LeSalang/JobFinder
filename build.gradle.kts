import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.detekt) apply true
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
}

allprojects.onEach {
    it.afterEvaluate {
        with(project.plugins) {
            if (hasPlugin(libs.plugins.kotlin.android.get().pluginId)) {
                apply(libs.plugins.detekt.get().pluginId)
                extensions.configure<DetektExtension> {
                    config.setFrom(rootProject.files("detekt-config.yml"))
                }
                dependencies.add(
                    "detektPlugins",
                    libs.detekt.formatting.get()
                        .toString()
                )
            }
        }
    }
}
