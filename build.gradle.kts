plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

application {
    mainClass.set("it.unicam.cs.mpgc.rpg130681.Main")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

javafx {
    version = "21"

    modules = listOf(
        "javafx.controls"
    )
}

tasks.test {
    useJUnitPlatform()
}