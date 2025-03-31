plugins {
    id("java")
    checkstyle
    id("org.sonarqube") version "6.0.1.5171"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
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
}