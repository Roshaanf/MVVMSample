package com.roshaan.githubapp.data.util

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ClockTest {
    private lateinit var clock: Clock

    private val fakeTime = 1610206272871
    private val fakeFiveMinutesInFutureTime = 1610206572871

    @Before
    fun setup() {
        clock = spy(Clock())
    }

    @Test
    fun `hasTimePassed, returns correct result when time has passed`() {
//        stubbing current time
        doReturn(fakeFiveMinutesInFutureTime).whenever(clock).getCurrentTime()

        assertEquals(true, clock.hasTimePassed(4, fakeTime))
    }

    @Test
    fun `hasTimePassed, returns correct result when time has not passed`() {
//        stubbing current time
        doReturn(fakeFiveMinutesInFutureTime).whenever(clock).getCurrentTime()

        assertEquals(false, clock.hasTimePassed(6, fakeTime))
    }
}