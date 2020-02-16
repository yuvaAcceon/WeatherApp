package com.yuvasai.baseproject.server.response

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticlesResponse {

    @SerializedName("response")
    @Expose
    var response: JsonElement? = null

    fun parse(): List<Article>{
        val rootObject = response as JsonObject
        val responseArray = rootObject.getAsJsonArray("docs")
        return Gson().fromJson(responseArray, ArrayList<Article>()::class.java)
    }
}

class Article {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("journal")
    @Expose
    var journal: String? = null
    @SerializedName("eissn")
    @Expose
    var eissn: String? = null
    @SerializedName("publication_date")
    @Expose
    var publicationDate: String? = null
    @SerializedName("article_type")
    @Expose
    var articleType: String? = null

}