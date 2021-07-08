package com.example.demo.entity

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
@Table(name = "t_role")
class Role : GrantedAuthority {
    @Id
    var id: Long? = null
    var name: String? = null

    @Transient
    @ManyToMany(mappedBy = "roles")
    var users: Set<User>? = null

    constructor() {}
    constructor(id: Long?) {
        this.id = id
    }

    constructor(id: Long?, name: String?) {
        this.id = id
        this.name = name
    }

    override fun getAuthority(): String {
        return name!!
    }
}
