// util/GreetingHelper.kt — Time-based greeting generator
package com.citrascan.app.util

import java.util.Calendar

/**
 * Generates time-of-day-aware greetings, matching the HTML prototype's
 * universal greeting logic.
 */
object GreetingHelper {

    /**
     * Returns an appropriate greeting based on the current time.
     *
     * @return "Good morning" (before 12), "Good afternoon" (12–17), or "Good evening" (after 17).
     */
    fun getGreeting(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when {
            hour < 12 -> "Good morning"
            hour < 17 -> "Good afternoon"
            else -> "Good evening"
        }
    }
}
