package com.luisansal.jetpack.model.usecase.interfaces

import com.luisansal.jetpack.common.observer.BaseSingleObserver
import com.luisansal.jetpack.model.domain.Login
import io.reactivex.Single

interface LoginUseCase {

    fun login(usr: String, pwd: String, observer: BaseSingleObserver<Login>): Single<Login>
}
