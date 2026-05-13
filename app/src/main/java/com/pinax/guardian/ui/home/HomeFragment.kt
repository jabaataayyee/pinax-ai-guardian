package com.pinax.guardian.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pinax.guardian.R
import com.pinax.guardian.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Home Fragment - Displays Guardian AI Face and Threat Index
 * Main dashboard for Pinax AI Guardian
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeViewModel()
        Timber.d("HomeFragment created")
    }

    private fun setupUI() {
        binding.textHome.text = "🛡️ Pinax Guardian Active"
        binding.threatLevelText.text = "Threat Level: SAFE"
        binding.threatLevelText.setTextColor(resources.getColor(R.color.neon_green, null))

        // Setup threat index visualization
        updateThreatIndex(0)

        // Start Guardian Service button
        binding.startGuardianBtn.setOnClickListener {
            homeViewModel.startGuardian()
            Timber.d("Guardian started")
        }

        // Status text
        binding.statusText.text = "Status: Online & Monitoring"
    }

    private fun observeViewModel() {
        homeViewModel.threatLevel.observe(viewLifecycleOwner) { level ->
            updateThreatIndex(level)
        }

        homeViewModel.isGuardianActive.observe(viewLifecycleOwner) { isActive ->
            binding.statusText.text = if (isActive) {
                "Status: Online & Monitoring"
            } else {
                "Status: Standby Mode"
            }
        }
    }

    private fun updateThreatIndex(level: Int) {
        val (levelText, color) = when (level) {
            0 -> "SAFE" to R.color.neon_green
            1 -> "ATTENTION" to R.color.neon_yellow
            2 -> "WARNING" to R.color.neon_orange
            3 -> "DANGER" to R.color.neon_red
            4 -> "CRITICAL" to R.color.neon_red
            else -> "UNKNOWN" to R.color.dark_blue
        }

        binding.threatLevelText.text = "Threat Level: $levelText"
        binding.threatLevelText.setTextColor(resources.getColor(color, null))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
