package com.luisansal.jetpack.model.usecase.interfaces

import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.common.observer.BaseSingleObserver

interface UserUseCase {
    fun saveUser(user: User,subscriber : BaseCompletableObserver)
    fun getUserByDni(dni: String,subscriber : BaseSingleObserver<User>)
}