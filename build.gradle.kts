plugins {
    kotlin("jvm") version "1.6.21"
    jacoco
    `jacoco-report-aggregation`
    id("com.github.nbaztec.coveralls-jacoco") version "1.2.14"
}

val githubUsername by extra { System.getenv("GH_USERNAME") ?: project.findProperty("GH_USERNAME") as String? ?: "cc-jhr" }
val githubReleaseToken by extra { System.getenv("GH_PACKAGES_RELEASE_TOKEN") ?: project.findProperty("GH_PACKAGES_RELEASE_TOKEN") as String? ?: "NOT_SET" }

allprojects {
    repositories {
        mavenCentral()
    }
    group = "io.github.ccjhr.hikaku"
}

coverallsJacoco {
    reportPath = "$buildDir/reports/jacoco/test/jacocoFullReport.xml"
}

tasks.jacocoTestReport {
    subprojects {
        this@subprojects.plugins.withType<JacocoPlugin>().configureEach {
            this@subprojects.tasks.matching {
                it.extensions.findByType<JacocoTaskExtension>() != null }
                .configureEach {
                    sourceSets(this@subprojects.the<SourceSetContainer>().named("main").get())
                    executionData(this)
                }
        }
    }

    reports {
        html.required.set(false)
        html.outputLocation.set(file("$buildDir/reports/jacoco/test/jacocoFullReport.html"))
        xml.required.set(true)
        xml.outputLocation.set(file("$buildDir/reports/jacoco/test/jacocoFullReport.xml"))
    }
    dependsOn(allprojects.map { it.tasks.named<Test>("test") })
}