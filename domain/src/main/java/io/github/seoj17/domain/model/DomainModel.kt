package io.github.seoj17.domain.model

import androidx.recyclerview.widget.DiffUtil

data class DomainModel(
    val id: Int,
    val author: String,
    val width: String,
    val height: String,
    val url: String,
    val downloadUrl: String,
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<DomainModel>() {
            override fun areItemsTheSame(
                oldItem: DomainModel,
                newItem: DomainModel,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DomainModel,
                newItem: DomainModel,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}



