plugins {
    id(Plugins.application)
    id(Plugins.android)
}

android {
    namespace = "com.vsv.ruleyourtime"
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = "com.vsv.ruleyourtime"
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testInstrumentationRunner
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
        targetCompatibility = Config.javaVersion
        sourceCompatibility = Config.javaVersion
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

    implementation(project(":feature_alarm_clock"))
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
    testImplementation(Dependencies.Koin.koinTestJunit4)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}