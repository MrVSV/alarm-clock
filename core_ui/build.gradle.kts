plugins {
    id(Plugins.library)
    id(Plugins.android)
}

android {
    namespace = "com.vsv.core_ui"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk

        testInstrumentationRunner = Config.defaultTestInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
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
}