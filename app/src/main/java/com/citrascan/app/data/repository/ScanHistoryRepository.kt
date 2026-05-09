// data/repository/ScanHistoryRepository.kt — Persistent scan history store
package com.citrascan.app.data.repository

import android.content.Context
import com.citrascan.app.data.model.ScanHistory
import com.citrascan.app.data.model.Severity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for scan history entries.
 * Persists history to SharedPreferences so data survives app restarts.
 */
@Singleton
class ScanHistoryRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private const val PREFS_NAME = "citrascan_history"
        private const val KEY_HISTORY = "scan_history_json"
    }

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val _history = MutableStateFlow(loadFromPrefs())
    val history: StateFlow<List<ScanHistory>> = _history.asStateFlow()

    fun addEntry(
        diseaseKey: String,
        diseaseName: String,
        confidence: Float,
        severity: Severity
    ) {
        val entry = ScanHistory(
            id = UUID.randomUUID().toString(),
            diseaseKey = diseaseKey,
            diseaseName = diseaseName,
            confidence = confidence,
            severity = severity,
            timestamp = System.currentTimeMillis()
        )
        _history.value = listOf(entry) + _history.value
        saveToPrefs(_history.value)
    }

    fun search(query: String): List<ScanHistory> {
        if (query.isBlank()) return _history.value
        return _history.value.filter {
            it.diseaseName.contains(query, ignoreCase = true)
        }
    }

    fun totalScans(): Int = _history.value.size
    fun healthyScans(): Int = _history.value.count { it.severity == Severity.OK }
    fun diseasesFound(): Int = _history.value
        .filter { it.severity != Severity.OK }
        .map { it.diseaseKey }
        .distinct()
        .size

    /**
     * Serializes the history list to JSON and saves to SharedPreferences.
     */
    private fun saveToPrefs(list: List<ScanHistory>) {
        val jsonArray = JSONArray()
        for (entry in list) {
            val obj = JSONObject().apply {
                put("id", entry.id)
                put("diseaseKey", entry.diseaseKey)
                put("diseaseName", entry.diseaseName)
                put("confidence", entry.confidence.toDouble())
                put("severity", entry.severity.name)
                put("timestamp", entry.timestamp)
            }
            jsonArray.put(obj)
        }
        prefs.edit().putString(KEY_HISTORY, jsonArray.toString()).apply()
    }

    /**
     * Loads the history list from SharedPreferences.
     */
    private fun loadFromPrefs(): List<ScanHistory> {
        val json = prefs.getString(KEY_HISTORY, null) ?: return emptyList()
        return try {
            val jsonArray = JSONArray(json)
            val list = mutableListOf<ScanHistory>()
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                list.add(
                    ScanHistory(
                        id = obj.getString("id"),
                        diseaseKey = obj.getString("diseaseKey"),
                        diseaseName = obj.getString("diseaseName"),
                        confidence = obj.getDouble("confidence").toFloat(),
                        severity = try {
                            Severity.valueOf(obj.getString("severity"))
                        } catch (_: Exception) {
                            Severity.OK
                        },
                        timestamp = obj.getLong("timestamp")
                    )
                )
            }
            list
        } catch (_: Exception) {
            emptyList()
        }
    }
}
