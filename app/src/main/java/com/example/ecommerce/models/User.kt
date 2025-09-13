package com.example.ecommerce.models

class User {
    var name:String = ""
    var email:String = ""
    var id:String = ""

    constructor(name: String, email: String, id: String) {
        this.name = name
        this.email = email
        this.id = id
    }

    constructor()
}