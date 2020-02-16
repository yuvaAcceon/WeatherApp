package com.yuvasai.baseproject.di.modules

import android.os.Build
import com.igweze.ebi.simplecalladapter.SimpleCallAdapterFactory
import com.mfino.goodmoney.server.interceptor.Http2FixInterceptor
import com.yuvasai.baseproject.BuildConfig
import com.yuvasai.baseproject.repository.ILocalDataRepository
import com.yuvasai.baseproject.server.apis.Api
import com.yuvasai.baseproject.server.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicReference

@Module
class ApiModule {

    private var baseURL: String = BuildConfig.HOST

    @Provides
    internal fun provideOkHttpClient(localDataRepository: ILocalDataRepository): OkHttpClient {
        val okHttpReference = AtomicReference<OkHttpClient>()
        val okHttpClientBuilder = OkHttpClient.Builder()
        /*okHttpClientBuilder.addInterceptor { chain ->
            var request = chain.request()
            request = request.newBuilder().addHeader("Content-Type","application/x-www-form-urlencoded").build()
            chain.proceed(request)
        }*/
        okHttpClientBuilder.addInterceptor(
            AuthInterceptor(
                localDataRepository
            )
        )
//        if (BuildConfig.DEBUG) {
        okHttpClientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//        }
        okHttpClientBuilder.addInterceptor(Http2FixInterceptor(okHttpReference))
        okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(60, TimeUnit.SECONDS)

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            val spec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384
                )
                .build()
            okHttpClientBuilder.connectionSpecs(Collections.singletonList(spec))
        }

        val okHttp = okHttpClientBuilder.build()
        okHttpReference.set(okHttp)
        return okHttp
    }

    @Provides
    internal fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .addCallAdapterFactory(SimpleCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
    }

    @Provides
    internal fun provideRetrofit(retrofitBuilder: Retrofit.Builder): Retrofit {
        return retrofitBuilder
            .baseUrl(baseURL)
            .build()
    }

    @Provides
    internal fun provideAuthApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

}