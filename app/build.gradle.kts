plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp")
}

android {

    namespace = "com.example.anthar_jalawatch"

    compileSdk = 36

    defaultConfig {

        applicationId = "com.example.anthar_jalawatch"

        minSdk = 26

        targetSdk = 36

        versionCode = 1

        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        release {

            isMinifyEnabled = false

            proguardFiles(

                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),

                "proguard-rules.pro"
            )
        }
    }

    compileOptions {

        sourceCompatibility =
            JavaVersion.VERSION_11

        targetCompatibility =
            JavaVersion.VERSION_11
    }

    kotlinOptions {

        jvmTarget = "11"
    }

    buildFeatures {

        compose = true
    }

    composeOptions {

        kotlinCompilerExtensionVersion =
            "1.5.14"
    }
}

dependencies {

    // MATERIAL ICONS
    implementation(
        "androidx.compose.material:material-icons-extended"
    )

    // GOOGLE MAPS
    implementation(
        "com.google.android.gms:play-services-maps:19.0.0"
    )

    implementation(
        "com.google.maps.android:android-maps-utils:3.8.2"
    )

    implementation(
        "com.google.maps.android:maps-compose:4.3.3"
    )

    implementation(
        "com.google.android.gms:play-services-location:21.3.0"
    )

    // GOOGLE SIGN-IN
    implementation(
        "com.google.android.gms:play-services-auth:21.2.0"
    )

    // ANDROID CORE
    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.activity.compose)

    // COMPOSE
    implementation(
        platform(libs.androidx.compose.bom)
    )

    implementation(libs.androidx.compose.ui)

    implementation(libs.androidx.compose.ui.graphics)

    implementation(
        libs.androidx.compose.ui.tooling.preview
    )

    implementation(libs.androidx.compose.material3)

    // NAVIGATION
    implementation(
        "androidx.navigation:navigation-compose:2.7.7"
    )

    // VIEWMODEL
    implementation(
        "androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0"
    )

    // FIREBASE
    implementation(
        platform(
            "com.google.firebase:firebase-bom:33.15.0"
        )
    )

    implementation(
        "com.google.firebase:firebase-auth-ktx"
    )

    implementation(
        "com.google.firebase:firebase-firestore-ktx"
    )

    implementation(
        "com.google.firebase:firebase-database-ktx"
    )

    // ROOM DATABASE
    implementation(
        "androidx.room:room-runtime:2.6.1"
    )

    implementation(
        "androidx.room:room-ktx:2.6.1"
    )

    ksp(
        "androidx.room:room-compiler:2.6.1"
    )

    // CHARTS
    implementation(
        "com.github.PhilJay:MPAndroidChart:v3.1.0"
    )

    // TESTING
    testImplementation(libs.junit)

    androidTestImplementation(
        libs.androidx.junit
    )

    androidTestImplementation(
        libs.androidx.espresso.core
    )

    androidTestImplementation(
        platform(libs.androidx.compose.bom)
    )

    androidTestImplementation(
        libs.androidx.compose.ui.test.junit4
    )

    debugImplementation(
        libs.androidx.compose.ui.tooling
    )

    debugImplementation(
        libs.androidx.compose.ui.test.manifest
    )
}