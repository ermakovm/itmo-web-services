dependencies {
    implementation("org.postgresql:postgresql:${property("postgresVersion")}")

    implementation("org.glassfish.jersey.containers:jersey-container-servlet:4.0.0-M1")
    implementation("org.glassfish.jersey.media:jersey-media-json-jackson:4.0.0-M1")
    implementation("org.glassfish.jersey.media:jersey-media-json-processing:4.0.0-M1")

    implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-http:4.0.0-M1")
    implementation("org.glassfish.jersey.core:jersey-common:4.0.0-M1")
    implementation("org.glassfish.jersey.core:jersey-server:4.0.0-M1")
    implementation("org.glassfish.jersey.inject:jersey-hk2:4.0.0-M1")


    implementation("jakarta.platform:jakarta.jakartaee-web-api:11.0.0-M4")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.2")
}