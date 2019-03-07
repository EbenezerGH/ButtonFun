package jfyg.utils

import org.junit.Assert

infix fun Int.isEqualTo(value: Int) = Assert.assertEquals(this, value)