plugins {
    id("java")
}

group = "com.kimiega.unosoft.testtask"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
}

tasks.test {
}

tasks.jar {
    manifest {
        attributes(mapOf(
            "Main-Class" to "com.kimiega.unosoft.testtask.Main"
        ))
    }
}