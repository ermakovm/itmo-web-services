pluginManagement {
    val lombokVersion: String by settings
    val wsdlVersion: String by settings

    repositories {
        gradlePluginPortal()
    }

    plugins {
        id("io.freefair.lombok") version lombokVersion
        id("com.github.bjornvester.wsdl2java") version wsdlVersion
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
include("lab2")
include("lab2:client")
findProject(":lab2:client")?.name = "client"
include("lab2:standalone")
findProject(":lab2:standalone")?.name = "standalone"
