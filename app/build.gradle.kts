plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.navigationdrawerapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.navigationdrawerapp"
        minSdk = 16
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        dataBinding = true
        viewBinding = false
    }
}

dependencies {
    //region Android UI Layout Library and backward-compatible Library(Legacy)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    //endregion
    //region circular image view (de.hdodenhof.circleimageview.CircleImageView)
    implementation("de.hdodenhof:circleimageview:2.2.0")
    //endregion
    //region Android Unit Test and U.I. Test Library
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //endregion
}