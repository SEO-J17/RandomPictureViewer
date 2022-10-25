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
    //커스텀 리스너. 클릭 이벤트를 받고 위함이다.
    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }

    //외부에서 클릭시 일어나는 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    //binding을 사용하기위한 생성자 선언
    //바인딩 루트라는 것이 바인딩 된 객체의 제일 최상위 뷰그룹이 맞나요..?
    class ListViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        //이쪽코드를 작성을 못하겠습니다ㅠㅠ R을 이용하지 않고 inflate를 할 수있을까여..?
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(ListItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        //리스트에서 클릭이 일어나면 클릭이 일어난 view와 인데스 값을 보내기 위함
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }

        with(dataSet[position]) {
            holder.binding.pictures.load(imageUrl)
        }
    }

    override fun getItemCount(): Int = dataSet.size


}