package com.pinax.guardian.ui.medic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for Medic Fragment
 * Manages health, emergency contacts, and first aid
 */
@HiltViewModel
class MedicViewModel @Inject constructor() : ViewModel() {

    private val _emergencyContactCount = MutableLiveData<Int>(0)
    val emergencyContactCount: LiveData<Int> get() = _emergencyContactCount

    fun addEmergencyContact() {
        val current = _emergencyContactCount.value ?: 0
        _emergencyContactCount.value = current + 1
        Timber.d("Emergency contact added. Total: ${_emergencyContactCount.value}")
    }

    fun removeEmergencyContact() {
        val current = _emergencyContactCount.value ?: 0
        if (current > 0) {
            _emergencyContactCount.value = current - 1
            Timber.d("Emergency contact removed. Total: ${_emergencyContactCount.value}")
        }
    }
}
