package com.example.navigationdrawerapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun contains_fax() {
        assertEquals(true, "fax".contains("Fax", true))
    }

    @Test
    fun contains_Fax() {
        assertEquals(true, "Fax".contains("Fax", true))
    }

    @Test
    fun contains_FAX() {
        assertEquals(true, "FAX".contains("Fax", true))
    }
    @Test
    fun contains_PhoneFax() {
        assertEquals(true, "Phone is fax?".contains("Fax", true))
    }

    @Test
    fun contains_Phone() {
        assertEquals(false, "Phone".contains("Fax", true))
    }
}