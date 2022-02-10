package com.example.randomuser.core.di

import com.example.randomuser.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/** ---D
 *
 * Dagger module to handle Retrofit initialization for injection
 *
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    /** ---
     * Provides Interceptor for logging API call
     * Filter messages in Logcat using `OkHttp` to view API request/response logs
     */
    internal val loggingInterceptor: HttpLoggingInterceptor
        @Provides
        get() = HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        )

    /** ---
     * Provides OkHttp to handle interceptors and other configurations such as read/write timeouts
     */
    @Provides
    @Singleton
    internal fun getHttpClient(
        interceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder
            .addInterceptor(interceptor)
            .addInterceptor(httpApiInterceptor())
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
        return builder.build()
    }

    /** ---
     * Provides Retrofit instance
     * Injected string @Named("serverUrl") for the api base url
     * and OkHttpClient
     */
    @Provides
    @Singleton
    internal fun provideApiRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    /** ---
     * Interceptor to handle adding of header in API request
     */
    private fun httpApiInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            request = request.newBuilder()
                .header("Content-Type", "application/json")
                .build()
            chain.proceed(request)
        }
    }
}