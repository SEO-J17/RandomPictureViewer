package project.seo.pictureviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import project.seo.pictureviewer.databinding.FragmentPictureBinding
import project.seo.pictureviewer.network.RetrofitService

class PictureFragment : Fragment() {
    private lateinit var binding: FragmentPictureBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentPictureBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.addItemDecoration(RecyclerViewItemDecorator())

        binding.recyclerView.adapter = ListAdapter { position ->
            (activity as MainActivity).setFragment(DetailFragment(position))
        }
        lifecycleScope.launch {
            with(RetrofitService.getPicture()) {
                (binding.recyclerView.adapter as? ListAdapter)?.updateData(this)
                QueryUtils.extractData(this)
            }
            withContext(Dispatchers.Main) {
                binding.loadingBar.visibility = View.GONE
            }
        }
    }
}