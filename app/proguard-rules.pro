# Pinax AI Guardian ProGuard Rules

# Keep Android components
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.Fragment
-keep public class * extends androidx.fragment.app.Fragment

# Keep TensorFlow Lite
-keep class org.tensorflow.** { *; }
-keep class org.tensorflow.lite.** { *; }
-dontwarn org.tensorflow.**

# Keep Room Database
-keep class androidx.room.** { *; }
-dontwarn androidx.room.**

# Keep Hilt
-keep class dagger.hilt.** { *; }
-keep class hilt_aggregated_deps.** { *; }
-keep class ** extends dagger.hilt.android.lifecycle.HiltViewModel { *; }

# Keep Kotlin metadata
-keepclassmembers class ** {
    *** Companion;
}
-keep class kotlin.** { *; }
-keep interface kotlin.** { *; }
-dontwarn kotlin.**

# Keep serialization
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**
-keep class kotlinx.serialization.** { *; }

# Keep our app classes
-keep class com.pinax.guardian.** { *; }

# Remove logging in release
-assumenosideeffects class timber.log.Timber { *; }
-assumenosideeffects class android.util.Log { *; }

# Optimization
-optimizationpasses 5
-dontusemixedcaseclassnames
-verbose
-allowaccessmodification
-repackageclasses com.pinax.guardian.obfuscated