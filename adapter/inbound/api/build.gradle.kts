dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation(project(":application"))
    implementation(project(":domain"))
}

tasks.bootJar{
    enabled = false
}

tasks.jar{
    enabled = true
}
