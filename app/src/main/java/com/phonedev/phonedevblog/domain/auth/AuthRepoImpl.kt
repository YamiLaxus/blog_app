package com.phonedev.phonedevblog.domain.auth

import com.google.firebase.auth.FirebaseUser
import com.phonedev.phonedevblog.data.remote.auth.AuthDataSource

class AuthRepoImpl(private val dataSource: AuthDataSource): AuthRepo {
    override suspend fun signIn(email: String, password: String): FirebaseUser? = dataSource.signIn(email, password)
    override suspend fun singUp(email: String, password: String, username: String): FirebaseUser? = dataSource.singUp(email, password, username)
}