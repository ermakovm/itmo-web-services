plugins {
    id("com.github.bjornvester.wsdl2java")
}

wsdl2java {
    generatedSourceDir.set(layout.projectDirectory.dir("src/generated"))
    useJakarta = true
    cxfVersion = "4.0.5"
    packageName = "ws"
}

dependencies {
    implementation("com.sun.xml.ws:rt:${property("rtVersion")}")
}