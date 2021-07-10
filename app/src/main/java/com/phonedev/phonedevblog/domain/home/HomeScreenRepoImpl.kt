package com.phonedev.phonedevblog.domain.home

import com.phonedev.phonedevblog.core.Result
import com.phonedev.phonedevblog.data.model.Post
import com.phonedev.phonedevblog.data.remote.home.HomeScreenDataSource

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource): HomeScreenRepo {

    override suspend fun getLatestPosts(): Result<List<Post>> = dataSource.getLatestPosts()


}