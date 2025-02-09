package com.rdev.nure.vmptfpz3.task4

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FitnessDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: FitnessTraining)

    @Update
    suspend fun update(item: FitnessTraining)

    @Delete
    suspend fun delete(item: FitnessTraining)

    @Query("SELECT * from trainings WHERE id = :id")
    fun getTraining(id: Int): Flow<FitnessTraining>

    @Query("SELECT * from trainings ORDER BY id DESC")
    fun getAllTrainings(): Flow<List<FitnessTraining>>

    @Query("SELECT AVG(time_per_day) from (SELECT date, SUM(time) as time_per_day from trainings GROUP BY date)")
    fun getAvgTimePerDay(): Flow<Long>

    @Query("SELECT SUM(time) from trainings")
    fun getTotTime(): Flow<Long>
}