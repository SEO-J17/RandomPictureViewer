package project.seo.pictureviewer

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import project.seo.pictureviewer.data.MainPicture
import project.seo.pictureviewer.databinding.ListItemBinding

class ListAdapter(private val dataSet: MutableList<MainPicture>) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    class ListViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(ListItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        with(dataSet[position]) {
            holder.binding.author.text = author
            holder.binding.pictures.load(url)
        }
    }

    override fun getItemCount(): Int = dataSet.size
}