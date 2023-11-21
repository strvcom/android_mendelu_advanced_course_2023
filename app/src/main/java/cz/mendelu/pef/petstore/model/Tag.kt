package cz.mendelu.pef.petstore.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tag(
    val id: Long?,
    val name: String?
)