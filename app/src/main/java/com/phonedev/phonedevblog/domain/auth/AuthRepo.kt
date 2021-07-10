package com.phonedev.phonedevblog.domain.auth

import com.google.firebase.auth.FirebaseUser

interface AuthRepo {

    suspend fun signIn(email: String, password: String): FirebaseUser?
    suspend fun singUp(email: String, password: String, username: String): FirebaseUser?

}