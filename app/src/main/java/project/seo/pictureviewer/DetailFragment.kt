package project.seo.pictureviewer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import project.seo.pictureviewer.data.PictureData
import project.seo.pictureviewer.databinding.FragmentDetailBinding

class DetailFragment(
    private val position: Int
) : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startPage(position)
    }

    private fun startPage(position: Int) {
        val backPosition = position - 1
        val nextPosition = position + 1
        getDataSet(position)?.let { data ->
            with(binding) {
                detailImage.load(data.downloadUrl)
                idValue.text = data.id
                authorValue.text = data.author
                widthValue.text = data.width
                heightValue.text = data.height
                urlValue.text = data.url
                urlValue.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data.url)))
                }
                //TODO: 링크 클릭시 그림 다운로드해서 갤러리에 저장하기
                downloadValue.text = data.downloadUrl
                backPage.setOnClickListener {
                    if (position > 0) {
                        (activity as MainActivity).setFragment(DetailFragment(backPosition))
                    } else {
                        showToast("처음 페이지 입니다.")
                    }
                }
                nextPage.setOnClickListener {
                    if (position < QueryUtils.getSize() - 1) {
                        (activity as MainActivity).setFragment(DetailFragment(nextPosition))
                    } else {
                        showToast("마지막 페이지 입니다.")
                    }
                }
                currentPicture.load(data.downloadUrl)
                showPreview(previousPicture, backPosition)
                showPreview(nextPicture, nextPosition)
            }
        }
    }

    private fun getDataSet(position: Int): PictureData? = QueryUtils.get(position)

    private fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun showPreview(previewPicture: ImageView, position: Int) {
        previewPicture.load(getDataSet(position)?.downloadUrl ?: R.drawable.no_image)
    }


}