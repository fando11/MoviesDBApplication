package dependencies.android

import dependencies.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.androidX() {
    implementation("androidx.core:core-ktx:1.1.0-alpha05")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.annotation:annotation:1.1.0")
    implementation("androidx.activity:activity-ktx:1.3.1")
    implementation("androidx.fragment:fragment-ktx:1.3.6")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}