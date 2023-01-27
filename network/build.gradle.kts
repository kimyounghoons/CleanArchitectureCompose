plugins {
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "net.deali.network"
    compileSdk = Versions.targetSDK

    defaultConfig {
        minSdk = Versions.minSDK
        targetSdk = Versions.targetSDK
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "TMDB_API_KEY", "\"${System.getenv("TMDB_API_KEY")}\"")
        }

        debug {
            buildConfigField("String", "TMDB_API_KEY", "\"${System.getenv("TMDB_API_KEY")}\"")
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
    implementation(Libs.retrofit)
    implementation(Libs.retrofitRxjavaAdapter)
    implementation(Libs.retrofitConvertGson)
    implementation(Libs.okHttp)
    implementation(Libs.okHttp3LoggingIntercepter)
    implementation(Libs.gson)
    implementation(Libs.hiltAndroid)
    kapt(Libs.hiltCompiler)
}
kapt {
    correctErrorTypes = true
}