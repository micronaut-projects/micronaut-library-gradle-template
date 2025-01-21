rootProject.name = "MicronautLibrary"

include("bootstrap")
include("code-coverage-report")

val micronautVersion: String by settings
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        create("mn") {
            from("io.micronaut.platform:micronaut-platform:$micronautVersion")
        }
    }
}