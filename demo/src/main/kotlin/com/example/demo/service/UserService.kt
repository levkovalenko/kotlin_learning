package com.example.demo.service

import com.example.demo.entity.Article
import com.example.demo.entity.Role
import com.example.demo.entity.User
import com.example.demo.repository.ArticleRepository
import com.example.demo.repository.RoleRepository
import com.example.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class UserService : UserDetailsService {
    @PersistenceContext
    private val em: EntityManager? = null

    @Autowired
    var userRepository: UserRepository? = null

    @Autowired
    var roleRepository: RoleRepository? = null

    @Autowired
    var articleRepository: ArticleRepository? = null

    @Autowired
    var bCryptPasswordEncoder: BCryptPasswordEncoder? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository!!.findByUsername(username) ?: throw UsernameNotFoundException("User not found")
    }

    fun findUserById(userId: Long): User {
        val userFromDb = userRepository!!.findById(userId)
        return userFromDb.orElse(User())
    }

    fun allUsers(): List<User> {
        return userRepository!!.findAll()
    }

    fun allRoles(): List<Role> {
        return roleRepository!!.findAll()
    }

    fun saveUser(user: User): Boolean {
        val userFromDB = userRepository!!.findByUsername(user.username)
        if (userFromDB != null) {
            return false
        }
        user.roles = setOf(Role(1L, "ROLE_USER"))
        user.setPassword(bCryptPasswordEncoder!!.encode(user.password))
        userRepository!!.save(user)
        return true
    }

    fun deleteUser(userId: Long): Boolean {
        if (userRepository!!.findById(userId).isPresent) {
            userRepository!!.deleteById(userId)
            return true
        }
        return false
    }

    fun updateRole(userId: Long, roles: List<Long>): Boolean {
        var user = findUserById(userId)
        var user_roles = (roles.map { it -> roleRepository!!.findById(it).orElse(Role()) }).toSet()
        user.roles = user_roles
        userRepository!!.save(user!!)
        return true
    }

    fun usergtList(idMin: Long?): List<User> {
        return em!!.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User::class.java)
            .setParameter("paramId", idMin).resultList
    }

    fun findArticleByUser(user: User): Article {
        val article = articleRepository!!.findByUser(user)
        return article!!
    }

    fun allArticle(): List<Article> {
        return articleRepository!!.findAll()
    }

    fun saveArticle(title: String?, text: String?, username: String?): Boolean {
        val userFromDB = userRepository!!.findByUsername(username)
        var article = Article(0, title, text)
        article.user = userFromDB
        articleRepository!!.save(article)
        return true
    }

    fun checkPlagiat(substrs: List<String>): Int {
        return substrs.map { it -> articleRepository!!.findByTextContaining(it)}.filter { it.count() > 0 }.count()
    }
}
