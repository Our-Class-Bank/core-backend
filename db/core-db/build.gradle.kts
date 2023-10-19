plugins {
    idea
}

dependencies {
    val p6spyVersion: String by project
    val queryDslVersion: String by project

    implementation(project(":domain:model-domain"))

    // jpa + mysql
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("com.h2database:h2")
    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:${p6spyVersion}")

    // querydsl
    implementation ("com.querydsl:querydsl-jpa:${queryDslVersion}:jakarta")
    kapt("com.querydsl:querydsl-apt:${queryDslVersion}:jakarta")
}

tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}

idea {
    module {
        val kaptMain = file("build/generated/source/kapt/main")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}
