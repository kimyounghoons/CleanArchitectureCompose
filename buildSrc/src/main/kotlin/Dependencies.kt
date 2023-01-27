object Versions {
    const val minSDK = 24
    const val targetSDK = 33

    const val androidxAppCompat = "1.6.0"
    const val androidxCoreKtx = "1.7.0"
    const val androidxRecyclerView = "1.2.1"
    const val androidMaterial = "1.8.0"
    const val androidxLifecycleExtensions = "2.2.0"

    const val kotlinStdLibJdk7 = "1.5.30"

    const val androidxHiltCompiler = "1.0.0"

    const val androidxViewModelKtx = "2.5.0"
    const val androidxLifecycleLiveData = "2.4.0"

    const val retrofit = "2.9.0"
    const val okHttp = "4.10.0"

    const val gson = "2.8.6"

    const val rxjava = "2.2.20"
    const val rxKotlin = "2.4.0"
    const val rxAndroid = "2.1.1"

    const val compose = "1.3.0"
    const val coil = "2.2.2"

    const val kotlinXCoroutinesAndroid = "1.6.1"
    const val kotlinXCoroutineCore = "1.5.0"


    //firebase
    const val firebaseCrashlytics = "18.2.3"

    //3rd library
    const val tedPermission = "2.2.2"
    const val hilt = "2.44"

    //glide
    const val glide = "4.12.0"
    const val glideCompiler = "4.12.0"

    //test
    const val testJunit = "4.13.2"
    const val androidXTestRunner = "1.4.0"
    const val testExtJunit = "1.1.5"
    const val androidXTestRules = "1.4.1-alpha07"
    const val testMockito = "3.7.7"
    const val testMockitoInline = "3.5.13"
    const val androidXArchCoreTesting = "2.1.0"
    const val powerMock = "2.0.9"
    const val testEspresso = "3.5.1"
    const val testKotlinCoroutineTest = "1.6.1"

}

object Libs {

    //AndroidX
    const val androidxAppcompat = "androidx.appcompat:appcompat:${Versions.androidxAppCompat}"
    const val androidxCoreKtx = "androidx.core:core-ktx:${Versions.androidxCoreKtx}"
    const val androidxRecyclerView = "androidx.recyclerview:recyclerview:${Versions.androidxRecyclerView}"
    const val androidxLifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.androidxLifecycleExtensions}"
    const val androidxHiltCompiler = "androidx.hilt:hilt-compiler:${Versions.androidxHiltCompiler}"
    const val androidxViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidxViewModelKtx}"
    const val androidxLifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidxLifecycleLiveData}"

    const val kotlinStdLibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinStdLibJdk7}"

    const val androidMaterial = "com.google.android.material:material:${Versions.androidMaterial}"

    //이미지 로딩 라이브러리
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideCompiler}"

    //hilt DI 라이브러리
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val javaxInject = "javax.inject:javax.inject:1"


    const val kotlinXCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinXCoroutinesAndroid}"
    const val kotlinXCoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinXCoroutineCore}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConvertGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitConvertSimpleXml = "com.squareup.retrofit2:converter-simplexml:${Versions.retrofit}"
    const val retrofitRxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"

    const val okHttp  = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttp3LoggingIntercepter = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

    //Compose
    const val composeRuntimeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coil}"

    //Test
    const val testJunit = "junit:junit:${Versions.testJunit}"
    const val androidXTestRunner = "androidx.test:runner:${Versions.androidXTestRunner}"

    const val testMockito = "org.mockito:mockito-core:${Versions.testMockito}"
    const val testMockitoInline = "org.mockito:mockito-inline:${Versions.testMockitoInline}"
    const val androidXArchCoreTesting ="androidx.arch.core:core-testing:${Versions.androidXArchCoreTesting}"

    const val testPowerMockCore = "org.powermock:powermock-core:${Versions.powerMock}"
    const val testPowerMockModuleJunit = "org.powermock:powermock-module-junit4:${Versions.powerMock}"
    const val testPowerMockApi = "org.powermock:powermock-api-mockito2:${Versions.powerMock}"

    const val testExtJunit = "androidx.test.ext:junit:${Versions.testExtJunit}"
    const val androidXTestRules = "androidx.test:rules:${Versions.androidXTestRules}"
    const val testEspresso = "androidx.test.espresso:espresso-core:${Versions.testEspresso}"
    const val testEspressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.testEspresso}"
    const val testKotlinCoroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.testKotlinCoroutineTest}"

}