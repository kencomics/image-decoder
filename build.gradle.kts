// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.library") version "8.10.0" apply false
    kotlin("android") version "2.1.10" apply false
    id("maven-publish")
}

configurations.all {
    resolutionStrategy {
        failOnVersionConflict()
    }
}
