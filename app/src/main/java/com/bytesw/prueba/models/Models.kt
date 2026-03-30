package com.bytesw.prueba.models

data class Donut(
    val id: String,
    val type: String,
    val name: String,
    val ppu: Double,
    val batters: BatterContainer?,
    val topping: List<Topping>?
)

data class BatterContainer(
    val batter: List<Batter>?
)

data class Batter(
    val id: String,
    val type: String
)

data class Topping(
    val id: String,
    val type: String
)
