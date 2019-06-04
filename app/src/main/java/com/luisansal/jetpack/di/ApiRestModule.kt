package com.luisansal.jetpack.di

import com.luisansal.jetpack.BuildConfig
import com.luisansal.jetpack.model.api.AuthorApi
import com.luisansal.jetpack.model.api.LoginApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiRestModule {

    companion object {

        const val HEADER_NAME_CONTENT_TYPE = "Content-Type"
        const val HEADER_NAME_ACCEPT = "Accept"
        const val HEADER_NAME_AUTHORIZATION = "Authorization"
        const val HEADER_NAME_APP_VERSION = "appVersion"
        const val HEADER_NAME_AUTH = "typeAuth"

        const val HEADER_VALUE_JSON = "application/json"
        const val HEADER_VALE_URL_ENCODED = "application/x-www-form-urlencoded"
        const val HEADER_VALUE_BEARER = "Bearer"
        const val HEADER_VALUE_AUTH = "31e41dbf-72d8-422b-ab3c-58812e7bf4ab"

        const val NAMED_AUTH = "Auth"
        const val NAMED_NO_AUTH = "NoAuth"
        const val NAMED_TOKEN_AUTH = "TokenAuth"
        const val NAMED_SB2 = "SomosBelcorp"
        const val NAMED_GOOGLE = "Google"

        const val HOST_GOOGLE = "https://maps.googleapis.com"

    }

    /**
     * Creación OkHttpClient
     */

    @Provides
    @Singleton
    @Named(NAMED_TOKEN_AUTH)
    fun provideHttpClientTokenAuth(): OkHttpClient {
        return createOkHttpClient {
            header(HEADER_NAME_CONTENT_TYPE, HEADER_VALE_URL_ENCODED)
            header(HEADER_NAME_AUTH, HEADER_VALUE_AUTH)
        }
    }

    /**
     * Creación Retrofit
     */

    @Provides
    @Singleton
    @Named(NAMED_NO_AUTH)
    fun provideFFVVMobileNoAuth(): Retrofit {
        return createRetrofit(createOkHttpClient(), BuildConfig.HOST)
    }

    @Provides
    @Singleton
    @Named(NAMED_TOKEN_AUTH)
    fun provideFFVVMobileTokenAuth(@Named(NAMED_TOKEN_AUTH) httpClient: OkHttpClient): Retrofit {
        return createRetrofit(httpClient, BuildConfig.HOST)
    }

    @Provides
    @Singleton
    @Named(NAMED_AUTH)
    fun provideFFVVMobileAuth(@Named(NAMED_AUTH) httpClient: OkHttpClient): Retrofit {
        return createRetrofit(httpClient, BuildConfig.HOST)
    }

    @Provides
    @Singleton
    @Named(NAMED_GOOGLE)
    fun provideGoogle(): Retrofit {
        return createRetrofit(createOkHttpClient(), HOST_GOOGLE)
    }

    /**
     * Creación API's
     */
    @Provides
    fun provideAuthorApi(@Named(NAMED_NO_AUTH) retrofit: Retrofit): AuthorApi {
        return retrofit.create(AuthorApi::class.java)
    }

    @Provides
    fun provideLoginApi(@Named(NAMED_NO_AUTH) retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    /**
     * Helpers
     */

    private fun createRetrofit(httpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
    }

    private fun createOkHttpClient(f: (Request.Builder.() -> Request.Builder)? = null): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val appVersion = BuildConfig.VERSION_NAME
                    val requestBuilder = original.newBuilder()
                    val request = (f?.invoke(requestBuilder) ?: requestBuilder)
                            .header(HEADER_NAME_APP_VERSION, appVersion)

                    chain.proceed(request.build())
                }
                .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                })
                .readTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .build()
    }

}