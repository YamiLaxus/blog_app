package com.phonedev.phonedevblog.domain.home

import com.phonedev.phonedevblog.core.Result
import com.phonedev.phonedevblog.data.model.Post

interface HomeScreenRepo {

    suspend fun getLatestPosts(): Result<List<Post>>

}