package com.yuvasai.baseproject.server.interceptor

import com.yuvasai.baseproject.repository.ILocalDataRepository
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val localUserDataRepository: ILocalDataRepository) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        localUserDataRepository.getCurrentToken().let {
            builder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(builder.build())
    }
}