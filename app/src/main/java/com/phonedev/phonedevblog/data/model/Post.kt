package com.phonedev.phonedevblog.data.model

data class Post(
        val profile_picture: String = "",
        val profile_name: String = "",
        val post_timestamp: com.google.firebase.Timestamp? = null,
        val post_image: String = "")