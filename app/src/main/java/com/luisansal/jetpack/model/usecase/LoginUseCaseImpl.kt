package com.luisansal.jetpack.model.usecase

import com.luisansal.jetpack.common.executor.PostExecutionThread
import com.luisansal.jetpack.common.executor.ThreadExecutor
import com.luisansal.jetpack.common.observer.BaseSingleObserver
import com.luisansal.jetpack.model.domain.Login
import com.luisansal.jetpack.model.repository.interfaces.LoginCloudRepository
import com.luisansal.jetpack.model.usecase.interfaces.LoginUseCase
import com.luisansal.jetpack.model.usecase.interfaces.UseCase
import io.reactivex.Single
import javax.inject.Inject


class LoginUseCaseImpl @Inject constructor(private val loginCloudRepository: LoginCloudRepository,
                                            threadExecutor: ThreadExecutor,
                                            postExecutionThread: PostExecutionThread) : UseCase(threadExecutor, postExecutionThread)
        , LoginUseCase {


    override fun login(usr: String, pwd: String, observer: BaseSingleObserver<Login>): Single<Login> {
        val single = loginCloudRepository.getLogin(usr, pwd)
        return execute(single, observer)
    }

}