package com.example.projectdongthue

import java.io.Serializable

interface Object {
    var id: Int
    var name: String
    val category: String

    fun getInfo(): String

}