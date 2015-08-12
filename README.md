[![Android Gems](http://www.android-gems.com/badge/jjhesk/DisqusSDK-Android.svg?branch=master)](http://www.android-gems.com/lib/jjhesk/DisqusSDK-Android)

# DisqusSDK-Android
This is the simple library for https://disqus.com/ developed on Android.

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-DisqusSDK--Android-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/1855)

## Development Chart
Check out the lastest progress of the [development](https://github.com/jjhesk/DisqusSDK-Android/network) graph.
## About
This library implements the Disqus API for use in Android applications. This librar is ongoing

## Download

## API RoadMap Support
- [x] Post comments
- [x] Post Comments more flexible
- [x] Updated authentication OAuth2.0 standard
- [x] Additional support for the CacheUrl mechanism

### Gradle

Add the following to your `build.gradle`:
[ ![Download](https://api.bintray.com/packages/jjhesk/maven/disqus/images/download.svg) ](https://bintray.com/jjhesk/maven/disqus/_latestVersion)
```gradle
repositories {
    maven { url 'https://dl.bintray.com/jjhesk/maven' }
}
dependencies {
 compile 'DisqusSDK-Android:disqus:0.1.2'
 compile 'com.squareup.retrofit:retrofit:1.7.1'
 compile 'com.squareup.okhttp:okhttp-urlconnection:2.2.0'
 compile 'com.squareup.picasso:picasso:2.5.0'
 compile 'io.realm:realm-android:0.80.0'
}
```

## Authentication

### Using AuthorizeActivity

1. Use `AuthorizeUtils.createIntent` to create a new `Intent` with your application settings.
2. Start the activity with `startActivityForResult`.
3. Implement 'onActivityResult' to get the access token object:
```java
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode == RESULT_OK) {
                // Auth completed, get the access token
                AccessToken accessToken = data.getParcelableExtra(AuthorizeActivity.EXTRA_ACCESS_TOKEN);
            } else {
                // Auth failed
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
```
### Using AuthorizeFragment

1. Add the fragment to your activity via `AuthorizeFragment.newInstance`.

2. Your activity should implement the `AuthorizeFragment.AuthorizeListener` callback.

3. You may need to handle failures/cancellations within your activity, e.g. if back is pressed as
    currently the cancel button on the Disqus auth page doesn't seem to work.

### Google, Facebook, Twitter

Third party logins are not supported at this time.

## Basic usage

### Create configuration

Use the `ApiConfig` class to set your app configuration
```java
    ApiConfig apiConfig = new ApiConfig("__API_KEY__", "__AccessToken__", LogLevel.BASIC);
```
Use the `ApiConfig` class to setup your configuration with api secret
```java
    ApiConfig apiConfig = new ApiConfig("__API_KEY__", RestAdapter.LogLevel.BASIC);
    apiConfig.setApiSecret("__API_SECRET__");
    ApiClient setup = new ApiClient(apiConfig);
```
#### Standard options

* API key - mandatory for all requests.
* Access token - required for requests that require authentication.
* Referrer - required for some requests that perform domain checks, should match a domain in your
Disqus app settings.
* Log level - set the Retrofit logging level, see [Square's docs](http://square.github.io/retrofit/javadoc/retrofit/RestAdapter.LogLevel.html) for details.

#### Other options

* API secret - intended for server to server requests as an alternative to the API key/access token
and provided for completeness. Can be used from a mobile app but this presents security risks and is
not recommended.

### Create client

As of version 0.0.5 the library is using [Retrofit](http://square.github.io/retrofit/) for requests.

The `ApiClient` can be used to create Disqus resource objects based on the Retrofit interfaces
defined in the `me.philio.disqus.api.resources` package. It works as a wrapper to the Retrofit
`RestAdapter` and configures the adapter and deserialisation options for Gson.
```java
    ApiClient apiClient = new ApiClient(apiConfig);
```
### Create resource and make requests
```java
    Applications applications = apiClient.createApplications();
    Response<Usage> usage = applications.listUsage("MyApp", 7);
```
All resources and requests match the naming conventions defined in the Disqus API documentation, but
often method signatures are kept as simple as possible.


```java
  setup.createThreads().listPostByIDAsync(comment_id, "hypebeast", cb);
  
  
  
    private void getPost() {
        try {
            base.getComments("1008680 http://hypebeast.com/?p=1008680", new Callback<com.hkm.disqus.api.model.Response<List<Post>>>() {
                @Override
                public void success(com.hkm.disqus.api.model.Response<List<Post>> posts, Response response) {

                    com.hkm.disqus.api.model.Response<List<Post>> d = posts;

                    Log.d(TAG, "now its working now");
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d(TAG, error.getMessage());
                }
            });
        } catch (ApiException e) {

        }
    }

```
In general:

* Named parameters are required and should not be null.

* Optional parameters can be provided as a `Map` where applicable, refer to the Disqus documentation
for details of optional parameters.

### Response format

All requests will return a `Response` object. Typical responses contain an error code (which will
always be 0 as errors throw exceptions), an optional cursor (which can be used for pagination) and
some data which is a generic type.

The data is usually an object or list of objects and for the majority of requests will be one of the
models defined in the API package. Some requests return empty structures so to avoid parsing issues
the data type is either `Object` or `List<Object>`, the response data for these requests can be
disregarded.

## Known issues

### Disqus issues

1. The cancel button for the authorisation page doesn't work.
2. Related param has no effect when used with `Blacklists.list()` method.
