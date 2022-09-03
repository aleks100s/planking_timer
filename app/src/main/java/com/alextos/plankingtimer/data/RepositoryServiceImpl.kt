package com.alextos.plankingtimer.data

import com.alextos.plankingtimer.domain.model.main.TimerQueue
import com.alextos.plankingtimer.domain.services.RepositoryService
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class RepositoryServiceImpl: RepositoryService {

    companion object {
        val TAG: String = RepositoryServiceImpl::class.java.simpleName
    }

    private val db = Firebase.firestore

    override fun subscribeTimerList(collection: String): Flow<List<TimerQueue>> {
        return callbackFlow {
            val listener = db.collection("FXBr4Fu5S8VMpESPOUWagiIbzIC3")
                .addSnapshotListener { value, error ->
                    error?.let {
                        close()
                        return@addSnapshotListener
                    }
                    value?.let { snapshot ->
                        val list = snapshot.toObjects(TimerQueue::class.java)
                        trySend(list)
                    }
                }
            awaitClose {
                listener.remove()
            }
        }
    }

    override fun saveTimer(timer: TimerQueue, collection: String) {
        TODO("Not yet implemented")
    }

    override fun deleteTimer(timer: TimerQueue, collection: String) {
        TODO("Not yet implemented")
    }
}