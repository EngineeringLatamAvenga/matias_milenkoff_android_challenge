plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.mtmilenkoff.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        val map_key: String by project
        buildConfigField("String", "map_key", map_key)
    }


    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    packaging {
        resources {
            excludes.add("/META-INF/*")
            excludes.add("**/*")
        }
    }
}

dependencies {
    implementation(project(":domain"))
    ksp(libs.room.compiler)
    ksp(libs.hilt.compiler)
    implementation(libs.bundles.network)
    implementation(libs.bundles.hilt)
    implementation(libs.bundles.room)
}
