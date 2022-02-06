package com.example.tinf.domain

import com.example.tinf.api.DevelopersLifeApi
import java.util.concurrent.CopyOnWriteArrayList

interface PostsRepository {

    suspend fun getPost(): Post

    suspend fun getPreviousPost(): Post?

    class Impl(private val api: DevelopersLifeApi) : PostsRepository {

        private val cache = CopyOnWriteArrayList<Post>()
        private var currentPostIndex = 0

        override suspend fun getPost(): Post {
            return if (currentPostIndex >= cache.size-1) {
                val post = api.getPost()
                cache += post
                currentPostIndex += 1
                post
            } else {
                currentPostIndex += 1
                cache[currentPostIndex]
            }
        }

        override suspend fun getPreviousPost(): Post? {
            return if (currentPostIndex > 0) {
                cache.getOrNull(--currentPostIndex)
            } else {
                return null
            }
        }
    }
}