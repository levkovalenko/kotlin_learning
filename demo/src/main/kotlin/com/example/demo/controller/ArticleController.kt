package com.example.demo.controller

import com.example.demo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class ArticleController {
    @Autowired
    private val userService: UserService? = null

    @GetMapping("/create_article")
    fun create_article(model: Model, authentication: Authentication): String {
        model.addAttribute("current_user", authentication.getName())
        model.addAttribute("plagiat_coef", 0)
        return "create_article"
    }

    @PostMapping("/create_article")
    fun addArticle(
        @RequestParam(required = true, defaultValue = "") username: String?,
        @RequestParam(required = true, defaultValue = "") title: String?,
        @RequestParam(required = true, defaultValue = "") text: String?,
        model: Model, authentication: Authentication
    ): String {
        val re = Regex("[^A-Za-z0-9 ]")
        var substrs = (text!!.split(" ").asSequence()
                                    .map { it -> setOf(it) }
                                    .zipWithNext { a, b -> a + b }
                                    .zipWithNext { a, b -> a + b }
                                    .zipWithNext { a, b -> a + b }
                                    .zipWithNext { a, b -> a + b }
                                    .zipWithNext { a, b -> a + b }
                                    .map { it -> it.joinToString(" ") })
        if(substrs.count() == 0) {
            substrs += sequenceOf(text)
        }

        var plagiat = userService!!.checkPlagiat(substrs.toList())
        model.addAttribute("current_user", authentication.getName())
        model.addAttribute("plagiat_coef", plagiat*100/substrs.count())
        userService!!.saveArticle(title, text, username)
        return "create_article"
    }

    @GetMapping("/news")
    fun news(model: Model, authentication: Authentication): String {
        model.addAttribute("current_user", authentication.getName())
        var articles = userService!!.allArticle()
        model.addAttribute("articles", articles)
        return "news"
    }
}
