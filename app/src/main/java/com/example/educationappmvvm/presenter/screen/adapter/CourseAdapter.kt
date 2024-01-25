package com.example.educationappmvvm.presenter.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.educationappmvvm.databinding.ItemCourseBinding
import com.example.educationappmvvm.presenter.data.model.CourseData

class CourseAdapter : ListAdapter<CourseData, CourseAdapter.CourseViewHolder>(CourseDiffUtil) {

    object CourseDiffUtil : DiffUtil.ItemCallback<CourseData>() {
        override fun areItemsTheSame(oldItem: CourseData, newItem: CourseData): Boolean =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: CourseData, newItem: CourseData): Boolean =
            oldItem.name == newItem.name
    }

    inner class CourseViewHolder(private val binding: ItemCourseBinding) :
        ViewHolder(binding.root) {

        fun bind() {
            binding.txtCourseName.text = getItem(absoluteAdapterPosition).name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder =
        CourseViewHolder(
            ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) = holder.bind()

}