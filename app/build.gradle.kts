plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.0-RC2"
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 31

    buildFeatures {
        dataBinding = true
    }

    defaultConfig {

        applicationId = "com.app.marvelapp"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {

    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.material)
    implementation(Dependencies.AndroidX.lifecycleExtensions)
    implementation(Dependencies.AndroidX.liveData)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.navigation)
    implementation(Dependencies.AndroidX.navigationKtk)
    implementation(Dependencies.Coroutines.coroutinesCore)
    implementation(Dependencies.Coroutines.coroutinesAndroid)
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.okHttp)
    implementation(Dependencies.Retrofit.serializationConverter)
    implementation(Dependencies.Retrofit.serializationKtx)
    implementation(Dependencies.Glide.glide)
    implementation(Dependencies.Di.koin)
    implementation(Dependencies.Database.room)
    kapt(Dependencies.Database.roomKapt)
    implementation(Dependencies.Database.roomKtx)

    testImplementation (Dependencies.Testing.androidXCore)
    testImplementation (Dependencies.Testing.coroutinesTest)
    testImplementation (Dependencies.Testing.jUnit)
    testImplementation (Dependencies.Testing.mockk)

    androidTestImplementation (Dependencies.Testing.androidxTest)
    androidTestImplementation (Dependencies.Testing.espresso)
}