plugins {
    kotlin("jvm") version "1.6.21"
}

val githubUsername by extra { System.getenv("GH_USERNAME") ?: project.findProperty("GH_USERNAME") as String? ?: "cc-jhr" }
val githubReleaseToken by extra { System.getenv("GH_PACKAGES_RELEASE_TOKEN") ?: project.findProperty("GH_PACKAGES_RELEASE_TOKEN") as String? ?: "NOT_SET" }

allprojects {
    repositories {
        mavenCentral()
    }
    group = "io.github.ccjhr.hikaku"
}