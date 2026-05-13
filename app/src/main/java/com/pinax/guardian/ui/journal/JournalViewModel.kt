package com.pinax.guardian.ui.journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for Journal Fragment
 * Manages incident history and emergency logs
 */
@HiltViewModel
class JournalViewModel @Inject constructor() : ViewModel() {

    private val _incidentCount = MutableLiveData<Int>(0)
    val incidentCount: LiveData<Int> get() = _incidentCount

    private val _lastIncident = MutableLiveData<String?>(null)
    val lastIncident: LiveData<String?> get() = _lastIncident

    fun loadIncidents() {
        // Will integrate with Room database in STEP 8
        Timber.d("Loading incidents from database")
    }

    fun addIncident(description: String) {
        val current = _incidentCount.value ?: 0
        _incidentCount.value = current + 1
        _lastIncident.value = description
        Timber.i("Incident logged: $description")
    }

    fun clearJournal() {
        _incidentCount.value = 0
        _lastIncident.value = null
        Timber.d("Journal cleared")
    }
}
