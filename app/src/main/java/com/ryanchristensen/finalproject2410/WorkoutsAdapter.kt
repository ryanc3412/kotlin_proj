package com.ryanchristensen.finalproject2410

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import com.ryanchristensen.finalproject.models.Workout
import com.ryanchristensen.finalproject2410.databinding.WorkoutItemBinding

class WorkoutsAdapter (val workouts: ObservableArrayList<Workout>, val onWorkoutClicked: (Workout) -> Unit): RecyclerView.Adapter<WorkoutsAdapter.ViewHolder>() {
    class ViewHolder(val binding: WorkoutItemBinding) : RecyclerView.ViewHolder(binding.root)

    init {
        workouts.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableArrayList<Workout>>() {
            override fun onChanged(sender: ObservableArrayList<Workout>?) {
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(
                sender: ObservableArrayList<Workout>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemChanged(positionStart)
            }

            override fun onItemRangeInserted(
                sender: ObservableArrayList<Workout>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemInserted(positionStart)
            }

            override fun onItemRangeMoved(
                sender: ObservableArrayList<Workout>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {

            }

            override fun onItemRangeRemoved(
                sender: ObservableArrayList<Workout>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemRemoved(positionStart)
            }

        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = WorkoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workout = workouts[position]
        holder.binding.checkBox.text = workout.description
        holder.binding.checkBox.isChecked = workout.completed
        holder.binding.checkBox.setOnClickListener {
            onWorkoutClicked(workout)
        }
    }

    override fun getItemCount(): Int {
        return workouts.size
    }
}