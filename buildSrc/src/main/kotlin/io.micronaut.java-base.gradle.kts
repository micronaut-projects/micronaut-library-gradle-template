import org.gradle.kotlin.dsl.repositories

plugins {
    `java`
}

java {
    withSourcesJar()
    withJavadocJar()
}

group = project.findProperty("mavenGroup") as String
version = project.findProperty("projectVersion") as String
repositories {
    mavenCentral()
}
