plugins {
    id(Plugins.library)
    id(Plugins.android)
    id(Plugins.ksp)

}

android {
    namespace = "com.vsv.feature_alarm_clock"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk

        testInstrumentationRunner = Config.defaultTestInstrumentationRunner
//        testInstrumentationRunnerArguments.putAll(mapOf("clearPackageData" to "true"))
        consumerProguardFiles("consumer-rules.pro")
    }

//    testOptions {
//        execution = "ANDROIDX_TEST_ORCHESTRATOR"
//        unitTests {
//            isIncludeAndroidResources = true
//        }
//    }

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
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.composeCompilerVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":local_data_base"))
    implementation(project(":core"))
    implementation(project(":core_ui"))

    implementation(Dependencies.Core.core)
    implementation(platform(Dependencies.Core.kotlinBom))

    implementation(platform(Dependencies.Compose.composeBom))
    implementation(Dependencies.Compose.composeUi)
    implementation(Dependencies.Compose.composeUiGraphics)
    implementation(Dependencies.Compose.composeUiToolingPreview)
    implementation(Dependencies.Compose.composeMaterial)
    implementation(Dependencies.Compose.lifecycle)
    implementation(Dependencies.Compose.lifecycleViewModel)
    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.navigation)

    androidTestImplementation(platform(Dependencies.Compose.composeBom))
    androidTestImplementation(Dependencies.Compose.composeUiTestJunit4)
    androidTestImplementation(Dependencies.Compose.navigation)
    debugImplementation(Dependencies.Compose.composeUiTooling)
    debugImplementation(Dependencies.Compose.composeUiTestManifest)

    implementation(Dependencies.Koin.koin)
    implementation(Dependencies.Koin.koinAndroidxCompose)
    testImplementation(Dependencies.Koin.koinTest)
    androidTestImplementation(Dependencies.Koin.koinAndroidTest)

    implementation(Dependencies.Room.roomRuntime)
    ksp(Dependencies.Room.roomCompiler)

    implementation(Dependencies.DataStore.preferencesDataStore)

    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.truth)
    testImplementation(Dependencies.Test.coroutinesTest)
    androidTestImplementation(Dependencies.Test.androidTest)
    androidTestImplementation(Dependencies.Test.espresso)
    androidTestImplementation(Dependencies.Test.truth)
    androidTestImplementation(Dependencies.Test.testRunner)
    androidTestImplementation(Dependencies.Test.androidTestRules)
    androidTestUtil(Dependencies.Test.orchestrator)
    androidTestImplementation(Dependencies.Test.UiAutomator)

}