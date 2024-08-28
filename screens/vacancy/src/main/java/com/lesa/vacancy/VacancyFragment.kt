package com.lesa.vacancy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.lesa.vacancy.databinding.FragmentVacancyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VacancyFragment : Fragment(R.layout.fragment_vacancy) {
    private val viewModel: VacancyViewModel by viewModels()
    private val binding: FragmentVacancyBinding by viewBinding()
    private lateinit var questionAdapter: QuestionAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<VacancyUI>(VACANCY_KEY)?.let {
            viewModel.setVacancy(it)
        }
        setUpScreen()
    }

    private fun setUpScreen() {
        setUpBackButton()
        setUpFavoriteButton()
        setUpVacancyTitle()
        setUpVacancySalary()
        setUpVacancyExperience()
        setUpVacancySchedules()
        setUpVacancyRespondedPeople()
        setUpVacancyWatchingPeople()
        setUpVacancyCompany()
        setUpVacancyLocation()
        setUpVacancyDescription()
        setUpVacancyResponsibilities()
        setUpVacancyQuestions()
        setUpVacancyApplyButton()
    }

    private fun setUpBackButton() {
        binding.vacancyIconBack.setOnClickListener {
            // TODO: Navigate to SearchFragment
        }
    }

    private fun setUpFavoriteButton() {
        val button = binding.vacancyIconFavorite
        if (viewModel.vacancy.value?.isFavorite == true) {
            button.setImageResource(com.lesa.ui_kit.R.drawable.ic_favourites_active)
        } else {
            button.setImageResource(com.lesa.ui_kit.R.drawable.ic_favourites)
        }
        button.setOnClickListener {
            viewModel.onFavoriteButtonClick()
            // TODO: Add to favorites
        }
    }

    private fun setUpVacancyTitle() {
        val title = binding.vacancyTitle
        title.text = viewModel.vacancy.value?.title
    }

    private fun setUpVacancySalary() {
        val salary = binding.vacancySalary
        salary.text = viewModel.vacancy.value?.fullSalary
    }

    private fun setUpVacancyExperience() {
        val experience = binding.vacancyExperience
        experience.text = resources.getString(
            R.string.required_experience, viewModel.vacancy.value?.experienceText
        )
    }

    private fun setUpVacancySchedules() {
        val schedules = binding.vacancySchedules
        val text = viewModel.vacancy.value?.schedules
            ?.joinToString(", ")
            ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        schedules.text = text
    }

    private fun setUpVacancyRespondedPeople() {
        val block = binding.vacancyRespondedPeopleBlock
        val text = binding.vacancyRespondingPeopleText
        val number = viewModel.vacancy.value?.appliedNumber
        if (number == null) {
            block.visibility = View.GONE
        } else {
            block.visibility = View.VISIBLE
            text.text = resources.getQuantityString(
                R.plurals.responded_people, number, number
            )
        }
    }

    private fun setUpVacancyWatchingPeople() {
        val block = binding.vacancyWatchingPeopleBlock
        val text = binding.vacancyWatchingPeopleText
        val number = viewModel.vacancy.value?.lookingNumber
        if (number == null) {
            block.visibility = View.GONE
        } else {
            block.visibility = View.VISIBLE
            text.text = resources.getQuantityString(
                R.plurals.watching_people, number, number
            )
        }
    }

    private fun setUpVacancyCompany() {
        val company = binding.vacancyCompany
        company.text = viewModel.vacancy.value?.company
    }

    private fun setUpVacancyLocation() {
        val location = binding.vacancyLocation
        val town = viewModel.vacancy.value?.town
        val street = viewModel.vacancy.value?.street
        val house = viewModel.vacancy.value?.house
        location.text = "$town, $street, $house"
    }

    private fun setUpVacancyDescription() {
        val description = binding.vacancyDescription
        val text = viewModel.vacancy.value?.description
        if (text == null) {
            description.visibility = View.GONE
        } else {
            description.visibility = View.VISIBLE
            description.text = text
        }
    }

    private fun setUpVacancyResponsibilities() {
        val responsibilities = binding.vacancyResponsibilities
        val text = viewModel.vacancy.value?.responsibilities
        if (text == null) {
            responsibilities.visibility = View.GONE
        } else {
            responsibilities.visibility = View.VISIBLE
            responsibilities.text = text
        }
    }

    private fun setUpVacancyQuestions() {
        val questions = binding.vacanciesRecyclerView
        questionAdapter = QuestionAdapter()
        questions.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )
        questions.adapter = questionAdapter

        viewModel.vacancy.observe(viewLifecycleOwner) { vacancy ->
            questionAdapter.data = vacancy?.questions ?: emptyList()
        }
    }

    private fun setUpVacancyApplyButton() {
        val button = binding.vacancyApplyButton
        button.setOnClickListener {
            // TODO: Navigate to ApplicationFragment
        }
    }

    companion object {
        private const val VACANCY_KEY = "vacancy_key"

        fun getNewInstance(vacancy: VacancyUI): VacancyFragment {
            return VacancyFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(VACANCY_KEY, vacancy)
                }
            }
        }
    }
}
