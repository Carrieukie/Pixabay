AdaLabs-PixabayAPI Android Take Home Assignment
==============

Problem definition
You will be using the Pixabay API to  create this android application
Sign up for the API key  on the following Link https://pixabay.com/api/docs/#api_search_images
You can use Kotlin for this

Requirements
----

* The user SHOULD see a list of images and the username of the owner.
* The user SHOULD select an image and be able to view image details.
* The user SHOULD search for images and see results in a list.
* When the app opens let the app search for pictures of dogs as default
* The android application SHOULD be able to work offline.

# APK

Find an apk that caters for the above functional requirements on the [assets folder](https://github.com/Carrieukie/AdaLabs-PixabayAPI/tree/dev/assets).

Min Api Level: 21

Build System : [Gradle](https://gradle.org/)


### Tech-stack

* Tech-stack
    * [Kotlin](https://kotlinlang.org/) - a cross-platform, statically typed, general-purpose programming language with type inference.
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations.
    * [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - handle the stream of data asynchronously that executes sequentially.
    * [Dagger hilt](https://dagger.dev/hilt/) - a pragmatic lightweight dependency injection framework.
    * [Jetpack](https://developer.android.com/jetpack)
        * [Room](https://developer.android.com/topic/libraries/architecture/room) - a persistence library provides an abstraction layer over SQLite.
        * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - is an observable data holder.
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform action when lifecycle state changes.
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way.
        * [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - The Paging library helps you load and display pages of data from a larger dataset from local storage or over network. This approach allows your app to use both network bandwidth and system resources more efficiently. .
        * [Navigation components](https://developer.android.com/guide/navigation/navigation-getting-started) - Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app.

    * [Glide](https://bumptech.github.io/glide/) - Glide is a fast and efficient image loading library for Android focused on smooth scrolling. Glide offers an easy to use API, a performant and extensible resource decoding pipeline and automatic resource pooling.
    * [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java

    
* CI/CD
    * Github Actions

* Architecture
    * MVVM - Model View View Model

## App Architecture
   This app integrates directly into the recommended [Android app architecture](https://developer.android.com/jetpack/guide). It majorly uses the paging library to load and display pages of data from a larger dataset from local storage or over network. This approach allows your app to use both network bandwidth and system resources more efficiently.The paging lib operates in three layers :
   
   - The repository layer
   - The ViewModel layer
   - The UI layer

     <img src="https://developer.android.com/topic/libraries/architecture/images/paging3-library-architecture.svg" />
      
      ### Repository layer

      The primary Paging library component in the repository layer is RemoteMediator. A RemoteMediator object handles paging from a layered data source, such as a network data source with a local database cache.It Defines a set of callbacks used to incrementally load data from a remote source into a local source wrapped by a PagingSource, e.g., loading data from network into a local db cache.
      
      ### ViewModel layer
      The Pager component provides a public API for constructing instances of PagingData that are exposed in reactive streams, based on a PagingSource object and a PagingConfig configuration object. The component that connects the ViewModel layer to the UI is PagingData. A PagingData object is a container for a snapshot of paginated data. It queries a PagingSource object and stores the result.
      
      ### UI layer
      
      The primary Paging library component in the UI layer is PagingDataAdapter, a RecyclerView adapter that handles paginated data.This class is a convenience wrapper around AsyncPagingDataDiffer that implements common default behavior for item counting, and listening to update events.To present a Pager, use collectLatest to observe Pager.flow and call submitData whenever a new PagingData is emitted.PagingDataAdapter listens to internal PagingData loading events as pages are loaded, and uses DiffUtil on a background thread to compute fine grained updates as updated content in the form of new PagingData objects are received.

## Testing

The App has tests on Fragments as well as unit tests under the Android Test packages in respective modules.

## Screenshots

   * Screenshots
    
     <img src="https://github.com/Carrieukie/AdaLabs-PixabayAPI/blob/dev/assets/screenshot2.jpg" width="320"/>
     
     
     <img src="https://github.com/Carrieukie/AdaLabs-PixabayAPI/blob/dev/assets/screenshot1.jpg" width="320"/>
     
## Demo recording

   * When the device is online.
 
        <img src="https://github.com/Carrieukie/AdaLabs-PixabayAPI/blob/dev/assets/online.gif" width="320"/>
     
   * When the device is offline.
   
        <img src="https://github.com/Carrieukie/AdaLabs-PixabayAPI/blob/dev/assets/offline.gif" width="320"/>
        

    
