package project.seo.pictureviewer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import project.seo.pictureviewer.data.PictureData
import project.seo.pictureviewer.databinding.ListItemBinding

class ListAdapter(private val dataSet: MutableList<PictureData>) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }

    //외부에서 클릭시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    class ListViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(ListItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }

        with(dataSet[position]) {
            //holder.binding.author.text = author
            holder.binding.pictures.load(imageUrl)
        }
    }

    override fun getItemCount(): Int = dataSet.size


}