package com.luisansal.jetpack.common.interfaces

interface CrudListener<T> {

    var oBject: T?

    val objects: List<T>

    fun onList()

    fun onNew()

    fun onEdit()

    fun setOBjects(oBjects: List<T>)
}
