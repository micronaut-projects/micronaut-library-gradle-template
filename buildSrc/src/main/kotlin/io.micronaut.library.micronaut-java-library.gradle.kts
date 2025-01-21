plugins {
    checkstyle // https://docs.gradle.org/current/userguide/checkstyle_plugin.html
    jacoco
    id("com.diffplug.spotless")
    id("io.micronaut.java-base")
    id("io.micronaut.library")
}

micronaut {
    processing {
        module.set(project.name)
        group.set(project.findProperty("mavenGroup") as String)
        incremental.set(true)
        annotations.add("${project.findProperty("mavenGroup") as String}.*")
    }
}
dependencies {
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
