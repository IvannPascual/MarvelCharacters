
private const val koinVersion = "3.1.3"
private const val roomVersion = "2.3.0"
private const val materialVersion = "1.4.0"
private const val lifeCycleVersion = "2.2.0"
private const val liveDataVersion = "2.4.0"
private const val constrainVersion = "2.1.0"
private const val coreKtxVersion = "1.7.0"
private const val appCompatVersion = "1.3.1"
private const val navigationVersion = "2.3.5"
private const val coroutinesVersion = "1.5.1"
private const val retrofitVersion = "2.9.0"
private const val okHttpVersion = "4.9.1"
private const val serializationConverterVersion = "0.8.0"
private const val serializationVersion = "1.1.0"
private const val glideVersion = "4.12.0"

const val coreAndroidXTestVersion = "2.1.0"
const val coroutinesTestVersion = "1.5.1"
const val jUnitVersion = "4.12"
const val mockkVersion = "1.12.0"
const val androidXTestVersion = "1.1.3"
const val espressoCoreVersion = "4.12.0"

class Dependencies {

    object Di {
        const val koin = "io.insert-koin:koin-android:$koinVersion"
    }

    object Database {
        const val room = "androidx.room:room-runtime:$roomVersion"
        const val roomKapt =  "androidx.room:room-compiler:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    }

    object AndroidX {
        const val material =  "com.google.android.material:material:$materialVersion"
        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:$lifeCycleVersion"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$liveDataVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constrainVersion"
        const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
        const val navigation = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationKtk = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
    }

    object Coroutines {
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    }
    
    object Retrofit {
        const val retrofit =  "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val okHttp =  "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
        const val serializationConverter =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$serializationConverterVersion"
        const val serializationKtx = "org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:$glideVersion"
    }

    object Testing {
        const val androidXCore = "androidx.arch.core:core-testing:$coreAndroidXTestVersion"
        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion"
        const val jUnit = "junit:junit:$jUnitVersion"
        const val mockk = "io.mockk:mockk:$mockkVersion"
        const val androidxTest = "androidx.test.ext:junit:$androidXTestVersion"
        const val espresso = "androidx.test.espresso:espresso-core:$espressoCoreVersion"
    }


}