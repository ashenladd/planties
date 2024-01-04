import java.net.URI

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false
    id("androidx.navigation.safeargs") version  "2.5.3" apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io") }
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.1.2");

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
