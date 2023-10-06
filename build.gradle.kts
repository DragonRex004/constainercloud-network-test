plugins {
    id("java")
}

group = "de.containercloud"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.esotericsoftware:kryonet:2.22.0-RC1")
    implementation("com.google.code.gson:gson:2.10.1")
}