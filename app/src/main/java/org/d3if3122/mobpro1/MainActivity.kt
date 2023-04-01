package org.d3if3122.mobpro1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if3122.mobpro1.databinding.ActivityMainBinding
import kotlin.random.Random

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.roll.setOnClickListener { TombolRoll() }
    }
    private fun TombolRoll() {
        val coin = binding.coinInp.text.toString()
        if (TextUtils.isEmpty(coin)) {
            Toast.makeText(this, R.string.coin_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val coinInt = coin.toInt()
        if (coinInt < 1) {
            Toast.makeText(this, R.string.coin_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val luckyDice = binding.diceInp.text.toString()
        if (TextUtils.isEmpty(luckyDice)) {
            Toast.makeText(this, R.string.luckyDice_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val luckyDice2 = luckyDice.toInt()
        if (luckyDice2 < 1) {
            Toast.makeText(this, R.string.diatas0_invalid, Toast.LENGTH_LONG).show()
            return
        }
        if (luckyDice2 > 7) {
            Toast.makeText(this, R.string.dibawah7_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val randomValues =  Random.nextInt(1, 7)
        val penyelesaian = randomValues == luckyDice2
        val hasil = hasilAkhir(penyelesaian)

        binding.diceRolled.text = getString(R.string.putaranDadu_x, randomValues.toString())
        binding.winOrLose.text = getString(R.string.menangKalah_x, hasil)


        if(penyelesaian) {
            val perkalian = (coin.toInt() * 2).toString()
            binding.hasilGamble.text = getString(R.string.menangGamble_x, perkalian)
        } else {
            binding.hasilGamble.text = getString(R.string.kalahGamble_x, coin)
        }
    }
    private fun hasilAkhir(penyelesaian: Boolean): String {
        val menangKalah = if (penyelesaian){
            R.string.menang
            } else {
            R.string.kalah
        }
        return getString(menangKalah)
    }
}