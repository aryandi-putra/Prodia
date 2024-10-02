plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.aryandi.prodia"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.aryandi.prodia"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        addManifestPlaceholders(
            mapOf(
                "auth0Domain" to "dev-6svay1o461286x71.us.auth0.com",
                "auth0Scheme" to "demo"
            )
        )

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "SPACEFLIGHT_NEWS_BASE_URL", "\"https://api.spaceflightnewsapi.net\"")
            buildConfigField("String", "SPACEFLIGHT_NEWS_API_VERSION", "\"v4\"")
            buildConfigField("String", "COM_AUTH0_DOMAIN", "dev-6svay1o461286x71.us.auth0.com")
            buildConfigField("String", "COM_AUTH0_CLIENTID", "vLrnt38w7QxlLhvz2t1Tp6RDrRi4OIGP")
        }
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "SPACEFLIGHT_NEWS_BASE_URL", "\"https://api.spaceflightnewsapi.net\"")
            buildConfigField("String", "SPACEFLIGHT_NEWS_API_VERSION", "\"v4\"")
            buildConfigField("String", "COM_AUTH0_DOMAIN", "\"dev-6svay1o461286x71.us.auth0.com\"")
            buildConfigField("String", "COM_AUTH0_CLIENTID", "\"vLrnt38w7QxlLhvz2t1Tp6RDrRi4OIGP\"")
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.auth0)

    // Networking
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    implementation(libs.joda.time)
    implementation(libs.coil.compose)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}