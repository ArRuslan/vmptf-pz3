package com.rdev.nure.vmptfpz3.task4

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trainings")
data class FitnessTraining(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Long,
    val time: Int,
)
