plugins {

}

dependencies {
    val openapi3Version: String by project

    implementation(project(":domain:core-domain"))
    implementation(project(":domain:model-domain"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")

    // openapi3
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$openapi3Version")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:$openapi3Version")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}
