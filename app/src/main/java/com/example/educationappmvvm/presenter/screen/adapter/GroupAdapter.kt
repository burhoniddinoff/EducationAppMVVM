package com.example.educationappmvvm.presenter.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.educationappmvvm.databinding.ItemGroupBinding
import com.example.educationappmvvm.presenter.data.model.GroupData

class GroupAdapter : ListAdapter<GroupData, GroupAdapter.GroupViewHolder>(GroupDiffUtil) {
    var onLongClickListener: ((GroupData) -> Unit)? = null
    var onClickListener: ((GroupData) -> Unit)? = null

    object GroupDiffUtil : DiffUtil.ItemCallback<GroupData>() {
        override fun areItemsTheSame(oldItem: GroupData, newItem: GroupData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GroupData, newItem: GroupData): Boolean {
            return oldItem.name == newItem.name
        }
    }

    inner class GroupViewHolder(private val binding: ItemGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickListener?.invoke(getItem(absoluteAdapterPosition))
            }

            binding.root.setOnLongClickListener {
                onLongClickListener?.invoke(getItem(absoluteAdapterPosition))
                return@setOnLongClickListener true
            }
        }

        fun bind() {
            binding.txtGroupName.text = getItem(absoluteAdapterPosition).name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder =
        GroupViewHolder(
            ItemGroupBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bind()
    }

}