package com.example.demo.entity

import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "t_user")
class User : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    private var username: @Size(min = 2, message = "Не меньше 5 знаков") String? = null
    private var password: @Size(min = 2, message = "Не меньше 5 знаков") String? = null

    @Transient
    var passwordConfirm: String? = null

    @ManyToMany(fetch = FetchType.EAGER)
    var roles: Set<Role?>? = null

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    var articles: Set<Article?>? = null

    override fun getUsername(): @Size(message = "Не меньше 5 знаков", min = 2) String? {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun hasRole(role: Role): Boolean {
        return roles!!.contains(role)
    }

    fun checkUserName(new_username: String): Boolean {
        return this.username == new_username
    }

    override fun getAuthorities(): Set<Role?>? {
        return roles
    }

    override fun getPassword(): @Size(message = "Не меньше 5 знаков", min = 2) String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }
}
