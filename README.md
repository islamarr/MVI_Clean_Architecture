# MVI_Clean_Architecture_Sample

![GitHub followers][40]     ![GitHub Repo stars][41]     ![GitHub forks][42]

About this project
--------------
🚀 Grid view List of pictures.

🛠 Simple implementation for MVI architecture pattern and Clean Architecture.

🛠 Unit tests included, test coverage is round 100%.

🛠 SOLID principles.

🛠 Standard Coding Style.


Architecture pattern Used
--------------
[MVI][1]

[Why I prefer MVI?][5]

![architecture pattern][2]

Testing Coverage
--------------

![Testing][6]


Libraries & Tools Used
--------------

* [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
* [Test][4] - An Android testing framework for unit and runtime UI tests.
* [Architecture][10] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
    * [Data Binding][11] - Declaratively bind observable data to UI elements.
    * [Lifecycles][12] - Create a UI that automatically responds to lifecycle events.
    * [Navigation][14] - Handle everything needed for in-app navigation.
    * [ViewModel][17] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
      asynchronous tasks for optimal execution.
    * [Repository][3] - A Module that handle data operations, You can consider repositories to be mediators between different data sources.
    * [Kotlin Flows][21] - A stream of data that can be computed asynchronously.
    * [Retrofit][20] - A simple library that is used for network transaction.
    * [Glide][90] For image loading.
    * [Hilt][92]: For [dependency injection][93]
    * [Kotlin Coroutines][91] For managing background threads with simplified code and reducing needs for callbacks.
* Other tools/plugins   
    * [SonarLint plugin][50] - Static Code Analysis that identifies and helps you fix quality and security issues as you code.
  

Other Projects
--------------
[Sona3][30]
[Recorder][31]
[Prayer Now][32]
[Mn Ahyaha][33]
[shutterstock_image_list][34]


[0]: https://developer.android.com/jetpack/components
[1]: https://cycle.js.org/model-view-intent.html
[2]: https://github.com/islamarr/MVI_Clean_Architecture/blob/master/app/src/main/res/drawable/mvi_diagram.png
[3]: https://developer.android.com/jetpack/guide#fetch-data
[4]: https://developer.android.com/training/testing/
[5]: http://hannesdorfmann.com/android/mosby3-mvi-1/
[6]: https://github.com/islamarr/MVI_Clean_Architecture/blob/master/app/src/main/res/drawable/unit_test_coverage.png
[10]: https://developer.android.com/jetpack/arch/
[11]: https://developer.android.com/topic/libraries/data-binding/
[12]: https://developer.android.com/topic/libraries/architecture/lifecycle
[14]: https://developer.android.com/topic/libraries/architecture/navigation/
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel
[20]: https://square.github.io/retrofit
[21]: https://developer.android.com/kotlin/flow
[30]: https://github.com/islamarr/Sona3
[31]: https://github.com/islamarr/recorder
[32]: https://play.google.com/store/apps/details?id=com.AppRocks.now.prayer
[33]: https://play.google.com/store/apps/details?id=com.Ihsan.Ahyaha
[34]: https://github.com/islamarr/shutterstock_image_list
[40]: https://img.shields.io/github/followers/islamarr?style=social
[41]: https://img.shields.io/github/stars/islamarr/MVI_Clean_Architecture?style=social
[42]: https://img.shields.io/github/forks/islamarr/MVI_Clean_Architecture?style=social
[50]: https://www.sonarlint.org/
[90]: https://bumptech.github.io/glide/
[91]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[92]: https://developer.android.com/training/dependency-injection/hilt-android
[93]: https://developer.android.com/training/dependency-injection

