object Dependencies {

    object Core {
        const val core = "androidx.core:core-ktx:1.9.0"
        const val kotlinBom = "org.jetbrains.kotlin:kotlin-bom:1.9.0"
    }

    object Compose {
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-compose:2.6.1"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1"
        const val activity = "androidx.activity:activity-compose:1.7.2"
        const val navigation = "androidx.navigation:navigation-compose:2.6.0"
        const val composeBom = "androidx.compose:compose-bom:2023.06.01"
        const val composeUi = "androidx.compose.ui:ui"
        const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
        const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val composeMaterial = "androidx.compose.material3:material3"

        const val composeUiTooling = "androidx.compose.ui:ui-tooling"
        const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
        const val composeUiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
    }

    object Koin {
        const val koin = "io.insert-koin:koin-android:3.4.3"
        const val koinAndroidxCompose = "io.insert-koin:koin-androidx-compose:3.4.6"
        const val koinTest = "io.insert-koin:koin-test:3.4.3"
        const val koinAndroidTest = "io.insert-koin:koin-android-test:3.4.3"
    }

    object Room {
        const val roomRuntime = "androidx.room:room-ktx:2.5.2"
        const val roomCompiler = "androidx.room:room-compiler:2.5.2"
    }

    object Test {
        const val core = "androidx.test:core-ktx:1.5.0"
        const val junit = "junit:junit:4.13.2"
        const val androidTest = "androidx.test.ext:junit:1.1.5"
        const val espresso = "androidx.test.espresso:espresso-core:3.5.1"
        const val truth = "com.google.truth:truth:1.1.4"
        const val mockitoCore = "org.mockito:mockito-core:5.4.0"
        const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:5.1.0"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3"
        const val robolectric = "org.robolectric:robolectric:4.9"
        const val androidTestRules = "androidx.test:rules:1.5.0"
        const val UiAutomator = "androidx.test.uiautomator:uiautomator:2.2.0"
        const val testRunner = "androidx.test:runner:1.5.2"
        const val orchestrator = "androidx.test:orchestrator:1.4.2"
    }

    object DataStore {
        const val preferencesDataStore = "androidx.datastore:datastore-preferences:1.0.0"
    }
}