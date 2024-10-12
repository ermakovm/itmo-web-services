plugins {
    war
}

dependencies {
    implementation("org.glassfish.metro:webservices-rt:${property("webservicesRtVersion")}")
}