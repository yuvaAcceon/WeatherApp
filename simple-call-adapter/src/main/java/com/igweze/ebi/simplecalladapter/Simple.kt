package com.igweze.ebi.simplecalladapter

import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class Simple<R>(private val call: Call<R>) {

    @Throws(IOException::class)
    fun run() = call.execute()

    fun process(onSuccess: (response: R?) -> Unit, onFailure: (throwable: Throwable?) -> Unit): Subscription {
        val subscription = Subscription()

        // define callback
        val callback = object : Callback<R> {

            override fun onFailure(call: Call<R>?, t: Throwable?)  {
                if (!subscription.isDisposed()) onFailure(t)
            }

            override fun onResponse(call: Call<R>?, response: Response<R>?) {
                if (!subscription.isDisposed()) handleResponse(response,onSuccess,onFailure)
            }
        }

        // enqueue network call
        call.enqueue(callback)
        // return subscription
        return subscription
    }


    private fun handleResponse(response: Response<R>?, onSuccess: (response: R?) -> Unit, onFailure: (throwable: Throwable?)-> Unit) {
        if (response?.isSuccessful == true) {
            val responseRootObject = Gson().toJsonTree(response.body())
            var actualResponse: JsonObject?
            actualResponse = if(responseRootObject.isJsonArray){
                responseRootObject.asJsonArray.first().asJsonObject
            }else{
                responseRootObject.asJsonObject
            }
            onSuccess(response.body())
            /*if(actualResponse.has("success") && actualResponse.get("success").asBoolean){
                onSuccess(response.body())
            }else{
                onFailure(ServerResponseException(Gson().fromJson(actualResponse,ErrorResponse::class.java)))
            }*/
        }else {
            if (response?.code() in 400..511) onFailure(HttpException(response) as Throwable?)
            else onSuccess(response?.body())
        }
    }
}
