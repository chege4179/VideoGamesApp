plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.peterchege.gamesapp"
    compileSdk = 33

    defaultConfig {
        applicationId ="com.peterchege.gamesapp"
        minSdk =21
        targetSdk =33
        versionCode= 1
        versionName= "1.0"

        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary= true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled =false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.9.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation ("androidx.activity:activity-compose:1.6.1")
    implementation ("androidx.compose.ui:ui:1.3.2")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.3.2")
    implementation ("androidx.compose.material:material:1.3.1")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation( "androidx.test.ext:junit:1.1.4")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.0")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.3.2")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.3.2")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.3.2")


    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    // view model
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

    //coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // dagger hilt
    implementation ("com.google.dagger:hilt-android:2.44.2")
    kapt ("com.google.dagger:hilt-android-compiler:2.44.2")
//    implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    kapt ("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    // coil
    implementation( "io.coil-kt:coil-compose:2.2.2")


    // Also declare the dependency for the Google Play services library and specify its version
    implementation ("com.google.android.gms:play-services-auth:20.4.0")

    // room
    implementation ("androidx.room:room-runtime:2.4.3")
    kapt ("androidx.room:room-compiler:2.4.3")

    // Kotlin Extensions and Coroutines support for Room
    implementation ("androidx.room:room-ktx:2.4.3")

    // compose icons
    implementation ("androidx.compose.material:material-icons-extended:1.4.0-alpha03")
    // glide
    implementation ("dev.chrisbanes.accompanist:accompanist-glide:0.5.1")
    //pager
    implementation ("com.google.accompanist:accompanist-pager:0.24.3-alpha")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.24.3-alpha")
    // swipe refresh
    implementation ("com.google.accompanist:accompanist-swiperefresh:0.24.2-alpha")
    // landscapist
    implementation ("com.github.skydoves:landscapist-glide:1.4.8")

    implementation ("com.google.accompanist:accompanist-flowlayout:0.27.0")


    implementation ("androidx.datastore:datastore:1.0.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    implementation ("androidx.core:core-splashscreen:1.0.0")

    implementation ("com.google.accompanist:accompanist-navigation-animation:0.26.4-beta")

    //paging 3
    implementation ("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation ("androidx.paging:paging-compose:1.0.0-alpha17")


    // Local unit tests
    testImplementation ("androidx.test:core:1.5.0")
    testImplementation ("junit:junit:4.13.2")
    testImplementation ("androidx.arch.core:core-testing:2.1.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    testImplementation ("com.google.truth:truth:1.1.3")
    testImplementation ("com.squareup.okhttp3:mockwebserver:4.9.3")
    testImplementation ("io.mockk:mockk:1.10.5")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.4.0-alpha03")

    // Instrumentation tests
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.44.2")
    kaptAndroidTest ("com.google.dagger:hilt-android-compiler:2.44.2")
    androidTestImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    androidTestImplementation ("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation ("com.google.truth:truth:1.1.3")
    androidTestImplementation ("androidx.test.ext:junit:1.1.4")
    androidTestImplementation ("androidx.test:core-ktx:1.5.0")
    androidTestImplementation ("com.squareup.okhttp3:mockwebserver:4.9.3")
    androidTestImplementation ("io.mockk:mockk-android:1.10.5")
    androidTestImplementation ("androidx.test:runner:1.5.2")
    androidTestImplementation ("com.linkedin.dexmaker:dexmaker-mockito:2.28.3")

    
    
    
    


}