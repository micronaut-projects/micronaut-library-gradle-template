# Micronaut Library Gradle Template
[GitHub Repository Template](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-template-repository) to create a [Micronaut](https://micronaut.io) library with a Gradle Build

## Language and Test Framework

This template assumes you want to write a Java Library compatible with Java 17 and uses [Micronaut JUnit 5](https://micronaut-projects.github.io/micronaut-test/4.6.2/guide/#junit5) for testing. 

## Micronaut Platform Gradle Version Catalogue

This template applies the [Micronaut Version Catalogue](https://micronaut-projects.github.io/micronaut-platform/latest/guide) in root `settings.gradle.kts`. 

## License Management

This template uses the [Spotless Gradle Plugin](https://github.com/diffplug/spotless) to ensure every file contains a license matching the template in `config/spotless.license.java`.

## Code Coverage

The template applies the [JaCoCo Gradle Plugin](https://docs.gradle.org/current/userguide/jacoco_plugin.html) to get code coverage and the [JaCoCo report Aggregation Plugin](https://docs.gradle.org/current/userguide/jacoco_report_aggregation_plugin.html). 

Run `./gradlew testCodeCoverageReport` and you can access the HTML report ` open code-coverage-report/build/reports/jacoco/testCodeCoverageReport/html/index.html`.

## Publish to Sonatype OSSRH (OSS Repository Hosting)

### GPG Key
To publish to Sonatype OSSRH, you need first to [generate a GPG key](https://central.sonatype.org/publish/requirements/gpg/#generating-a-key-pair9) and [distributed your public key](https://central.sonatype.org/publish/requirements/gpg/#distributing-your-public-key). 

### User Token

- [Generate a Token](https://central.sonatype.org/publish/generate-token/#generate-a-token-on-ossrh-sonatype-nexus-repository-manager-servers). Save the user token username and password.

### Credentials

The [credentials](https://central.sonatype.org/publish/publish-gradle/#credentials) for signing and upload can be stored in your `gradle.properties` file in your users home directory. The content would look like this

`$HOME/.gradle/gradle.properties`

```properties
sonatypeUsername=SonaTypeUserTokenUserName
sonatypePassword=SonaTypeUserTokenPassword
# <1>
signing.keyId=YourKeyId
# <2>
signing.password=YouPublicKeyPassword
# <3>
signing.secretKeyRingFile=PathToYourKeyRingFile
```

<1> The public key ID (The last 8 symbols of the keyId. You can use `gpg -K --keyid-format short` to get it).
<2> The passphrase used to protect your private key.
<3> The absolute path to the secret key ring file containing your private key. (Since gpg 2.1, you need to export the keys with command `gpg --keyring secring.gpg --export-secret-keys > ~/.gnupg/secring.gpg`).

### Publish Release to Sonatype OSSRH

- Bump up version. Ensure `projectVersion` does not contain `-SNAPSHOT`. 
- Tag it. E.g. `v1.0.0`
- `./gradlew publishToSonatype closeSonatypeStagingRepository --info`