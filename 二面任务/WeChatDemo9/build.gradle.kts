// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false//alias(libs.plugins.android.application)：表示 Android 应用插件，它提供了构建 Android 应用所需的所有任务和配置选项，比如打包 APK、处理资源文件、配置签名等。apply false 意味着这里只是声明了插件，但暂时没有应用到项目中，后续可能会在模块级的 build.gradle.kts 文件中具体应用。
    alias(libs.plugins.kotlin.android) apply false//是 Kotlin Android 插件，用于在 Android 项目中支持 Kotlin 语言，包括 Kotlin 代码的编译、与 Android 框架的集成等功能。同样 apply false 表示暂未应用。
    alias(libs.plugins.kotlin.compose) apply false//用于在 Android 项目中支持 Jetpack Compose，允许你使用 Compose 构建 Android 应用的 UI。apply false 表示暂未应用。
}

