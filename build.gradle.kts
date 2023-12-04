group = "tw.maoyue"
version = "1.1.0"
description = "My Discord music bot, base on JMusicBot"
java.sourceCompatibility = JavaVersion.VERSION_11

plugins {
    `java-library`
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    api("net.dv8tion:JDA:5.0.0-beta.18")
    api("dev.arbjerg:lavaplayer:2.0.4")
    api("com.github.DxsSucuk:jda-utilities:fdbc298")
    api("com.github.MagicTeaMC:MaoLyrics:2d521a797c")
    api("ch.qos.logback:logback-classic:1.4.13")
    api("com.typesafe:config:1.4.3")
    api("org.jsoup:jsoup:1.17.1")
    api("com.squareup.okhttp3:okhttp:4.12.0")
    api("com.google.code.gson:gson:2.10.1")
    api("org.slf4j:slf4j-nop:2.0.9")
    api("org.slf4j:slf4j-api:2.0.9")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.hamcrest:hamcrest-core:2.2")
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.shadowJar {
    archiveFileName = "OrangeDog-$version.jar"
}

tasks.jar {
    archiveFileName = "originalOrangeDog-$version.jar"
    manifest {
        attributes(
            "Main-Class" to "com.jagrosh.jmusicbot.JMusicBot",
            "Specification-Title" to project.name,
            "Specification-Version" to project.version,
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version,
            "Implementation-Vendor-Id" to project.group
        )
    }
}