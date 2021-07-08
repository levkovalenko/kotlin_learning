package com.example.demo.entity

import javax.persistence.*

@Entity
@Table(name = "t_aticle")
class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var title: String? = null
    @Column(columnDefinition = "text")
    var text: String? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    var user: User? = null

    constructor() {}
    constructor(id: Long?) {
        this.id = id
    }

    constructor(id: Long?, title: String?) {
        this.id = id
        this.title = title
    }

    constructor(id: Long?, title: String?, text: String?) {
        this.id = id
        this.title = title
        this.text = text
    }
}
