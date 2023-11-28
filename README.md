This is a Kotlin Multiplatform project targeting Android, iOS, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

Kotlin Multiplatform 模板
https://github.com/JetBrains/compose-multiplatform-ios-android-template

KMP 多平台依赖库，架构组件选型
https://github.com/terrakok/kmp-awesome

Image Loader
https://github.com/Kamel-Media/Kamel
-使用 Ktor HttpClient 请求框架，内部引用了 core ，需要依赖对应平台库代码，
-比如 android  `{  implementation("io.ktor:ktor-client-okhttp:2.3.6") }`

HttpClient
https://api.ktor.io/ktor-client/ktor-client-core/io.ktor.client/index.html

MVVM
https://github.com/icerockdev/moko-mvvm

Ktor 网络请求，除了 core ,需要依赖对应平台库代码，以下是对应表
https://ktor.io/docs/http-client-engines.html#limitations


