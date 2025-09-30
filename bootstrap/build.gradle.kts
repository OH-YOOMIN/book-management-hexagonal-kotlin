dependencies {
    implementation(project(":adapter:inbound:api"))
    implementation(project(":adapter:outbound:persistence"))
    implementation(project(":application"))
    implementation(project(":domain"))
}

tasks.bootJar{
    enabled = true
}

tasks.jar{
    enabled = false
}

tasks.bootBuildImage{
    imageName.set(rootProject.name)

    createdDate.set("now")
}