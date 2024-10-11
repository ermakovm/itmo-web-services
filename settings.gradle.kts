pluginManagement {
    val lombokVersion: String by settings

    repositories {
        gradlePluginPortal()
    }

    plugins {
        id("io.freefair.lombok") version lombokVersion
    }
}

rootProject.name = "web-services"
include("lab1")
include("lab1:standalone")
findProject(":lab1:standalone")?.name = "standalone"
include("lab1:j2ee")
findProject(":lab1:j2ee")?.name = "j2ee"
include("lab1:client")
findProject(":lab1:client")?.name = "client"
