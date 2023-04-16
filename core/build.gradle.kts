plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "net.deali.core"
    compileSdk = Versions.targetSDK

    defaultConfig {
        minSdk = Versions.minSDK
        targetSdk = Versions.targetSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += listOf(
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${rootProject.file(".").absolutePath}/report/compose-reports/${namespace}"
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(path = ":coredomain", configuration = "default"))
    implementation(Libs.coilCompose)
    implementation(Libs.androidxCoreKtx)
    implementation(Libs.androidxAppcompat)
    implementation(Libs.androidMaterial)
    implementation(Libs.androidxLifecycleLifecycleRuntimeKtx)
    implementation(Libs.androidxActivityCompose)
    implementation(Libs.androidXComposeUi)
    implementation(Libs.androidXComposeUiToolingPreview)
    implementation(Libs.androidXComposeMaterial)
    implementation(Libs.accompanistPager)
    implementation(Libs.accompanistPagerIndicators)
    implementation(Libs.constraintLayoutCompose)

    testImplementation(Libs.testJunit)
    androidTestImplementation(Libs.testExtJunit)
    androidTestImplementation(Libs.testEspresso)
    androidTestImplementation(Libs.testAndroidXComposeUiTestJunit)
    debugImplementation(Libs.testAndroidXComposeUiTooling)
    debugImplementation(Libs.testAndroidXComposeUiTestManifest)
}