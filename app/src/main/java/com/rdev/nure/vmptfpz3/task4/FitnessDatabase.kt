package com.rdev.nure.vmptfpz3.task4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FitnessTraining::class], version = 1, exportSchema = false)
abstract class FitnessDatabase : RoomDatabase() {

    abstract fun itemDao(): FitnessDao

    companion object {
        @Volatile
        private var Instance: FitnessDatabase? = null

        fun getDatabase(context: Context): FitnessDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FitnessDatabase::class.java, "fitness_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}