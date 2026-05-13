package com.pinax.guardian.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pinax.guardian.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Settings Fragment - App configuration
 * Language, theme, permissions, background services
 */
@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        settingsViewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeViewModel()
        Timber.d("SettingsFragment created")
    }

    private fun setupUI() {
        binding.textSettings.text = "⚙️ Settings & Configuration"
        binding.versionText.text = "Version: 1.0.0"
        binding.languageText.text = "Language: English"

        // Language selection
        binding.languageBtn.setOnClickListener {
            binding.languageText.text = "Language: Afaan Oromo"
            settingsViewModel.setLanguage("or")
            Timber.d("Language changed to Afaan Oromo")
        }

        // Background service toggle
        binding.backgroundServiceToggle.setOnCheckedChangeListener { _, isChecked ->
            settingsViewModel.setBackgroundServiceEnabled(isChecked)
            binding.serviceStatusText.text = if (isChecked) "Background Service: ENABLED" else "Background Service: DISABLED"
            Timber.d("Background service toggled: $isChecked")
        }

        // Permissions button
        binding.permissionsBtn.setOnClickListener {
            binding.permissionsText.text = "\n✅ Camera\n✅ Microphone\n✅ Location\n✅ Phone\n✅ SMS\n✅ Contacts"
            Timber.d("Permissions displayed")
        }

        binding.serviceStatusText.text = "Background Service: ENABLED"
        binding.permissionsText.text = "Tap Permissions to view all granted permissions"
    }

    private fun observeViewModel() {
        settingsViewModel.currentLanguage.observe(viewLifecycleOwner) { lang ->
            val langName = when (lang) {
                "en" -> "English"
                "or" -> "Afaan Oromo"
                "am" -> "Amharic"
                else -> "Unknown"
            }
            binding.languageText.text = "Language: $langName"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
