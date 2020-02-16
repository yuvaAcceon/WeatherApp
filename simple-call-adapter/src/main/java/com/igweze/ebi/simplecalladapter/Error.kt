package com.igweze.ebi.simplecalladapter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Error {

    @SerializedName("objectName")
    @Expose
    val objectName: String? = null
    @SerializedName("attributeName")
    @Expose
    val attributeName: String? = null
    @SerializedName("attributeValue")
    @Expose
    val attributeValue: String? = null
    @SerializedName("errorCode")
    @Expose
    val errorCode: Any? = null
    @SerializedName("errorMessage")
    @Expose
    val errorMessage: String? = null
    @SerializedName("methodName")
    @Expose
    val methodName: Any? = null
    @SerializedName("applicableAttributes")
    @Expose
    val applicableAttributes: Any? = null
   /* @SerializedName("error")
    @Expose
    val validationErrors: List<Error>? = null*/
}