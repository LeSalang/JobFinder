package com.lesa.testjobfinderapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lesa.favourites.FavouritesFragment
import com.lesa.login.LoginFragment
import com.lesa.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val navigator = AppNavigator(this, R.id.fragment_container)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        router.newRootScreen(
            FragmentScreen {
                LoginFragment()
            }
        )
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        setUpNavigation(bottomNavigationView)
    }

    private fun setUpNavigation(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_search -> {
                    router.navigateTo(
                        FragmentScreen {
                            SearchFragment()
                        }
                    )
                    true
                }
                R.id.menu_favourites -> {
                    router.navigateTo(
                        FragmentScreen {
                            FavouritesFragment()
                        }
                    )
                    true
                }
                R.id.menu_responses -> {
                    false
                }
                R.id.menu_messages -> {
                    false
                }
                R.id.menu_profile -> {
                    false
                }
                else -> {
                    false
                }
            }
        }
        router.newRootScreen(
            FragmentScreen {
                LoginFragment()
            }
        )
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
