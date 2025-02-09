package com.rdev.nure.vmptfpz3.task4

import kotlinx.coroutines.flow.Flow

interface FitnessRepository {
    fun getAllTrainingsStream(): Flow<List<FitnessTraining>>

    suspend fun insertTraining(item: FitnessTraining)

    fun getAvgTimePerDay(): Flow<Long>

    fun getTotTime(): Flow<Long>
}