plugins {
    java
    idea
    id("io.freefair.lombok") version "8.10.2"
}

idea {
    project {
        setLanguageLevel(21)
    }

    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}

allprojects {
    group = "info.mermakov.itmo.web"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "io.freefair.lombok")
}