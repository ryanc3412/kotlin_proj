package com.ryanchristensen.finalproject2410

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ryanchristensen.finalproject.models.Workout

@Database(entities = [Workout::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getWorkoutsDao(): WorkoutsDoa
}