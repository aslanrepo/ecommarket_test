plugins {
	java
	//https://plugins.gradle.org/plugin/org.springframework.boot
	id("org.springframework.boot") version "3.1.2"
	//https://plugins.gradle.org/plugin/io.spring.dependency-management
	id("io.spring.dependency-management") version "1.1.2"
}

group = "ton.telegrambots.ecommarket"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web
	implementation("org.springframework.boot:spring-boot-starter-web")
	// https://mvnrepository.com/artifact/org.springframework/spring-aop
	implementation("org.springframework.boot:spring-boot-starter-aop")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

springBoot {
	buildInfo()
}