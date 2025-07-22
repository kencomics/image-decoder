plugins {
    id("com.android.library") version "8.10.0"
    kotlin("android") version "2.1.10"
    id("maven-publish")
}

group = "tachiyomiorg"

android {
    compileSdk = 36
    ndkVersion = "28.2.13676358"
    namespace = "tachiyomi.decoder"

    defaultConfig {
        minSdk = 21

        consumerProguardFiles("consumer-rules.pro")
        externalNativeBuild {
            cmake {
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1+"
        }
    }
}

dependencies {
}

afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
//            release(MavenPublication) {
////                from components.release
//                groupId = "tachiyomiorg"
//                artifactId = "image-decoder"
//                version = "1.0"
//            }
        }
    }
}
