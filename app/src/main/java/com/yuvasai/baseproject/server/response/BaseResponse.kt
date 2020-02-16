package com.yuvasai.baseproject.server.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.igweze.ebi.simplecalladapter.Error
import java.io.Serializable

open class BaseResponse : Serializable {

    @SerializedName("success", alternate = ["Success"])
    @Expose
    var success: String? = null

    @SerializedName("statusCode")
    @Expose
    var statusCode: String? = null

    @SerializedName("message", alternate = ["errorMessage"])
    @Expose
    var message: String? = null

    //temporary, its use for pdf view in tos screen , it shaould be removew later
    @SerializedName("image")
    @Expose
    var image: String? = null

    @SerializedName("xVerifyMsg")
    @Expose
    var xVerifyMsg: String? = null

    @SerializedName("xVerifyStatusCode")
    @Expose
    var xVerifyStatusCode: String? = null

    @SerializedName("errorCode")
    @Expose
    var errorCode: String? = null

    @SerializedName("uuid")
    @Expose
    var uuid: String? = null

    @SerializedName("wrongAttemptsLeft")
    @Expose
    val wrongAttemptsLeft: Int? = null

    @SerializedName("wrongAttemptsDone")
    @Expose
    val wrongAttemptsDone: Int? = null

    @SerializedName("error")
    @Expose
    public val error: List<Error>? = null

    @SerializedName("ERROR_TYPE")
    @Expose
    val eRRORTYPE: String? = null

    @SerializedName("standingInstructionSummary")
    @Expose
    val standingInstructionSummary: Any? = null

}