plugins {
    id(Plugins.library)
    id(Plugins.android)
    id(Plugins.ksp)
}

android {
    namespace = "com.vsv.local_data_base"
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
}

dependencies {

    implementation(project(":core"))
    
    implementation(Dependencies.Core.core)
    implementation(Dependencies.Core.kotlinBom)

    implementation(Dependencies.Room.roomRuntime)
    ksp(Dependencies.Room.roomCompiler)

    implementation(Dependencies.Koin.koin)
    implementation(Dependencies.Koin.koinAndroidxCompose)
    testImplementation(Dependencies.Koin.koinTest)
}