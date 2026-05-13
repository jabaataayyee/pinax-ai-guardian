package com.pinax.guardian.ui.medic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pinax.guardian.databinding.FragmentMedicBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Medic Fragment - Health and safety information
 * First aid, emergency contacts, health monitoring
 */
@AndroidEntryPoint
class MedicFragment : Fragment() {

    private var _binding: FragmentMedicBinding? = null
    private val binding get() = _binding!!

    private lateinit var medicViewModel: MedicViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        medicViewModel = ViewModelProvider(this).get(MedicViewModel::class.java)
        _binding = FragmentMedicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeViewModel()
        Timber.d("MedicFragment created")
    }

    private fun setupUI() {
        binding.textMedic.text = "🏥 Medic - Health & Safety Assistant"
        binding.healthStatusText.text = "Health Status: Normal"
        binding.emergencyContactsText.text = "Emergency Contacts: 0"

        // Add emergency contact button
        binding.addContactBtn.setOnClickListener {
            medicViewModel.addEmergencyContact()
            Timber.d("Emergency contact added")
        }

        // First aid tips button
        binding.firstAidBtn.setOnClickListener {
            binding.tipsText.text = "\n🚨 CPR - Press hard and fast on chest\n🩹 Bleeding - Apply pressure with clean cloth\n🔥 Burns - Cool with water for 20 mins\n💊 Choking - Heimlich maneuver"
            Timber.d("First aid tips displayed")
        }

        binding.tipsText.text = "Tap First Aid for emergency tips"
    }

    private fun observeViewModel() {
        medicViewModel.emergencyContactCount.observe(viewLifecycleOwner) { count ->
            binding.emergencyContactsText.text = "Emergency Contacts: $count"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
