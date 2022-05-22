# MVI_Clean_Architecture_Sample

![GitHub followers][60]     ![GitHub Repo stars][61]     ![GitHub forks][62]

About this project
--------------
🚀 Grid view List of pictures. 

🛠 Simple implementation for MVI architecture pattern and Clean Architecture.

🛠 Unit tests included with test coverage round 100% for dataSources, repositories, UseCases and ViewModels.

🛠 SOLID principles.

🛠 Standard Coding Style.

🛠 Support Tablet screens.


The Business Details
--------------
- The application should have two screens:
  * The first screen have one view with thumbnails of all available photos returned from API.
  * When the user taps on one of the thumbnails, a second screen load to show the larger photo in the middle of the screen.
- Image Urls should be edited by omitting the proxy part “m.mobile.de/yams-proxy/”, prepending “https://” 
  and appending “?rule=mo-640.jpg” for normal images or “?rule=mo-1600.jpg” for larger one.
- Display a grid list with 2 columns in the portrait mode and 3 columns in the landscape mode.


Demo
--------------

![demo][0]


Architecture pattern Used
--------------

[MVI][1]

![architecture pattern][2]


Testing Coverage
--------------

![Testing][3]


Libraries & Tools Used
--------------

* [Foundation][4] - Components for core system capabilities, Kotlin extensions and support for
  multidex and unit testing.
* [Test][5] - An Android testing framework for unit and runtime UI tests.
* [Architecture][10] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
    * [View Binding][11] - To more easily write code that interacts with views. 
    * [Lifecycles][12] - Create a UI that automatically responds to lifecycle events.
    * [Navigation][13] - Handle everything needed for in-app navigation.
    * [ViewModel][14] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
      asynchronous tasks for optimal execution.
    * [Repository][15] - A Module that handle data operations, You can consider repositories to be mediators between different data sources.
    * [Retrofit][16] - A simple library that is used for network transaction.
    * [Hilt][17]: For [dependency injection][18]
    * [Kotlin Coroutines][19] For managing background threads with simplified code and reducing needs for callbacks.
    * [Kotlin Flows][20] - A stream of data that can be computed asynchronously.
    * [Glide][21] For image loading.

* Other tools/plugins   
    * [Google material design][30] to build high-quality digital experiences for Android.
    * [SonarLint plugin][31] - Static Code Analysis that identifies and helps you fix quality and security issues as you code.

Technical choices
--------------

**MVI vs MVVM**

- A consistent state during the lifecycle of Views. 
- As it is unidirectional, Data flow can be tracked and predicted easily. 
- It ensures thread safety as the state objects are immutable.
- Easy to debug, As we know the state of the object when the error occurred.
- It's more decoupled as each component fulfills its own responsibility.
- Testing the app also will be easier as we can map the business logic for each state.
- [Read more][40]

**RecyclerView vs listview**

- In RecyclerView, it is mandatory to use ViewHolder pattern Which optimize the performance.
- DiffUtil callback Which optimize the performance.

**Activities vs Fragments**

- I have used a single-activity architecture which allowed me to take full advantage of the Navigation component, which mean that a single activity that manages and host multiple fragments.
- The fragment is more lite weight than Activity.
- I also added simple animation in navigation between fragments.

**Hilt vs Dagger2 vs Koin**

- Hilt is built on top of the Dagger, and it comes with some advantages like simplify Dagger code and create a standard set of components and scopes to ease setup.
- As this project is simple, Hilt is the best one. For more complex projects I will go with Dagger2 to avoid some limitation of hilt.
- Hilt does not need factories for ViewModel, koin need.
- Hilt generate the code in the compile time, while koin in runtime.

**Coroutines vs RxJava**

- Coroutines come with many advantages over RxJava, beside that it is Kotlin-friendly design pattern, it is: 
	* Lightweight: You can run many coroutines on a single thread due to support for suspension.
    * Fewer memory leaks: Use structured concurrency to run operations within a scope.
    * Built-in cancellation support.
    * Many Jetpack libraries include extensions that provide full coroutines support.

**Retrofit vs Volley**

- Retrofit has a well-designed structure.
- Suspend function support.

**Moshi vs GSON**

- Moshi is a Kotlin-Friendly converter. 
- Moshi has better and more human-readable serialization failed messages.
- Moshi has Code-gen adapter for Kotlin, This is great! With help of annotations, you make the Serialization/Deserialization much faster and bypass the old slow reflection method that Gson uses!


**Glide**

- Glide very effective for almost any case where you need to fetch, resize, cache and display a remote image.
- Support thumbnail and placeholder which I needed in this project.


What's next
--------------
While the project scale up, Some points should be considered: 
- Parent classes should be added for common used classes like ViewModels and Use Cases.
- Analytics and tracking system should be implemented to provide insight on app usage and user engagement.
- Caching mechanism should be added to reduce network calls and improve the performance.
- Pagination should be handled from backend and client side.


Other Projects
--------------

* [ShutterStock image list][50] - MVVM Sample.
* [Recorder][51] - Another MVVM Sample.
* [Prayer Now][52] - One of the projects I developed has 15+ Million downloads.
* [Mn Ahyaha][53] - Side project I developed from scratch.


[0]: https://github.com/islamarr/MVI_Clean_Architecture/blob/master/app/src/main/res/drawable/demo.gif
[1]: https://cycle.js.org/model-view-intent.html
[2]: https://github.com/islamarr/MVI_Clean_Architecture/blob/master/app/src/main/res/drawable/mvi_diagram.png
[3]: https://github.com/islamarr/MVI_Clean_Architecture/blob/master/app/src/main/res/drawable/unit_test_coverage.png
[4]: https://developer.android.com/jetpack/components
[5]: https://developer.android.com/training/testing/

[10]: https://developer.android.com/jetpack/arch/
[11]: https://developer.android.com/topic/libraries/view-binding
[12]: https://developer.android.com/topic/libraries/architecture/lifecycle
[13]: https://developer.android.com/topic/libraries/architecture/navigation/
[14]: https://developer.android.com/topic/libraries/architecture/viewmodel
[15]: https://developer.android.com/topic/architecture/data-layer#create_the_repository
[16]: https://square.github.io/retrofit
[17]: https://developer.android.com/training/dependency-injection/hilt-android
[18]: https://developer.android.com/training/dependency-injection
[19]: https://developer.android.com/kotlin/coroutines
[20]: https://developer.android.com/kotlin/flow
[21]: https://bumptech.github.io/glide/

[30]: https://m3.material.io/
[31]: https://www.sonarlint.org/

[40]: http://hannesdorfmann.com/android/mosby3-mvi-1/

[50]: https://github.com/islamarr/shutterstock_image_list
[51]: https://github.com/islamarr/recorder
[52]: https://play.google.com/store/apps/details?id=com.AppRocks.now.prayer
[53]: https://play.google.com/store/apps/details?id=com.Ihsan.Ahyaha

[60]: https://img.shields.io/github/followers/islamarr?style=social
[61]: https://img.shields.io/github/stars/islamarr/MVI_Clean_Architecture?style=social
[62]: https://img.shields.io/github/forks/islamarr/MVI_Clean_Architecture?style=social