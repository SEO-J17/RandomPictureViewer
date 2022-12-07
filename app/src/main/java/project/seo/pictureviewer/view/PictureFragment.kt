package project.seo.pictureviewer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import project.seo.pictureviewer.R
import project.seo.pictureviewer.adapter.ListAdapter
import project.seo.pictureviewer.RecyclerViewItemDecorator
import project.seo.pictureviewer.contract.PictureContract
import project.seo.pictureviewer.data.PictureData
import project.seo.pictureviewer.databinding.FragmentPictureBinding
import project.seo.pictureviewer.presenter.PicturePresenter

class PictureFragment : Fragment(), PictureContract.View {
    private lateinit var binding: FragmentPictureBinding
    private lateinit var presenter: PictureContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentPictureBinding.inflate(layoutInflater, container, false)
        presenter = PicturePresenter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.recyclerView) {
            adapter = ListAdapter { pictureId ->
                presenter.onItemClick(pictureId)
            }
            addItemDecoration(RecyclerViewItemDecorator())
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override val lifecycleScope: CoroutineScope
        get() = lifecycle.coroutineScope

    override fun showLoadingBar() {
        binding.loadingBar.visibility = View.VISIBLE
    }

    override fun hideLoadingBar() {
        binding.loadingBar.visibility = View.GONE
    }

    override fun showPictures(pictureInfo: List<PictureData>) {
        (binding.recyclerView.adapter as? ListAdapter)?.updatePictures(pictureInfo)
    }

    override fun showDetail(pictureId: Int) {
        parentFragmentManager.commit {
            replace<DetailFragment>(
                R.id.container, args = Bundle().apply {
                    putInt(DetailFragment.PICTURE_ID, pictureId)
                }
            )
        }
    }
}
