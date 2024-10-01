package com.aryandi.prodia.data.api.model

data class PaginatedResultRemote<T>(val next: String?, val previous: String?, val results: T)