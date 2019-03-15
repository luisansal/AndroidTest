package com.luisansal.jetpack.interfaces

import com.luisansal.jetpack.model.domain.User

interface CrudListener<T> {

    var oBject: User?

    val objects: List<User>?
    fun onList()

    fun onNew()

    fun onEdit()

    fun setOBjects(oBjects: List<T>)
}
