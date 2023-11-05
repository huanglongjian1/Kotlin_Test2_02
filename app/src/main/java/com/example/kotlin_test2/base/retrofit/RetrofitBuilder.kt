package com.example.kotlin_test2.base.retrofit

import com.coder.vincent.sharp_retrofit.call_adapter.flow.FlowCallAdapterFactory
import com.example.kotlin_test2.MyApp
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private val cacheFile = File(MyApp.getApplication().cacheDir, "mycache")
    private val cache = Cache(cacheFile, 1024 * 1024 * 50)// 50M 的缓存大小
    private val okHttpClient: OkHttpClient
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
            httpClient.run {
                cache(cache)
                connectTimeout(5, TimeUnit.SECONDS)
                readTimeout(5, TimeUnit.SECONDS)
                writeTimeout(5, TimeUnit.SECONDS)
                retryOnConnectionFailure(true)//错误重连
            }
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

    fun <T> createService(clazz: Class<T>, baseUrl: String): T =
        getRetrofit(baseUrl).create(clazz)

    inline fun <reified T> create(baseUrl: String): T = createService(T::class.java, baseUrl)
}
