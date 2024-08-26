plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.lesa.testjobfinderapp"
    compileSdk = libs.versions.sdk.compile.get().toInt()
    defaultConfig {
        applicationId = "com.lesa.testjobfinderapp"
        minSdk = libs.versions.sdk.min.get().toInt()
        targetSdk = libs.versions.sdk.target.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvm.target.get()
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // core:
    implementation(libs.androidx.activity)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)

    // di:
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // other:
    implementation(libs.kirich1409.viewbindingpropertydelegate)

    // modules:
    implementation(project(":core:navigation"))
    implementation(project(":core:ui_kit"))
    implementation(project(":screens:login"))
    implementation(project(":screens:verification"))
    implementation(project(":network"))
    implementation(project(":data"))
}
