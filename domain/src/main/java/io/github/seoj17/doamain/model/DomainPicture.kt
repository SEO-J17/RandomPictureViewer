package io.github.seoj17.doamain.model

import androidx.recyclerview.widget.DiffUtil

data class DomainPicture(
    val id: Int,
    val author: String,
    val width: String,
    val height: String,
    val url: String,
    val downloadUrl: String,
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<DomainPicture>() {
            override fun areItemsTheSame(
                oldItem: DomainPicture,
                newItem: DomainPicture,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DomainPicture,
                newItem: DomainPicture,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}