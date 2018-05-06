# CoordinatorsAndroid

This sample shows how to apply the coordinator pattern on android. Read more here:
[http://hannesdorfmann.com/android/coordinators-android](http://hannesdorfmann.com/android/coordinators-android)

### Login credentials
username: Hannes
password: 123

## Logout
Check overflow menu in news list

## Video

[https://www.youtube.com/watch?v=PfRLZeRLvTo&feature=youtu.be](https://www.youtube.com/watch?v=PfRLZeRLvTo&feature=youtu.be)

## Why not dagger?
Because it was too much boilerplate in combination with ViewModels from Architecture Components. Nevertheless, the idea of coordinators also totally works with dagger.
In this sample I do dependency injection manually. Check `AppViewModelFactory`.