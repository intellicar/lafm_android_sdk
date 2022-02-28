## Installation

### - Step 1- Add jitpack in your project build.gradle
```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

### - Step 2- Add the dependency in your app build.gradle
```groovy
dependencies {
    ...
    implementation 'com.github.intellicar:lafm_android_sdk:x.y.z'
}
```
Please replace x, y and z with the latest version
numbers: ![](https://jitpack.io/v/intellicar/lafm_android_sdk.svg)

### - Step 3- Optional

As of the 7.X.X gradle build tools. allprojects is deprecated use of dependencyResolutionManagement is the best practice for declaring repositories in every subproject of your build. Because of this Android projects will no longer generate with allprojects blocks in their project build.gradle files. It will instead generate a dependencyResolutionManagement block in settings.gradle.

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven {
            url 'https://jitpack.io'
        }
    }
}
```

## Documentation
Please visit [Documentation](https://github.com/intellicar/lafm_android_sdk/wiki/Home)
