package com.app.marvelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.app.marvelapp.common.navigation.Navigator
import com.app.marvelapp.common.navigation.Route
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val navigator: Navigator by inject()
    private val navHostId: Int = R.id.nav_host

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        navigator.directions.onEach { route ->
            when (route) {
                is Route.Forward -> navigateTo(route.direction)
                is Route.Exit -> finish()
                else -> navigateBack()
            }
        }.launchIn(lifecycleScope)

    }

    private fun navigateTo(route: NavDirections) {
        if (isFinishing) return
        Navigation.findNavController(this, navHostId).apply {
            navigate(route)
        }
    }

    private fun navigateBack() {
        Navigation.findNavController(this, navHostId).navigateUp()
    }

}