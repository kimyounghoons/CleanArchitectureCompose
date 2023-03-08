package net.deali.data.response

import com.google.gson.annotations.SerializedName
import net.deali.coredata.response.BaseResponse


data class MovieSearchKeywordResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<Result>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
) : BaseResponse() {
    data class Result(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("name")
        val name: String? = null
    )
}