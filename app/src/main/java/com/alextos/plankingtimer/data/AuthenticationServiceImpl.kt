package com.alextos.plankingtimer.data

import android.util.Log
import com.alextos.plankingtimer.domain.services.AuthenticationService
import com.alextos.plankingtimer.domain.util.Result
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

class AuthenticationServiceImpl: AuthenticationService {

    companion object {
        val TAG: String = AuthenticationServiceImpl::class.java.simpleName
    }

    private val auth = Firebase.auth

    override fun signUp(
        email: String,
        password: String,
        completion: (Result<String>) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    completion(Result.Success(auth.currentUser?.uid))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    completion(Result.Error(message = task.exception?.localizedMessage ?: ""))
                }
            }
    }

    override fun login(
        email: String,
        password: String,
        completion: (Result<String>) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmailAndPassword:success")
                    completion(Result.Success(auth.currentUser?.uid))
                } else {
                    Log.w(TAG, "signInWithEmailAndPassword:failure", task.exception)
                    completion(Result.Error(message = task.exception?.localizedMessage ?: ""))
                }
            }
    }
}