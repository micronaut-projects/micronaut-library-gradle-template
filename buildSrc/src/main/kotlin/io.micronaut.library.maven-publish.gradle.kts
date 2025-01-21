plugins {
    id("io.micronaut.library.micronaut-java-library")
    `maven-publish` // https://docs.gradle.org/current/userguide/publishing_maven.html
    signing // https://docs.gradle.org/current/userguide/signing_plugin.html
}
java {
    withJavadocJar()
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                inceptionYear.set(project.findProperty("inceptionYear") as String)
                name.set(project.findProperty("projectName") as String)
                description.set(project.findProperty("projectDesc") as String)
                url.set(project.findProperty("projectUrl") as String)
                licenses {
                    license {
                        name.set("Apache-2.0")
                        url.set("https://spdx.org/licenses/Apache-2.0.html")
                    }
                }
                developers {
                    developer {
                        id.set(project.findProperty("developerId") as String)
                        name.set(project.findProperty("developerName") as String)
                        email.set(project.findProperty("developerEmail") as String)
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/${project.findProperty("githubOrg") as String}/${project.findProperty("githubRepo") as String}.git")
                    developerConnection.set("scm:git:ssh://github.com/${project.findProperty("githubOrg") as String}/${project.findProperty("githubRepo") as String}.git")
                    url.set("https://github.com/${project.findProperty("githubOrg") as String}/${project.findProperty("githubRepo") as String}")
                }
            }
        }
    }
}
signing {
    sign(publishing.publications["mavenJava"])
}