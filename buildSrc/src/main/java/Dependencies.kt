object AppConfiguration {
    val androidCompileSdkVersion = 30
    val androidMinSdkVersion = 21
    val androidTargetSdkVersion = 30
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val kotlinVersion = "1.3.72"
    val retrofitVersion = "2.6.2"
    val okhttpLoggingInterceptorVersion = "3.12.1"
    val constraintLayoutVersion = "2.0.4"
    val lifecycleVersion = "2.2.0"
    val fragmentKtxVersion = "1.2.5"
    val appcompatVersion = "1.2.0"
    val ktxCoreVersion = "1.3.2"
    val materailDesign = "1.2.1"
    val coroutineVersion = "1.3.9"
    val roomVersion = "2.2.6"
    val glideVersion = "4.11.0"
    val recyclerviewVersion = "1.1.0"
    val hiltVersion = "2.28-alpha"
    val hiltAndroidXVersion = "1.0.0-alpha01"
    val shimmerVersion = "0.5.0"
    val swipeRefreshLayoutVersion = "1.1.0"
    val lottieVersion = "3.4.0"
}

object GradleDependencies {
    val androidGradleTools = "com.android.tools.build:gradle:4.1.0"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
}

object DevelopmentDependencies {

    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appcompatVersion}"
    val ktxCore = "androidx.core:core-ktx:${Versions.ktxCoreVersion}"
    val materailDesign = "com.google.android.material:material:${Versions.materailDesign}"
    val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"

    //    viewmodel ktx for viewmodelScope etc
    val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtxVersion}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    val okhttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLoggingInterceptorVersion}"

    //   this dependecy contains coroutines also Dispatcher.Main which is specialized for android
    val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineVersion}"

    val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"

    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerviewVersion}"

    val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltAndroidXVersion}"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
    val hiltAndroidXCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltAndroidXVersion}"

    val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmerVersion}"

    val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"

    val swipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayoutVersion}"
}


object TestDependencies {
    val junit = "junit:junit:4.+"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
    val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2"
}

object AndroidTestDependencies {
    val androidJUnit = "androidx.test.ext:junit:1.1.2"
    val espresseo = "androidx.test.espresso:espresso-core:3.3.0"
    val androidXCoreTesting = "androidx.arch.core:core-testing:2.1.0"

}
