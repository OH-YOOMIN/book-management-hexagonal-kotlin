dependencies {
    runtimeOnly("com.h2database:h2")

    kapt ("jakarta.annotation:jakarta.annotation-api")
    kapt ("jakarta.persistence:jakarta.persistence-api")

    implementation(project(":application"))
    implementation(project(":domain"))
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

noArg {
    annotation("jakarta.persistence.Entity")
}

tasks.bootJar{
    enabled = false
}

tasks.jar{
    enabled = true
}
