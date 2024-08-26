package com.lesa.data

import com.lesa.data.models.Offer
import com.lesa.data.models.Vacancy
import com.lesa.data.models.toOffer
import com.lesa.data.models.toVacancy
import com.lesa.network.Api
import javax.inject.Inject

interface JobRepository {
    suspend fun getAllVacancies(): List<Vacancy>
    suspend fun getAllOffers(): List<Offer>
}

class JobRepositoryImpl @Inject constructor(
    private val api: Api
) : JobRepository {
    override suspend fun getAllVacancies(): List<Vacancy> {
        return api.getJobResponse().vacancies.map {
            it.toVacancy()
        }
    }

    override suspend fun getAllOffers(): List<Offer> {
        return api.getJobResponse().offers.map {
            it.toOffer()
        }
    }
}
