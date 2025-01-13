-keep class io.ktor.** { *; }
-keep class kotlinx.serialization.** { *; }
-keep class io.ktor.client.network.sockets.** { *; }
-keep class io.ktor.client.plugins.* { *; }
-keep class io.ktor.util.* { *; }
-keep class io.ktor.utils.io.* { *; }
-keep class java.lang.management.* { *; }
-dontwarn io.ktor.client.network.sockets.SocketTimeoutException
-dontwarn java.lang.management.RuntimeMXBean

# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-keeppackagenames okhttp3.internal.publicsuffix.*
-adaptresourcefilenames okhttp3/internal/publicsuffix/PublicSuffixDatabase.gz

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt and other security providers are available.
-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**

-keep class org.mifospay.core.network.services.* { *;}
-keep class de.jensklingenberg.ktorfit.converter.** { *; }
-keep class de.jensklingenberg.ktorfit.** { *; }
-keeppackagenames de.jensklingenberg.ktorfit.*
