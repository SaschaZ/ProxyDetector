dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}
pluginManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }


    buildscript {
        repositories {
            gradlePluginPortal()
            mavenCentral()
            google()
        }
        dependencies {
            val kotlinVersion: String = "1.5.10"
            classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
            classpath(kotlin("serialization", version = kotlinVersion))
        }
    }
}
rootProject.name = "ProxyDetector"

