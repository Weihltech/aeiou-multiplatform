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

支持：
1、网络请求（未加密）
2、图片加载
3、ViewModule ，MVVM MVI


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

------
MVVM
https://github.com/icerockdev/moko-mvvm

-----
Ktor 网络请求，除了 core ,需要依赖对应平台库代码，以下是对应表
https://ktor.io/docs/http-client-engines.html#limitations

-----
依赖库介绍：
[Link](https://ktor.io/docs/serialization-client.html) io.ktor:ktor-client-content-negotiation 是 Ktor 的一个插件，它主要有两个用途12：
-请求内容需要做富文本传输解析等
-在客户端和服务器之间协商媒体类型。为此，它使用了 Accept 和 Content-Type 头12。
-在发送请求和接收响应时，以特定格式序列化/反序列化内容12。
-解析 Json 工件依赖 "io.ktor:ktor-serialization-kotlinx-json:2.3.6" , 依赖指向特定系列化工件 "org.jetbrains.kotlin:kotlin-serialization:1.9.20"

----
直接访问 github 项目资源
```
原始GitHub 文件地址： https://github.com/SebastianAigner/demo-image-api/blob/main/pictures.json
可Http访问资源内容的地址：https://raw.githubusercontent.com/SebastianAigner/demo-image-api/main/pictures.json
- raw.githubusercontent.com : 资源服务器
- SebastianAigner : 用户名
- demo-image-api : 仓库名
- master/demo/example.json : 文件地址
```


