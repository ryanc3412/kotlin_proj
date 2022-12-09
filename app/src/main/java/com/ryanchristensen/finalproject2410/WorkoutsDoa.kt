package com.ryanchristensen.finalproject2410

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ryanchristensen.finalproject.models.Workout

@Dao
interface WorkoutsDoa {
    @Query("SELECT * FROM workout")
    suspend fun getAllWorkouts(): List<Workout>

    @Insert
    suspend fun createWorkout(workout: Workout): Long

    @Update
    suspend fun updateWorkout(workout: Workout)

    @Delete
    suspend fun deleteWorkout(workout: Workout)
}