package com.example.demo.controller

import com.example.demo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AdminController {
    @Autowired
    private val userService: UserService? = null

    @GetMapping("/admin")
    fun userList(model: Model, authentication: Authentication): String {
        model.addAttribute("allUsers", userService!!.allUsers())
        model.addAttribute("roles", userService!!.allRoles())
        model.addAttribute("current_user", authentication.getName())
        return "admin"
    }

    @PostMapping("/admin")
    fun deleteUser(
        @RequestParam(required = true, defaultValue = "") userId: Long?,
        @RequestParam(required = true, defaultValue = "") action: String,
        @RequestParam(required = true, defaultValue = "") roles: List<Long>?,
        model: Model?
    ): String {
        println(action)
        if (action == "delete") {
            userService!!.deleteUser(userId!!)
        }
        if (action == "update_role") {
            userService!!.updateRole(userId!!, roles!!)
        }
        return "redirect:/admin"
    }

    @GetMapping("/admin/gt/{userId}")
    fun gtUser(@PathVariable("userId") userId: Long?, model: Model): String {
        model.addAttribute("allUsers", userService!!.usergtList(userId))
        return "admin"
    }
}
