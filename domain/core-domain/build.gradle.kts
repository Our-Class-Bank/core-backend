dependencies {
    implementation(project(":db:core-db"))
    implementation(project(":domain:model-domain"))
}

tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}
