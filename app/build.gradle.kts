plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
}

// Retrieve key for api
fun getApiKey(): String {
    val items = HashMap<String, String>()
    val f = File("apikey.properties")

    f.forEachLine {
        items[it.split("=")[0]] = it.split("=")[1]
    }

    return items["API_KEY"]!!
}

android {
    compileSdkVersion(Sdk.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(Sdk.MIN_SDK_VERSION)
        targetSdkVersion(Sdk.TARGET_SDK_VERSION)
        applicationId = AppCoordinates.APP_ID
        versionCode = AppCoordinates.APP_VERSION_CODE
        versionName = AppCoordinates.APP_VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "API_KEY", getApiKey())
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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

    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))

    //Android libs
    implementation(Libraries.AndroidLibs.ANDROIDX_APPCOMPAT)
    implementation(Libraries.AndroidLibs.ANDROIDX_CONSTRAINT_LAYOUT)
    implementation(Libraries.AndroidLibs.ANDROIDX_CORE_KTX)
    implementation(Libraries.AndroidLibs.ANDROIDX_VIEWMODEL)
    implementation(Libraries.AndroidLibs.ANDROIDX_LIVEDATA)
    kapt(Libraries.AndroidLibs.ANDROIDX_LIFECYCLE_COMPILER)
    implementation(Libraries.AndroidLibs.MATERIAL)
    implementation(Libraries.AndroidLibs.ANDROIDX_NAVIGATION_UI)
    implementation(Libraries.AndroidLibs.ANDROIDX_NAVIGATION_FRAGMENT)
    implementation(Libraries.AndroidLibs.ANDROIDX_RECYCLER_VIEW)
    implementation(Libraries.AndroidLibs.PALETTE)
    implementation(Libraries.AndroidLibs.LEGACY)

    //Glide
    implementation(Libraries.Utility.GLIDE)
    kapt(Libraries.Utility.GLIDE_COMPILER)

    //Concurrency
    implementation(Libraries.Concurrency.COROUTINES)


    //Networking
    implementation(Libraries.Networking.GSON)
    implementation(Libraries.Networking.GSON_CONVERTER)
    implementation(Libraries.Networking.RETROFIT)
    implementation(Libraries.Networking.OKHTTP)
    implementation(Libraries.Networking.OKHTTP_LOGGING_INTERCEPTOR)
    implementation(Libraries.Networking.OKHTTP_URLCONNECTION)

    //Hilt
    implementation(Libraries.DI.HILT)
    kapt(Libraries.DI.HILT_COMPILER)
    implementation(Libraries.DI.HILT_VIEWMODEL)
    kapt(Libraries.DI.HILT_VIEWMODEL_COMPILER)
    kaptAndroidTest(Libraries.DI.HILT_COMPILER)

    //Util
    implementation(Libraries.Utility.TIMBER)

    // Tests dep
    testImplementation(TestingLib.JUNIT)
    testImplementation(AndroidTestingLib.COROUTINES_TEST)
    testImplementation(Libraries.Concurrency.COROUTINES_ANDROID)
    testImplementation ("androidx.arch.core:core-testing:2.1.0")
    testImplementation ("com.squareup.okhttp3:mockwebserver:4.9.0")

    // Android test dep
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_CORE)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_RULES)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_RUNNER)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_JUNIT_TEST_EXT)
    androidTestImplementation(AndroidTestingLib.TRUTH)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TRUTH_EXT)
    androidTestImplementation(AndroidTestingLib.MOCKITO_INLINE)
    androidTestImplementation(AndroidTestingLib.MOCKITO_KOTLIN)
    androidTestImplementation(AndroidTestingLib.ROBOLECTRIC)
    androidTestImplementation(Libraries.DI.HILT_TESTING)

}
