plugins {
    id("java")
}

group = "me.dexrn"
version = "1.1.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(files("libs/RRDiscordBridge.jar"))
}