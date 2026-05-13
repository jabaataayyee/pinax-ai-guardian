# Kotlin
-keep class kotlin.Metadata { *; }
-keep class kotlin.jvm.internal.** { *; }
-keepclassmembers class kotlin.Metadata {
    *** invoke(...);
}

# Room
-keep class androidx.room.** { *; }
-keep @androidx.room.Entity class * { *; }
-keepclasseswithmembers @androidx.room.Entity * {
    @androidx.room.*;
}

# Hilt
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.internal.GeneratedComponent { *; }

# TensorFlow Lite
-keep class org.tensorflow.** { *; }
-keep class com.google.mediapipe.** { *; }

# VOSK
-keep class org.vosk.** { *; }

# Coroutines
-keep class kotlinx.coroutines.** { *; }
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keep class kotlinx.coroutines.android.AndroidDispatcherFactory { *; }

# Keep App Classes
-keep class com.pinax.guardian.** { *; }
-keepclassmembers class com.pinax.guardian.** { *; }
-keep interface com.pinax.guardian.** { *; }

# Security
-keep class androidx.security.** { *; }

# Retrofit & OkHttp
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.<Http>* <methods>;
}

# GSON
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Timber
-keep class timber.log.** { *; }
