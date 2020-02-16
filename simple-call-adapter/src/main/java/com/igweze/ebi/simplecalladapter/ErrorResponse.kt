package com.igweze.ebi.simplecalladapter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ErrorResponse {

    @SerializedName("success")
    @Expose
    var success: String? = null

    @SerializedName("statusCode")
    @Expose
    var statusCode: String? = null

    @SerializedName("message", alternate = ["errorMessage"])
    @Expose
    var message: String? = null

    @SerializedName("errorCode")
    @Expose
    var xErrorCode: String? = null

    @SerializedName("wrongAttemptsLeft")
    @Expose
    val wrongAttemptsLeft: Int? = null

    @SerializedName("wrongAttemptsDone")
    @Expose
    val wrongAttemptsDone: Int? = null

    @SerializedName("error")
    @Expose
    val error: List<Error>? = null

    @SerializedName("ERROR_TYPE")
    @Expose
    val eRRORTYPE: String? = null

    @SerializedName("response")
    @Expose
    val response: String? = null

    //linked external response
    @SerializedName("objectName")
    @Expose
    var objectName: String? = null
    @SerializedName("attributeName")
    @Expose
    var attributeName: String? = null
    @SerializedName("attributeValue")
    @Expose
    var attributeValue: String? = null
    @SerializedName("methodName")
    @Expose
    var methodName: Any? = null
    @SerializedName("applicableAttributes")
    @Expose
    var applicableAttributes: Any? = null
    // transfer limit
    @SerializedName("limitBreach")
    @Expose
    var limitBreach: List<String>? = null

    // transfer available limit
    @SerializedName("availableLimit")
    @Expose
    var availableLimit: List<String>? = null
    //
}