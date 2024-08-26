plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // Core:
    api(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.core)

    // Network:
    api(libs.okhttp3.logging.interceptor)
    api(libs.okhttp3.okhttp)
    implementation(libs.retrofit.adapters.result)
    implementation(libs.retrofit2.converter.kotlinx.serialization)
    implementation(libs.retrofit2.retrofit)
}
