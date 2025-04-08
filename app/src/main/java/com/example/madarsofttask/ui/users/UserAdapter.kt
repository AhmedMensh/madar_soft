package com.example.madarsofttask.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.madarsofttask.R
import com.example.madarsofttask.databinding.ListItemUserBinding
import com.example.madarsofttask.domain.models.UserModel


class UserAdapter : ListAdapter<UserModel, UserAdapter.UserViewHolder>(UserDiffCallback()) {

    class UserDiffCallback : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }
    }

    inner class UserViewHolder(private val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserModel) {
            val resources = binding.root.context.resources
            binding.tvName.text = resources.getString(R.string.name).plus(": ${user.name}")
            binding.tvAge.text = resources.getString(R.string.age).plus(": ${user.age}")
            binding.tvJobTitle.text =
                resources.getString(R.string.job_title).plus(": ${user.jobTitle}")
            binding.tvGender.text = resources.getString(R.string.gender).plus(": ${user.gender}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}