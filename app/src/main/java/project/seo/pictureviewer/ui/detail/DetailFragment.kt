package project.seo.pictureviewer.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.coroutineScope
import coil.load
import kotlinx.coroutines.CoroutineScope
import project.seo.pictureviewer.R
import project.seo.pictureviewer.databinding.FragmentDetailBinding

class DetailFragment(
) : Fragment(), DetailContract.View {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var presenter: DetailContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        presenter = DetailPresenter(this, arguments?.getInt(PICTURE_ID) ?: 0)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override val lifecycleScope: CoroutineScope
        get() = lifecycle.coroutineScope

    override fun showPicture(url: String) {
        binding.detailImage.load(url)
    }

    override fun showId(id: Int) {
        binding.idValue.text = "$id"
    }

    override fun showAuthor(author: String) {
        binding.authorValue.text = author
    }

    override fun showWidth(width: String) {
        binding.widthValue.text = width
    }

    override fun showHeight(height: String) {
        binding.heightValue.text = height
    }

    override fun showUrl(url: String) {
        binding.urlValue.text = url
    }

    override fun showDownloadUrl(downloadUrl: String) {
        binding.downloadValue.text = downloadUrl
    }

    override fun showErrorToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showPreviousPreview(url: String, pictureId: Int) {
        with(binding.previousPicture) {
            load(url)
            setOnClickListener {
                changePage(pictureId)
            }
        }
    }

    override fun showCurrentPreview(url: String) {
        binding.currentPicture.load(url)
    }

    override fun showNextPreview(url: String, pictureId: Int) {
        with(binding.nextPicture) {
            load(url)
            setOnClickListener {
                changePage(pictureId)
            }
        }
    }

    override fun showPictureWebSite(url: Uri) {
        binding.urlValue.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, url))
        }
    }

    override fun showNextPage(id: Int) {
        binding.nextPage.setOnClickListener {
            changePage(id)
        }
    }

    override fun showPreviousPage(id: Int) {
        binding.backPage.setOnClickListener {
            changePage(id)
        }
    }

    override fun showPreviousNoImage(id: Int) {
        binding.previousPicture.load(id)
    }

    override fun showNextNoImage(id: Int) {
        binding.nextPicture.load(id)
    }

    override fun changePage(pictureId: Int) {
        parentFragmentManager.commit {
            replace<DetailFragment>(
                R.id.container, args = Bundle().apply {
                    putInt(PICTURE_ID, pictureId)
                }
            )
        }
    }

    companion object {
        const val PICTURE_ID = "pictureId"
    }
}