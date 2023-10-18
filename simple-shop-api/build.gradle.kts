plugins {
    java
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "com.webster"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.30")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.1.4")
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.4")
    implementation("org.flywaydb:flyway-mysql:9.22.3")

    runtimeOnly("org.mariadb.jdbc:mariadb-java-client:3.2.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.4")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

tasks.getByName<Test>("test") {
    systemProperty("file.encoding", "UTF-8")
    useJUnitPlatform()
}