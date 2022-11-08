@file:OptIn(ExperimentalCoroutinesApi::class)

package com.will.moviedbapp.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class CoroutineTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    protected val testDispatcher = UnconfinedTestDispatcher()
    protected val testCoroutineScope = TestScope(testDispatcher)

    @Before
    fun setupViewModelScope() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanupViewModelScope() {
        Dispatchers.resetMain()
    }

    @After
    fun cleanupCoroutines() {
        testDispatcher.cancel()
    }

    fun coTest(block: suspend TestCoroutineScope.() -> Unit) =
        testCoroutineScope.runTest { (block) }
}
