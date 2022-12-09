package com.ryanchristensen.finalproject2410

import WorkoutsRepository
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryanchristensen.finalproject.models.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var idCounter = 0

class WorkoutsViewModel: ViewModel() {
    val workouts = ObservableArrayList<Workout>()
    val errorMessage = MutableLiveData("")

    init {
        loadWorkouts()
    }

    private fun loadWorkouts() {
        viewModelScope.launch {
            val loadedWorkouts = WorkoutsRepository.getAllWorkouts()
            workouts.addAll(loadedWorkouts)
        }
    }

    fun toggleWorkoutCompletion(workout: Workout) {
        viewModelScope.launch {
            workout.completed = !workout.completed
            WorkoutsRepository.update(workout)
            workouts[workouts.indexOf(workout)] = workout.copy()
        }
    }

    fun createWorkout(workoutInput: String) {
        errorMessage.value = ""

        if (workoutInput.isEmpty()) {
            errorMessage.value = "Todo input cannot be blank."
            viewModelScope.launch {
                delay(3000)
                errorMessage.value = ""
            }
            return
        }
        if(workoutInput.trim().isEmpty()) {
            errorMessage.value = "Todo input must contain at least one alpha numerica character"
            return
        }

            //this creates workout
        viewModelScope.launch {
            val workout = Workout(id = 0, description = workoutInput, completed = false)
            workout.id = WorkoutsRepository.createWorkout(workout)
            workouts.add(workout)
        }
    }
}