plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("dev.langchain4j:langchain4j-open-ai:1.22.0")
    implementation("org.jsoup:jsoup:1.23.2")
    implementation("com.knuddels:jtokkit:1.1.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.22.3")
}

tasks.test {
    useJUnitPlatform()
}