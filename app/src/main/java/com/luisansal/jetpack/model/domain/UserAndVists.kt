package com.luisansal.jetpack.model.domain

import androidx.room.Embedded
import androidx.room.Relation

class UserAndVists {

    @Embedded
    lateinit var user: User

    @Relation(parentColumn = "id", entityColumn = "userId", entity = Visit::class)
    lateinit var visits: List<Visit>
}
