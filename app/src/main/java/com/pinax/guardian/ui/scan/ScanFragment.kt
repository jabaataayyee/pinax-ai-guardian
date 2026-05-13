package com.pinax.guardian.ui.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pinax.guardian.databinding.FragmentScanBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Scan Fragment - Camera AI for threat detection
 * Detects falls, fires, suspicious movement, weapons, accidents
 */
@AndroidEntryPoint
class ScanFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!

    private lateinit var scanViewModel: ScanViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        scanViewModel = ViewModelProvider(this).get(ScanViewModel::class.java)
        _binding = FragmentScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeViewModel()
        Timber.d("ScanFragment created")
    }

    private fun setupUI() {
        binding.textScan.text = "📷 Camera AI Threat Scanner"
        binding.detectionStatusText.text = "Status: Ready"
        binding.detectedThreatsText.text = "Threats Detected: 0"

        // Start camera scan button
        binding.startCameraBtn.setOnClickListener {
            scanViewModel.startCameraScan()
            binding.detectionStatusText.text = "Status: Scanning..."
            Timber.d("Camera scan started")
        }

        // Stop camera scan button
        binding.stopCameraBtn.setOnClickListener {
            scanViewModel.stopCameraScan()
            binding.detectionStatusText.text = "Status: Stopped"
            Timber.d("Camera scan stopped")
        }

        // Detection types
        binding.detectionTypesText.text = "\n🚨 Fall Detection\n🔥 Fire Detection\n👁️ Movement Tracking\n🔫 Weapon Detection\n💥 Accident Detection"
    }

    private fun observeViewModel() {
        scanViewModel.threatsDetected.observe(viewLifecycleOwner) { count ->
            binding.detectedThreatsText.text = "Threats Detected: $count"
        }

        scanViewModel.isScanActive.observe(viewLifecycleOwner) { isActive ->
            binding.startCameraBtn.isEnabled = !isActive
            binding.stopCameraBtn.isEnabled = isActive
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scanViewModel.stopCameraScan()
        _binding = null
    }
}
