dependencies {
    implementation("org.postgresql:postgresql:${property("postgresVersion")}")
    implementation("org.glassfish.metro:webservices-rt:${property("webservicesRtVersion")}")
    implementation("com.sun.xml.bind:jaxb-impl:${property("jaxbImplVersion")}")
}