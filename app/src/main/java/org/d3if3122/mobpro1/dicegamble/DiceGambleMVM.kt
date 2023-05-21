package org.d3if3122.mobpro1.dicegamble

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if3122.mobpro1.model.DiceGamble_WinLoss
import org.d3if3122.mobpro1.model.KalkulasiDice

class DiceGambleMVM : ViewModel() {
    private val KD = MutableLiveData<KalkulasiDice?>()
    private val nav = MutableLiveData<DiceGamble_WinLoss?>()
    private fun hasilAkhir(penyelesaian: Boolean): DiceGamble_WinLoss {
        val menangKalah = if (penyelesaian){
            DiceGamble_WinLoss.MENANG
        } else {
            DiceGamble_WinLoss.KALAH
        }
        return menangKalah
    }
    fun kalkulasiPenyamaan(coin: String, coinInt: Int, luckyDice: String, luckyDiceInt: Int){
        val randomValues = (1..6).random()
        val penyelesaian = randomValues == luckyDiceInt
        val hasil = hasilAkhir(penyelesaian)

        KD.value = KalkulasiDice(penyelesaian, hasil, randomValues, coin, coinInt, luckyDice, luckyDiceInt)
    }
    fun getKalkulasiPenyamaan(): LiveData<KalkulasiDice?> = KD

    fun mulaiNav() {
        nav.value = KD.value?.hasil
    }
    fun selesaiNav() {
        nav.value = null
    }
    fun getNav() : LiveData<DiceGamble_WinLoss?> = nav

}