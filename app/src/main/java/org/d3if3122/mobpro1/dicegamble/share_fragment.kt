package org.d3if3122.mobpro1.dicegamble

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.d3if3122.mobpro1.R
import org.d3if3122.mobpro1.databinding.ShareBinding
import org.d3if3122.mobpro1.model.DiceGamble_WinLoss

class share_fragment : Fragment()  {
    private lateinit var binding: ShareBinding
    private val args: share_fragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = ShareBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI(args.wonloss)

    }

    private fun updateUI(wonloss : DiceGamble_WinLoss) {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        when (wonloss) {
            DiceGamble_WinLoss.MENANG -> {
                actionBar?.title = getString(R.string.menang)
                binding.imageView.setImageResource(R.drawable.ayaya)
                binding.titleWinloss.text = getString(R.string.congratulations)
            }
            DiceGamble_WinLoss.KALAH -> {
                actionBar?.title = getString(R.string.kalah)
                binding.imageView.setImageResource(R.drawable.moneyfly)
                binding.titleWinloss.text = getString(R.string.nt)
            }
        }
    }
}