
// build.gradle (Project-level)
buildscript {

    dependencies {
        val kotlin_version = "1.8.0"
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath ("com.android.tools.build:gradle:8.1.0")
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}