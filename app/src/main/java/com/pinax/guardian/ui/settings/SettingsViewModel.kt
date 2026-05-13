package com.pinax.guardian.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for Settings Fragment
 * Manages app configuration and preferences
 */
@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {

    private val _currentLanguage = MutableLiveData<String>("en")
    val currentLanguage: LiveData<String> get() = _currentLanguage

    private val _backgroundServiceEnabled = MutableLiveData<Boolean>(true)
    val backgroundServiceEnabled: LiveData<Boolean> get() = _backgroundServiceEnabled

    fun setLanguage(languageCode: String) {
        _currentLanguage.value = languageCode
        Timber.d("Language set to: $languageCode")
    }

    fun setBackgroundServiceEnabled(enabled: Boolean) {
        _backgroundServiceEnabled.value = enabled
        Timber.d("Background service enabled: $enabled")
    }
}
