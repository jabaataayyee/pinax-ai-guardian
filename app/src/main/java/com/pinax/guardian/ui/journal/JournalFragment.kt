package com.pinax.guardian.ui.journal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pinax.guardian.databinding.FragmentJournalBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Journal Fragment - Emergency log and incident history
 * Stores local evidence and event logs
 */
@AndroidEntryPoint
class JournalFragment : Fragment() {

    private var _binding: FragmentJournalBinding? = null
    private val binding get() = _binding!!

    private lateinit var journalViewModel: JournalViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        journalViewModel = ViewModelProvider(this).get(JournalViewModel::class.java)
        _binding = FragmentJournalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeViewModel()
        Timber.d("JournalFragment created")
    }

    private fun setupUI() {
        binding.textJournal.text = "📔 Emergency Journal & Evidence Log"
        binding.incidentsCountText.text = "Total Incidents: 0"
        binding.lastIncidentText.text = "Last Incident: None"

        // Load incidents button
        binding.loadIncidentsBtn.setOnClickListener {
            journalViewModel.loadIncidents()
            binding.incidentsListText.text = "Loading incidents..."
            Timber.d("Loading incidents")
        }

        // Clear journal button
        binding.clearJournalBtn.setOnClickListener {
            journalViewModel.clearJournal()
            binding.incidentsCountText.text = "Total Incidents: 0"
            binding.incidentsListText.text = "Journal cleared"
            Timber.d("Journal cleared")
        }

        binding.incidentsListText.text = "No incidents logged yet"
    }

    private fun observeViewModel() {
        journalViewModel.incidentCount.observe(viewLifecycleOwner) { count ->
            binding.incidentsCountText.text = "Total Incidents: $count"
        }

        journalViewModel.lastIncident.observe(viewLifecycleOwner) { incident ->
            binding.lastIncidentText.text = "Last Incident: ${incident ?: "None"}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
