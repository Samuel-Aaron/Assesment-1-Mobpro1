package org.d3if3122.mobpro1.dicegamble

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController

import org.d3if3122.mobpro1.R
import org.d3if3122.mobpro1.databinding.FragmenDgMainBinding
import org.d3if3122.mobpro1.model.DiceGamble_WinLoss
import org.d3if3122.mobpro1.model.KalkulasiDice

class fragment_DG : Fragment() {
    private lateinit var binding: FragmenDgMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmenDgMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.roll.setOnClickListener { cekNull() }
        binding.shareButton.setOnClickListener { viewModel.mulaiNav() }
        viewModel.getKalkulasiPenyamaan().observe(requireActivity(), { showResult(it) })
        viewModel.getNav().observe(viewLifecycleOwner, {
            if (it == null) return@observe
            findNavController().navigate(fragment_DGDirections.actionFragmentDGToShareFragment(it))
            viewModel.selesaiNav()
        })
    }
    private val viewModel: DiceGambleMVM by lazy {
        ViewModelProvider(requireActivity())[DiceGambleMVM::class.java]

    }

    private fun cekNull() {
        val luckyDice = binding.diceInp.text.toString()
        val luckyDiceInt = luckyDice.toInt()
        val coin = binding.coinInp.text.toString()
        val coinInt = coin.toInt()
        if (TextUtils.isEmpty(coin)) {
            Toast.makeText(context, R.string.coin_invalid, Toast.LENGTH_LONG).show()
            return
        }
        if (coinInt < 1) {
            Toast.makeText(context, R.string.coin_invalid, Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(luckyDice)) {
            Toast.makeText(context, R.string.luckyDice_invalid, Toast.LENGTH_LONG).show()
            return
        }

        if (luckyDiceInt < 1) {
            Toast.makeText(context, R.string.diatas0_invalid, Toast.LENGTH_LONG).show()
            return
        }
        if (luckyDiceInt > 7) {
            Toast.makeText(context, R.string.dibawah7_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val result = viewModel.kalkulasiPenyamaan(coin, coinInt, luckyDice, luckyDiceInt)
        return
    }

    private fun getHasilAkhir(hasil: DiceGamble_WinLoss): String{
        val stringRes = when(hasil){
            DiceGamble_WinLoss.MENANG -> R.string.menang
            DiceGamble_WinLoss.KALAH -> R.string.kalah
        }
        return getString(stringRes)
    }
    private fun showResult(result: KalkulasiDice?) {
        if (result == null) return

        binding.diceRolled.text = getString(R.string.putaranDadu_x, result.randomValues.toString())
        binding.winOrLose.text = getString(R.string.menangKalah_x, getHasilAkhir(result.hasil))

        if(result.penyelesaian) {
            val perkalian = (result.coinInt * 2).toString()
            binding.hasilGamble.text = getString(R.string.menangGamble_x, perkalian)
        } else {
            binding.hasilGamble.text = getString(R.string.kalahGamble_x, result.coin)
        }
        binding.shareButton.visibility = View.VISIBLE
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_fragment_DG_to_about_fragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}