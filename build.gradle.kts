plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:20.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation("org.flywaydb:flyway-core:9.6.0")
    implementation("mysql:mysql-connector-java:8.0.30")
    implementation("org.flywaydb:flyway-mysql:9.8.2")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.5")
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.5")
    implementation("com.google.code.gson:gson:2.10")
    compileOnly("org.projectlombok:lombok:1.18.24")

    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.2")

    implementation("javax.mail:javax.mail-api:1.6.2")
    implementation("org.springframework.boot:spring-boot-starter-mail:2.2.6.RELEASE")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}