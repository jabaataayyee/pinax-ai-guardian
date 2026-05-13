package com.pinax.guardian.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for Home Fragment
 * Manages threat level and guardian status
 */
@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _threatLevel = MutableLiveData<Int>(0)
    val threatLevel: LiveData<Int> get() = _threatLevel

    private val _isGuardianActive = MutableLiveData<Boolean>(false)
    val isGuardianActive: LiveData<Boolean> get() = _isGuardianActive

    fun startGuardian() {
        _isGuardianActive.value = true
        Timber.d("Guardian started from ViewModel")
    }

    fun stopGuardian() {
        _isGuardianActive.value = false
        Timber.d("Guardian stopped from ViewModel")
    }

    fun updateThreatLevel(level: Int) {
        if (level in 0..4) {
            _threatLevel.value = level
            Timber.d("Threat level updated to: $level")
        }
    }

    fun resetThreatLevel() {
        _threatLevel.value = 0
        Timber.d("Threat level reset")
    }
}
