plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(AppConfiguration.androidCompileSdkVersion)

    defaultConfig {
        applicationId = "com.roshaan.githubapp"
        minSdkVersion(AppConfiguration.androidMinSdkVersion)
        targetSdkVersion(AppConfiguration.androidTargetSdkVersion)
        versionCode = AppConfiguration.versionCode
        versionName = AppConfiguration.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    buildFeatures {

        viewBinding = true
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

    implementation(DevelopmentDependencies.kotlinStdLib)
    implementation(DevelopmentDependencies.ktxCore)
    implementation(DevelopmentDependencies.appCompat)
    implementation(DevelopmentDependencies.materailDesign)
    implementation(DevelopmentDependencies.constraintLayout)
    implementation(DevelopmentDependencies.recyclerView)

    implementation(DevelopmentDependencies.viewModelKtx)
    implementation(DevelopmentDependencies.fragmentKtx)
    implementation(DevelopmentDependencies.coroutinesAndroid)

    implementation(DevelopmentDependencies.retrofit)
    implementation(DevelopmentDependencies.retrofitGsonConverter)
    implementation(DevelopmentDependencies.okhttpLoggingInterceptor)

    implementation(DevelopmentDependencies.glide)

    implementation(DevelopmentDependencies.roomKtx)
    kapt(DevelopmentDependencies.roomCompiler)

    implementation(DevelopmentDependencies.hilt)
    implementation(DevelopmentDependencies.hiltViewModel)
    kapt(DevelopmentDependencies.hiltAndroidXCompiler)
    kapt(DevelopmentDependencies.hiltCompiler)

    implementation(DevelopmentDependencies.shimmer)

    implementation(DevelopmentDependencies.lottie)

    implementation(DevelopmentDependencies.swipeRefreshLayout)

    testImplementation(TestDependencies.junit)
    androidTestImplementation(AndroidTestDependencies.androidJUnit)
    androidTestImplementation(AndroidTestDependencies.espresseo)
}