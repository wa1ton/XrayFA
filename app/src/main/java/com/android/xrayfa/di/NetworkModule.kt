package com.android.xrayfa.di

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import xrayfa.tun2socks.qualifier.Application
import java.util.concurrent.TimeUnit


@Module
class NetworkModule {
    @Provides
    fun provideInterceptor(
        @Application context: Context
    ): Interceptor {
        val versionName = context.packageManager.getPackageInfo(context.packageName, 0).versionName
        return Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Accept","*/*")
                .header("User-Agent", "xrayFA/$versionName")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }
    }


    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }
}