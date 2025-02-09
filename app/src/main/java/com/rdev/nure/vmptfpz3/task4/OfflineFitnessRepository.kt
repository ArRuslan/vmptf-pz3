package com.rdev.nure.vmptfpz3.task4

import kotlinx.coroutines.flow.Flow

class OfflineFitnessRepository(private val fitnessDao: FitnessDao) : FitnessRepository {
    override fun getAllTrainingsStream(): Flow<List<FitnessTraining>> = fitnessDao.getAllTrainings()

    override suspend fun insertTraining(item: FitnessTraining) = fitnessDao.insert(item)

    override fun getAvgTimePerDay() = fitnessDao.getAvgTimePerDay()

    override fun getTotTime() = fitnessDao.getTotTime()
}