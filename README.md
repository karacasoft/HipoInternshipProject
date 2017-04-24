# Overview
This is a simple Flickr client that displays recent photos, can show photos full-screen. 
It also has search functionality.

# Structure
The application uses MVP design pattern as described [here](https://github.com/googlesamples/android-architecture).

# Used Libraries
- Retrofit 2 : for API HTTP requests
- Gson : for serialization (also is compatible with Retrofit)
- ButterKnife : to bind view references
- Universal Image Loader : for easy lifecycle control of loading images
- PhotoView : for zoom functionality in full screen image mode
