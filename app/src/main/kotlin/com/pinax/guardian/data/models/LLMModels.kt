package com.pinax.guardian.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// LLM Model Configuration
data class LLMModel(
    val id: String,
    val name: String,
    val version: String,
    val size: Long,
    val type: ModelType,
    val quantization: String,
    val contextSize: Int = 2048,
    val maxTokens: Int = 512,
    val description: String
)

enum class ModelType {
    GUARDIAN_THREAT_DETECTOR,
    GENERAL_PURPOSE,
    CONVERSATIONAL,
    LIGHTWEIGHT
}

// Chat Message Entity
@Entity(tableName = "chat_messages")
data class ChatMessage(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val role: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis(),
    val threatLevel: Int = 0,
    val modelUsed: String = ""
)

// Threat Detection Result
@Entity(tableName = "threat_detections")
data class ThreatDetection(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val threatType: String,
    val threatLevel: Int,
    val confidence: Float,
    val description: String,
    val timestamp: Long = System.currentTimeMillis(),
    val actionTaken: String = "",
    val resolved: Boolean = false
)

// Inference Request
data class InferenceRequest(
    val prompt: String,
    val modelId: String,
    val maxTokens: Int = 256,
    val temperature: Float = 0.7f,
    val topP: Float = 0.9f,
    val language: String = "en"
)

// Inference Response
data class InferenceResponse(
    val text: String,
    val tokens: Int,
    val modelId: String,
    val inferenceTime: Long,
    val threatLevel: Int = 0,
    val confidence: Float = 0.0f
)

// Guardian Settings
@Entity(tableName = "guardian_settings")
data class GuardianSettings(
    @PrimaryKey
    val id: Int = 1,
    val guardianModeEnabled: Boolean = true,
    val threatAlertLevel: Int = 2,
    val emergencyContactsEnabled: Boolean = true,
    val autoEmergencyCall: Boolean = false,
    val gpsTrackingEnabled: Boolean = false,
    val language: String = "en",
    val modelPreference: String = "guardian-7b",
    val lastUpdated: Long = System.currentTimeMillis()
)
