import org.gradle.api.JavaVersion

object Config {

    const val compileSdk = 33
    const val minSdk = 26
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"
    const val defaultTestInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val koinTestInstrumentationRunner = "com.vsv.ruleyourtime.KoinTestRunner"
    val javaVersion = JavaVersion.VERSION_17
    const val jvmTarget = "17"
    const val composeCompilerVersion = "1.4.8"
}