package com.example.projectdongthue

import java.io.Serializable

data class ResourceData(
    val person: ArrayList<ConNguoi>,
    val asset: ArrayList<Taisan>,
    val sumasset: ArrayList<Int>
): Serializable
