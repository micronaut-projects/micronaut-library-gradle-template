plugins {
    `kotlin-dsl`
    `groovy-gradle-plugin`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}
dependencies {
    implementation(libs.spotless)
    implementation(libs.graalvm.native.buildtools)
}
