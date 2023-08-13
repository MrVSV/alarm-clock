package com.vsv.ruleyourtime

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class KoinTestRule(
    private val modules: List<Module>
): TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext.applicationContext)
            modules(modules)
        }
    }

    override fun finished(description: Description) {
        super.finished(description)
        stopKoin()
    }
}