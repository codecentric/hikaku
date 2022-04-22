plugins {
  kotlin("jvm") version "1.6.21"
  `maven-publish`
  `java-library`
}

val moduleName = "hikaku-spring"
val githubUsername: String by rootProject.extra
val githubReleaseToken: String by rootProject.extra
val springBootVersion = "2.5.4"

dependencies {
  implementation(platform(kotlin("bom")))
  api(kotlin("stdlib-jdk8"))
  api(kotlin("reflect"))
  api(kotlin("test"))
  api("org.springframework:spring-webmvc:5.3.9")
  api(project(":core"))

  testImplementation(kotlin("test-junit5"))
  testImplementation("org.junit.platform:junit-platform-launcher:1.7.2")
  testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.2")
  testImplementation("org.assertj:assertj-core:3.20.2")
  testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
  testImplementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  kotlinOptions {
    jvmTarget = "17"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
  reports.html.required.set(false)
  reports.junitXml.required.set(false)
  maxParallelForks = Runtime.getRuntime().availableProcessors()
}

val sourcesJar by tasks.registering(Jar::class) {
  archiveClassifier.set("sources")
  from(sourceSets.main.get().allSource)
}

val javaDoc by tasks.registering(Jar::class) {
  archiveClassifier.set("javadoc")
  from(sourceSets.main.get().allSource)
}

publishing {
  repositories {
    maven {
      name = moduleName
      url = uri("https://maven.pkg.github.com/$githubUsername/${rootProject.name}")
      credentials {
        username = githubUsername
        password = githubReleaseToken
      }
    }
  }
  publications {
    create<MavenPublication>("maven") {
      groupId = project.group.toString()
      artifactId = moduleName
      version = project.version.toString()

      from(components["java"])
      artifact(sourcesJar.get())
      artifact(javaDoc.get())

      pom {
        packaging = "jar"
        name.set(moduleName)
        description.set("A library that tests if the implementation of a REST-API meets its specification. This module contains a converter for spring-mvc implementations.")
        url.set("https://github.com/$githubUsername/${rootProject.name}")

        licenses {
          license {
            name.set("Apache License 2.0")
            url.set("https://www.apache.org/licenses/LICENSE-2.0")
          }
        }

        scm {
          connection.set("scm:git@github.com:$githubUsername/${rootProject.name}.git")
          developerConnection.set("scm:git:ssh://github.com:$githubUsername/${rootProject.name}.git")
          url.set("https://github.com/$githubUsername/${rootProject.name}")
        }
      }
    }
  }
}