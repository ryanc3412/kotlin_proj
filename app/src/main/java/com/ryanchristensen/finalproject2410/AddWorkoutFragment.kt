package com.ryanchristensen.finalproject2410

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryanchristensen.finalproject2410.databinding.FragmentAddWorkoutBinding
import com.ryanchristensen.finalproject2410.databinding.FragmentWorkoutsBinding

class AddWorkoutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            //create a binding variable for the AddWorkout fragment and Workouts fragment
        val binding = FragmentAddWorkoutBinding.inflate(inflater, container, false)
        val homeBinding = FragmentWorkoutsBinding.inflate(inflater, container, false)
        val viewModel = WorkoutsViewModel()


        homeBinding.workoutsList.adapter = WorkoutsAdapter(viewModel.workouts, viewModel) {
            viewModel.toggleWorkoutCompletion(it)
        }
        homeBinding.workoutsList.layoutManager = LinearLayoutManager(context)
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            binding.errorOutput.text = errorMessage
        }
        binding.saveButton.setOnClickListener {
            viewModel.createWorkout(binding.workoutInput.text.toString())
            binding.workoutInput.setText("")
        }
        binding.goHomeButton.setOnClickListener {
            findNavController().navigate(R.id.action_addWorkoutFragment_to_workoutsFragment)
        }
        return binding.root

    }
}