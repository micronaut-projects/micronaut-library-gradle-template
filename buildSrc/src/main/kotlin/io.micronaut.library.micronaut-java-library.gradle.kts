plugins {
    `java-library`
    jacoco
    id("com.diffplug.spotless")

}
group = project.findProperty("mavenGroup") as String
version = project.findProperty("projectVersion") as String
repositories {
    mavenCentral()
}
val micronautVersion: String by project
dependencies {
    annotationProcessor(platform("io.micronaut.platform:micronaut-platform:${micronautVersion}"))
    annotationProcessor("io.micronaut:micronaut-inject-java")
    implementation(platform("io.micronaut.platform:micronaut-platform:${micronautVersion}"))
    implementation("io.micronaut:micronaut-inject")
    testAnnotationProcessor(platform("io.micronaut.platform:micronaut-platform:${micronautVersion}"))
    testAnnotationProcessor("io.micronaut:micronaut-inject-java")
    testImplementation(platform("io.micronaut.platform:micronaut-platform:${micronautVersion}"))
    // Logging
    testRuntimeOnly("ch.qos.logback:logback-classic")

    // JUnit Testing
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.micronaut.test:micronaut-test-junit5")

}
tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}
spotless {
    java {
        licenseHeaderFile("$rootDir/config/spotless.license.java")
    }
}