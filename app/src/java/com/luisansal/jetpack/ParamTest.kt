package com.luisansal.jetpack

import com.luisansal.jetpack.util.Params
import com.luisansal.jetpack.util.UtilParam
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.*
import kotlin.collections.ArrayList

@RunWith(value = Parameterized::class)
class ParamTest(private val nombre: String, private val num1: Int, private val num2: Int) {

    companion object {

        lateinit var params: Params

        @JvmStatic
        @Parameters(name = "{index}: testParam: {0} - {1} - {2}")
        fun data(): ArrayList<Any> {
            var multiArr = ArrayList<Any>()

            params = UtilParam().getFromFile("inputs")

            params.rfc.forEach{
                multiArr.add(arrayOf(it.nombres,1,2))
            }

            return multiArr
        }
    }

    @Before
    fun setup() {

    }

    @Test
    fun testParam() {

        Assert.assertEquals("OFELIA", nombre)

    }


}