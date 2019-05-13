package com.luisansal.jetpack.model.usecase

import com.luisansal.jetpack.common.executor.PostExecutionThread
import com.luisansal.jetpack.common.executor.ThreadExecutor
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.common.observer.BaseSingleObserver
import com.luisansal.jetpack.model.repository.UserRepository
import com.luisansal.jetpack.model.usecase.interfaces.UseCase
import com.luisansal.jetpack.model.usecase.interfaces.UserUseCase
import javax.inject.Inject


class UserUseCaseImpl @Inject constructor(private val userRepository: UserRepository,threadExecutor: ThreadExecutor,postExecutionThread: PostExecutionThread) : UseCase(threadExecutor,postExecutionThread)
, UserUseCase{


    override fun saveUser(user: User, subscriber: BaseCompletableObserver) {
        val completable = userRepository.save(user)
        execute(completable, subscriber)
    }

    override fun getUserByDni(dni: String, subscriber: BaseSingleObserver<User>) {
        val single = userRepository.getUserByDni(dni)
        execute(single, subscriber)
    }
}