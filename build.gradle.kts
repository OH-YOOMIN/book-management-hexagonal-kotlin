plugins {
    // Spring Boot & Dependency Management
    id("org.springframework.boot") version "3.3.6" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false

    // Kotlin Plugins
    kotlin("jvm") version "1.9.24"
    kotlin("kapt") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24" apply false
    kotlin("plugin.jpa") version "1.9.24" apply false
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

allprojects {
    group = "com.oynee.portfolio"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    applyKotlinProject()
}

// 멀티 모듈 구성
listOf(
    ":adapter:inbound:api",
    ":adapter:outbound:persistence",
    ":bootstrap",
    ":application",
).forEach { module ->
    project(module) {
        applySpringBootProject()
    }
}

// 공통 Kotlin 프로젝트 설정
fun Project.applyKotlinProject() {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

// Spring Boot 모듈 설정
fun Project.applySpringBootProject() {
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        // Spring Boot
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-validation")

        // Kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        // MapStruct
        implementation("org.mapstruct:mapstruct:1.5.5.Final")
        kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")

        // Logging
        implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")

        // Test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        testImplementation("io.mockk:mockk:1.13.12")
        kaptTest("org.mapstruct:mapstruct-processor:1.5.5.Final")
    }
}