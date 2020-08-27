package com.soumik.weathermvvm.network

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.soumik.weathermvvm.BuildConfig
import com.soumik.weathermvvm.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors


var apiClient = RetrofitClient().apiInterface

class RetrofitClient {

    var httpClient = OkHttpClient.Builder().addInterceptor(QueryParameterAddInterceptor()).apply {
        addInterceptor(
            LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("LOG")
                .response("LOG")
                .executor(Executors.newSingleThreadExecutor())
                .build()
        )
    }

    val client = httpClient.build()

    var retrofit:Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()



    var apiInterface:ApiInterface = retrofit.create(ApiInterface::class.java)
}