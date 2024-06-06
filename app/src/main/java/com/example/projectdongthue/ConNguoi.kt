package com.example.projectdongthue



class ConNguoi : Object{
    override var id: Int = 0
    var Taisans: ArrayList<Taisan?> = ArrayList()
    override var name: String = ""
    override val category: String = MainActivity.VALUE_PERSON
    override fun getInfo(): String {
        return "$name: $category"
    }
}