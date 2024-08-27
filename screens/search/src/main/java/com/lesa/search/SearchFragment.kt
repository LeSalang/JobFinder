package com.lesa.search

import android.os.Bundle
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.lesa.search.adapter.OfferAdapter
import com.lesa.search.adapter.VacancyAdapter
import com.lesa.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private val viewModel: SearchViewModel by viewModels()
    private val binding: FragmentSearchBinding by viewBinding()

    private lateinit var vacancyAdapter: VacancyAdapter
    private lateinit var offerAdapter: OfferAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUpScreen()
    }

    private fun setUpScreen() {
        setUpVacancyRecyclerView()
        setUpOfferRecyclerView()
        setUpMoreButton()
        setUpFilterTitle()
    }

    private fun setUpFilterTitle() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.vacanciesFull.collect { vacancies ->
                binding.filterTitleText.text = resources.getQuantityString(
                    R.plurals.vacancies_left, vacancies.size, vacancies.size
                )
            }
        }
    }

    private fun setUpMoreButton() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.vacanciesFull.collect { vacancies ->
                binding.buttonMore.text = getString(
                    R.string.more
                ) + " " + resources.getQuantityString(
                    R.plurals.vacancies_left,
                    vacancies.size,
                    vacancies.size
                )
            }
        }

        binding.buttonMore.setOnClickListener {
            viewModel.setFullScreenMode()
            binding.buttonMore.visibility = View.GONE
            setUpOfferRecyclerView()
            setUpVacancyRecyclerView()
            binding.filterTitle.visibility = View.VISIBLE
        }
    }

    private fun setUpVacancyRecyclerView() {
        vacancyAdapter = VacancyAdapter()
        binding.vacanciesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.vacanciesRecyclerView.adapter = vacancyAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            when (viewModel.screenMode.value) {
                ScreenMode.FULL -> {
                    viewModel.vacanciesFull.collect { vacancies ->
                        vacancyAdapter.data = vacancies
                    }
                }
                ScreenMode.MIN -> {
                    viewModel.vacanciesMin.collect { vacancies ->
                        vacancyAdapter.data = vacancies
                    }
                }
            }
        }
    }

    private fun setUpOfferRecyclerView() {
        if (viewModel.screenMode.value == ScreenMode.FULL) {
            binding.offersRecyclerView.visibility = View.GONE
        } else {
            offerAdapter = OfferAdapter()
            binding.offersRecyclerView.layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
            binding.offersRecyclerView.adapter = offerAdapter

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.offers.collect { offers ->
                    offerAdapter.data = offers
                }
            }
        }
    }
}
