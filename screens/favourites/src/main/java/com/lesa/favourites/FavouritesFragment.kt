package com.lesa.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.lesa.favourites.databinding.FragmentFavouritesBinding
import com.lesa.navigation.Navigator
import com.lesa.vacancy.VacancyFragment
import com.lesa.vacancy.VacancyUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FavouritesFragment : Fragment(R.layout.fragment_favourites) {
    private val binding: FragmentFavouritesBinding by viewBinding()
    private val viewModel: FavouritesViewModel by viewModels()
    private lateinit var vacancyAdapter: VacancyAdapter

    @Inject
    lateinit var navigator: Navigator

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUpVacancyRecyclerView()
    }

    private fun setUpVacancyRecyclerView() {
        vacancyAdapter = VacancyAdapter(
            onClick = { vacancy ->
                openVacancy(vacancy)
            }
        )
        binding.favouritesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.favouritesRecyclerView.adapter = vacancyAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.vacancies.collect { vacancies ->
                vacancyAdapter.data = vacancies
            }
        }
    }

    private fun openVacancy(vacancy: VacancyUI) {
        navigator.navigateToScreen(
            FragmentScreen {
                VacancyFragment.getNewInstance(vacancy)
            }
        )
    }
}
