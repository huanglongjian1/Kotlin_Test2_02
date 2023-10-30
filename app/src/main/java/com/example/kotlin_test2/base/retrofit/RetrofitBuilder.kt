package com.android.kotlin_test1.base.retrofit

import com.coder.vincent.sharp_retrofit.call_adapter.flow.FlowCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    val okHttpClient: OkHttpClient
        get() {
            // create http client
            val httpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
                val original = chain.request()

                //header
                val request =
                    original.newBuilder().method(original.method, original.body).build()

                return@Interceptor chain.proceed(request)
            }).readTimeout(30, TimeUnit.SECONDS)

            httpClient.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })

            return httpClient.build()
        }

    private const val BASE_URL = "https://5e510330f2c0d300147c034c.mockapi.io/"

    private fun getRetrofit(BASEURL: String): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory.create()) //kotlin flow 支持
            .build() //Doesn't require the adapter
    }

    fun <T> createService(clazz: Class<T>, baseUrl: String): T = getRetrofit(baseUrl).create(clazz)
    inline fun <reified T> create(baseUrl: String): T = createService(T::class.java, baseUrl)
}
