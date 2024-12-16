plugins {
    id("com.github.bjornvester.wsdl2java")
}

wsdl2java {
    generatedSourceDir.set(layout.projectDirectory.dir("src/generated"))
    useJakarta = false
    cxfVersion = "3.5.6"
    packageName = "ws"
}

dependencies {
    implementation("com.sun.xml.ws:jaxws-rt:2.3.7")
    implementation("javax.xml.ws:jaxws-api:2.3.1")

    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("com.sun.xml.bind:jaxb-impl:2.3.9")

    implementation("org.apache.juddi:juddi-client:3.3.10")
    implementation("org.apache.juddi:uddi-ws:3.3.10")
}