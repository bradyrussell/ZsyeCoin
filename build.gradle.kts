import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    `maven-publish`
    signing
}

val group = "me.aish"
val ver = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.github.netvl.ecoji:ecoji:1.0.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

publishing {
    publications {
        create<MavenPublication>(rootProject.name) {
            groupId = group
            artifactId = rootProject.name
            version = ver

            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "ZsyecoinRepositoryCredentials"
            credentials(PasswordCredentials::class)
            val releasesRepoUrl = "https://repo.bradyrussell.com/repository/bradyrussell-release/"
            val snapshotsRepoUrl = "https://repo.bradyrussell.com/repository/bradyrussell-snapshot/"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
        }
    }

}

signing {
    sign(publishing.publications[rootProject.name])
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}