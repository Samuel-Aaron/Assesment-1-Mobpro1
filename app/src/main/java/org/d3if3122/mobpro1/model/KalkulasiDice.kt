package org.d3if3122.mobpro1.model

data class KalkulasiDice(
    val penyelesaian: Boolean,
    val hasil: DiceGamble_WinLoss,
    val randomValues: Int,
    val coin: String,
    val coinInt: Int,
    val luckyDice: String,
    val luckyDiceInt: Int,
)
