dependencies {
    implementation(project(":db:core-db"))
    implementation(project(":domain:model-domain"))

    implementation("org.springframework.boot:spring-boot-starter-security")
}

tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}
