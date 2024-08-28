package com.lesa.testjobfinderapp

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.lesa.navigation.Navigator
import javax.inject.Inject

class AppNavigator @Inject constructor(private val cicerone: Cicerone<Router>) : Navigator {
    override fun navigateToScreen(screen: Screen) {
        cicerone.router.navigateTo(screen)
    }



    override fun back() {
        cicerone.router.exit()
    }

    override fun newRootScreen(screen: Screen) {
        cicerone.router.newRootScreen(screen)
    }
}
