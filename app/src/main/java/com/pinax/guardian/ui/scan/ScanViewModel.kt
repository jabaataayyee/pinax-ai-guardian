package com.pinax.guardian.ui.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for Scan Fragment
 * Manages camera AI threat detection
 */
@HiltViewModel
class ScanViewModel @Inject constructor() : ViewModel() {

    private val _threatsDetected = MutableLiveData<Int>(0)
    val threatsDetected: LiveData<Int> get() = _threatsDetected

    private val _isScanActive = MutableLiveData<Boolean>(false)
    val isScanActive: LiveData<Boolean> get() = _isScanActive

    fun startCameraScan() {
        _isScanActive.value = true
        Timber.d("Camera scan started from ViewModel")
    }

    fun stopCameraScan() {
        _isScanActive.value = false
        Timber.d("Camera scan stopped from ViewModel")
    }

    fun addDetectedThreat() {
        val current = _threatsDetected.value ?: 0
        _threatsDetected.value = current + 1
        Timber.w("Threat detected! Total: ${_threatsDetected.value}")
    }

    fun resetThreats() {
        _threatsDetected.value = 0
        Timber.d("Threats reset")
    }
}
