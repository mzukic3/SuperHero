object Sdk {
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 29
    const val COMPILE_SDK_VERSION = 29
}

object Versions {
    const val ANDROIDX_TEST_EXT = "1.1.1"
    const val ANDROIDX_TEST = "1.2.0"
    const val APPCOMPAT = "1.1.0"
    const val CONSTRAINT_LAYOUT = "1.1.3"
    const val RECYCLER_VIEW = "1.1.0"
    const val CORE_KTX = "1.2.0"
    const val ESPRESSO_CORE = "3.2.0"
    const val JUNIT = "4.13"
    const val KTLINT = "0.37.2"
    const val RETROFIT = "2.6.0"
    const val GSON = "2.8.5"
    const val GSNON_CONVERTER = "2.6.0"
    const val MATERIAL = "1.1.0"
    const val NAVIGATION = "2.3.0"
    const val LIFECYCLE = "2.2.0"
    const val TIMBER = "4.7.1"
    const val GLIDE = "4.11.0"
    const val COROUTINES = "1.3.8"
    const val HILT = "2.28-alpha"


}

object BuildPluginsVersion {
    const val AGP = "4.0.0"
    const val DETEKT = "1.10.0"
    const val KOTLIN = "1.3.72"
    const val KTLINT = "9.2.1"
    const val VERSIONS_PLUGIN = "0.28.0"
}

object Libraries {
    object AndroidLibs {
        const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
        const val ANDROIDX_CONSTRAINT_LAYOUT =
            "com.android.support.constraint:constraint-layout:${Versions.CONSTRAINT_LAYOUT}"
        const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val ANDROIDX_RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLER_VIEW}"
        const val ANDROIDX_VIEWMODEL =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
        const val ANDROIDX_LIVEDATA =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
        const val ANDROIDX_LIFECYCLE_COMPILER =
            "androidx.lifecycle:lifecycle-compiler:${Versions.LIFECYCLE}"
        const val ANDROIDX_NAVIGATION_FRAGMENT =
            "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
        const val ANDROIDX_NAVIGATION_UI =
            "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    }

    object Networking {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
        const val GSON_CONVERTER =
            "com.squareup.retrofit2:converter-gson:${Versions.GSNON_CONVERTER}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:4.4.0"
        const val OKHTTP_URLCONNECTION = "com.squareup.okhttp3:okhttp-urlconnection:4.4.0"
        const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.4.0"
    }

    object Concurrency {
        const val COROUTINES      = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
        const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
    }

    object Utility {
        const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
        const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
        const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"
    }

    object DI {
        const val HILT = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
        const val HILT_VIEWMODEL = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01"
        const val HILT_VIEWMODEL_COMPILER = "androidx.hilt:hilt-compiler:1.0.0-alpha01"
    }

}

object TestingLib {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
}

object AndroidTestingLib {
    const val ANDROIDX_TEST_RULES = "androidx.test:rules:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}
