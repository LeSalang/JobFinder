package com.lesa.navigation

import com.github.terrakok.cicerone.Screen

interface Navigator {
    fun navigateToScreen(screen: Screen)
    fun back()
    fun newRootScreen(screen: Screen)
}
