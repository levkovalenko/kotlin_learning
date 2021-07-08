package com.example.demo.repository

import com.example.demo.entity.Article
import com.example.demo.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<Article, Long> {
    fun findByUser(user: User?): Article?
    fun findByTextContaining(text: String): List<Article> 
}
