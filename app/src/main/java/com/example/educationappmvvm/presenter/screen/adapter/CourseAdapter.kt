package com.example.educationappmvvm.presenter.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.educationappmvvm.databinding.ItemCourseBinding
import com.example.educationappmvvm.presenter.data.model.CourseData

class CourseAdapter : ListAdapter<CourseData, CourseAdapter.CourseViewHolder>(CourseDiffUtil) {

    private var onLongClickListener: ((CourseData) -> Unit)? = null
    private var onClickListener: ((Long) -> Unit)? = null

    object CourseDiffUtil : DiffUtil.ItemCallback<CourseData>() {
        override fun areItemsTheSame(oldItem: CourseData, newItem: CourseData): Boolean =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: CourseData, newItem: CourseData): Boolean =
            oldItem.name == newItem.name
    }

    inner class CourseViewHolder(private val binding: ItemCourseBinding) :
        ViewHolder(binding.root) {

        init {
            binding.root.setOnLongClickListener {
                onLongClickListener?.invoke(
                    getItem(absoluteAdapterPosition)
                )
                return@setOnLongClickListener true
            }

            binding.root.setOnClickListener {
                onClickListener?.invoke(
                    getItem(absoluteAdapterPosition).id
                )
            }
        }

        fun bind() {
            binding.txtCourseName.text = getItem(absoluteAdapterPosition).name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder =
        CourseViewHolder(
            ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) = holder.bind()

    fun setOnLongClickListener(block: (CourseData) -> Unit) {
        this.onLongClickListener = block
    }

    fun setOnClickListener(block: (Long) -> Unit) {
        this.onClickListener = block
    }

}