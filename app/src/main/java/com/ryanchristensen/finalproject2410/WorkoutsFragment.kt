package com.ryanchristensen.finalproject2410

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryanchristensen.finalproject2410.databinding.FragmentWorkoutsBinding

class WorkoutsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentWorkoutsBinding.inflate(inflater, container, false)
        val viewModel = WorkoutsViewModel()

        binding.workoutsList.adapter = WorkoutsAdapter(viewModel.workouts, viewModel) {
            viewModel.toggleWorkoutCompletion(it)
        }
        binding.workoutsList.layoutManager = LinearLayoutManager(context)

        binding.addWorkoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_workoutsFragment_to_addWorkoutFragment)
        }
        return binding.root

    }
}