package com.kvest.nba.api.dto

import com.squareup.moshi.Json

class PageMetaDTO(
    @field:Json(name = "total_pages")
    val totalPages: Int,
    @field:Json(name = "current_page")
    val currentPage: Int,
    @field:Json(name = "next_page")
    val nextPage: Int?,
    @field:Json(name = "per_page")
    val perPage: Int,
    @field:Json(name = "total_count")
    val totalCount: Int,
)