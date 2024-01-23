plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.peterchege.gamesapp"
    compileSdk = 34

    defaultConfig {
        applicationId ="com.peterchege.gamesapp"
        minSdk =21
        targetSdk =34
        versionCode= 1
        versionName= "1.0"

        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary= true
        }
    }

    ksp {
        arg(k = "room.schemaLocation", v = "$projectDir/schemas")
    }
    signingConfigs {
        create("release"){
            storeFile = file("gamesapp.jks")
            keyAlias = "gamesapp"
            keyPassword = "peter"
            storePassword =  "peter"
        }
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled =true
            isShrinkResources = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/**")
        }
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.activity:activity-compose:1.8.2")
    implementation ("androidx.compose.ui:ui:1.6.0-rc01")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.6.0-rc01")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation( "androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.5.4")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.5.4")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.5.4")

    implementation ("androidx.navigation:navigation-compose:2.7.6")

    // compose icons
    implementation ("androidx.compose.material:material-icons-extended:1.6.0-rc01")
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.compose.material3:material3:1.2.0-beta02")
    implementation("androidx.compose.material3:material3-window-size-class:1.2.0-beta02")

    // Metrics Performance
    implementation("androidx.metrics:metrics-performance:1.0.0-beta01")

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    // view model
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")


    //coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // dagger hilt
    implementation("com.google.dagger:hilt-android:2.50")
    ksp("com.google.dagger:hilt-android-compiler:2.48.1")
    ksp("androidx.hilt:hilt-compiler:1.1.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.hilt:hilt-common:1.1.0")
    implementation("androidx.hilt:hilt-work:1.1.0")
    // coil
    implementation( "io.coil-kt:coil-compose:2.5.0")

    // room
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")



    implementation ("androidx.datastore:datastore:1.0.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.6")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")

    implementation ("androidx.core:core-splashscreen:1.0.1")

    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-perf")

    implementation("com.jakewharton.timber:timber:5.0.1")

    //paging 3 compose
    implementation("androidx.paging:paging-compose:3.2.1")
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")

    //chucker
    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")


    // Local unit tests
    testImplementation ("androidx.test:core:1.5.0")
    testImplementation ("junit:junit:4.13.2")
    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation ("com.google.truth:truth:1.2.0")
    testImplementation ("com.squareup.okhttp3:mockwebserver:4.12.0")
    testImplementation ("io.mockk:mockk:1.13.8")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.6.0-rc01")

    // Instrumentation tests
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.50")
    kspAndroidTest ("com.google.dagger:hilt-android-compiler:2.48.1")
    androidTestImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    androidTestImplementation ("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation ("com.google.truth:truth:1.2.0")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test:core-ktx:1.5.0")
    androidTestImplementation ("com.squareup.okhttp3:mockwebserver:4.12.0")
    androidTestImplementation ("io.mockk:mockk-android:1.13.8")
    androidTestImplementation ("androidx.test:runner:1.5.2")
    androidTestImplementation ("com.linkedin.dexmaker:dexmaker-mockito:2.28.3")

}