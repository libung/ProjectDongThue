package com.example.projectdongthue

class Taisan : Object {
    override var id: Int = 0
    override var name: String = ""
    var value: Int = 0
    override var category: String = MainActivity.VALUE_ASSET
    override fun getInfo(): String {
        return "$name: $category"
    }
}