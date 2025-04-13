import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("java")
    checkstyle
    application
    id("io.freefair.lombok") version "8.13.1"
    id("org.sonarqube") version "6.0.1.5171"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass = "hexlet.code.App"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("io.javalin:javalin:6.2.0")
    implementation("io.javalin:javalin-bundle:6.2.0")
    implementation("io.javalin:javalin-rendering:6.1.6")
    implementation("gg.jte:jte:3.2.0")

    implementation("com.zaxxer:HikariCP:6.3.0")
    implementation("com.h2database:h2:2.3.232")
    implementation("org.postgresql:postgresql:42.7.4")

    testImplementation(platform("org.junit:junit-bom:5.11.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

sonar {
 properties {
 property("sonar.projectKey", "sergeycherkasovv_java-project-72")
 property("sonar.organization", "sergeycherkasovv")
 property("sonar.host.url", "https://sonarcloud.io")
 }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        // showStackTraces = true
        // showCauses = true
        showStandardStreams = true
    }
}
