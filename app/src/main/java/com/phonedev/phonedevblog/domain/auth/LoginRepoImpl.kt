package com.phonedev.phonedevblog.domain.auth

import com.google.firebase.auth.FirebaseUser
import com.phonedev.phonedevblog.data.remote.auth.LoginDataSource

class LoginRepoImpl(private val dataSource: LoginDataSource): LoginRepo {
    override suspend fun signIn(email: String, password: String): FirebaseUser? = dataSource.signIn(email, password)
}