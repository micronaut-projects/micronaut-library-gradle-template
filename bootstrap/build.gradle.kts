plugins {
    id("io.micronaut.library.maven-publish")
}
dependencies {
    // Validation
    annotationProcessor(mn.micronaut.validation.processor)
    api(mn.micronaut.validation)
    api(mn.micronaut.views.fieldset)
}