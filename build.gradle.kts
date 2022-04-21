import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21" // Kotlin JVM plugin to add support for Kotlin.
}

val githubUsername by extra { System.getenv("GH_USERNAME") ?: project.findProperty("GH_USERNAME") as String? ?: "NOT_SET" }
val githubReleaseToken by extra { System.getenv("GH_PACKAGES_RELEASE_TOKEN") ?: project.findProperty("GH_PACKAGES_RELEASE_TOKEN") as String? ?: "NOT_SET" }

allprojects {
    repositories {
        mavenCentral()
    }
    group = "io.github.ccjhr.hikaku"
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "java") // needed for the scopes such as api, implementation and the like
    apply(plugin = "maven-publish")

    version = "3.3.1-SNAPSHOT"

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
            languageVersion = "1.6"
            apiVersion = "1.6"
        }
    }

    dependencies {
        implementation(platform(kotlin("bom")))
        api(kotlin("stdlib-jdk8"))
        api(kotlin("reflect"))
        api(kotlin("test"))

        testImplementation("org.junit.platform:junit-platform-launcher:1.7.2")
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.2")
        testImplementation(kotlin("test-junit5"))
        testImplementation("org.assertj:assertj-core:3.20.2")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        reports.html.required.set(false)
        reports.junitXml.required.set(false)
        maxParallelForks = Runtime.getRuntime().availableProcessors()
    }


}