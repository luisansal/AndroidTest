package com.luisansal.jetpack.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE


object WriteConfigurationUtil {
    fun writeConfiguration(ctx: Context) {
        try {
            ctx.openFileOutput("config.txt", Context.MODE_PRIVATE).use({ openFileOutput ->

                openFileOutput.write("This is a test1.".toByteArray())
                openFileOutput.write("This is a test2.".toByteArray())
            })
        } catch (e: Exception) {
            // not handled
        }

    }
}