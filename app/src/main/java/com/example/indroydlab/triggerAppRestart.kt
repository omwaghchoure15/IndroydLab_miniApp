package com.example.indroydlab

import android.content.Context
import android.content.Intent
import kotlin.system.exitProcess

fun triggerAppRestart(context: Context) {
    val packageManager = context.packageManager
    val intent = packageManager.getLaunchIntentForPackage(context.packageName)

    if (intent != null) {
        val componentName = intent.component
        // Creates an intent that starts the launch activity and clears the activity stack
        val restartIntent = Intent.makeRestartActivityTask(componentName)
        context.startActivity(restartIntent)

        // Terminate the current process so the OS spawns a completely fresh one
        exitProcess(0)
    }
}
