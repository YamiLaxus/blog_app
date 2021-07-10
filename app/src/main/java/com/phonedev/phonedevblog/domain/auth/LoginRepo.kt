package com.phonedev.phonedevblog.domain.auth

import com.google.firebase.auth.FirebaseUser

interface LoginRepo {

    suspend fun signIn(email: String, password: String): FirebaseUser?

}