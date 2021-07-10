package com.phonedev.phonedevblog.data.remote.home

import com.google.firebase.firestore.FirebaseFirestore
import com.phonedev.phonedevblog.core.Result
import com.phonedev.phonedevblog.data.model.Post
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource {

    suspend fun getLatestPosts(): Result<List<Post>> {
        val postList = mutableListOf<Post>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("posts").get().await()
        for(post in querySnapshot.documents){
            post.toObject(Post::class.java)?.let { fbPost ->
                postList.add(fbPost)
            }
        }
        return Result.Success(postList)
    }

}