package com.roshaan.githubapp.data.util

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Clock @Inject constructor() {

    fun getCurrentTime() = System.currentTimeMillis()

    fun hasTimePassed(howManyInMinutes: Int, fromTime: Long): Boolean {

//       checking if diff is greater thn 0 it may happen that user will change
//       the time of device resulting in negative diff
        val minutesPassed = getDifferenceInMinutes(fromTime, getCurrentTime())

        if (minutesPassed > 0) {
            if (minutesPassed >= howManyInMinutes)
                return true
            else
                return false
        } else
            return false
    }

    private fun getDifferenceInMinutes(from: Long, to: Long): Long {
        val timeDifference = to - from

        return timeDifference / (60 * 1000)
    }

}