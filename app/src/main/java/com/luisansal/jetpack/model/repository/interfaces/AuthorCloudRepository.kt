package com.luisansal.jetpack.model.repository.interfaces

import com.luisansal.jetpack.model.domain.Author
import io.reactivex.Single

interface AuthorCloudRepository {
    fun getAuthors(): Single<List<Author>>
}