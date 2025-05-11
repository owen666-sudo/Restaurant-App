allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val newBuildDir: Directory = rootProject.layout.buildDirectory.dir("../../build").get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}

subprojects {
    project.evaluationDependsOn(":app")
}

// 将 android 配置块移动到正确的位置
plugins {
    id("com.android.application") version "7.4.0" // 或其他版本
    kotlin("android") version "1.7.10"  // 如果你使用 Kotlin
}

android {
    ndkVersion = "27.0.1207973"  // 设置正确的 NDK 版本
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}