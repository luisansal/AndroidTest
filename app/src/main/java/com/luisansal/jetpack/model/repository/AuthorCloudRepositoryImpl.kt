package com.luisansal.jetpack.model.repository

import com.luisansal.jetpack.model.api.AuthorApi
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.model.repository.interfaces.AuthorCloudRepository
import io.reactivex.Single
import javax.inject.Inject

class AuthorCloudRepositoryImpl @Inject constructor(private val authorApi: AuthorApi) : AuthorCloudRepository {

    override fun getAuthors(): Single<List<Author>> {
        return authorApi.getAuthors()
                .map {
                    var authors = mutableListOf<Author>()
                    authors.addAll(it.resultado)
                    authors
                }
    }
}