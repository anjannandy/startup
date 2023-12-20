plugins {
	java
	jacoco
	application
	id("com.diffplug.spotless") version "5.17.1"
	id("com.avast.gradle.docker-compose") version "0.14.9"
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.startup.api-services"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

spotless {
	java {
		// Use the default importOrder configuration
		targetExclude("**/build/*")
		importOrder()
		removeUnusedImports()
		googleJavaFormat()
		target(
				project.fileTree(project.projectDir) {
					include("**/*.java")
					exclude("**/.gradle/**/*")
					exclude("**/build/**/*")
				}
		)
	}

}

dependencies {
	implementation("com.google.googlejavaformat:google-java-format:1.7")
}

repositories {
	mavenCentral()
	google()
	maven { url = uri("https://packages.confluent.io/maven/") }
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
