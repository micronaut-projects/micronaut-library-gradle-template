plugins {
    `kotlin-dsl`
    `groovy-gradle-plugin`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}
dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:7.0.2")
}
