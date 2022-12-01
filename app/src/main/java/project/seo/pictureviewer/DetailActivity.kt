package project.seo.pictureviewer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import project.seo.pictureviewer.data.PictureData
import project.seo.pictureviewer.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), DetailContract.View {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var presenter: DetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = DetailPresenter(this)
        presenter.getPictureInfo(
            intent.getSerializableExtra(MainActivity.EXTRA_DATA_SET) as List<PictureData>,
            intent.getSerializableExtra(MainActivity.EXTRA_NAME) as Int,
        )
    }

    override fun showErrorToast(message: String) {
        Toast.makeText(this@DetailActivity, message, Toast.LENGTH_SHORT)
            .show()
    }

    override fun showPreview(
        previewPicture: ImageView,
        dataSet: List<PictureData>,
        position: Int
    ) {
        with(previewPicture) {
            try {
                load(dataSet[position].downloadUrl)
            } catch (e: ArrayIndexOutOfBoundsException) {
                load(R.drawable.no_image)
            }
        }
    }

    override fun showPictureWebSite(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    override fun showPictureInfo(dataSet: List<PictureData>, position: Int) {
        val pictureData = dataSet[position]
        with(binding) {
            detailImage.load(pictureData.downloadUrl)
            idValue.text = pictureData.id
            authorValue.text = pictureData.author
            widthValue.text = pictureData.width
            heightValue.text = pictureData.height
            urlValue.text = pictureData.url
            urlValue.setOnClickListener {
                showPictureWebSite(pictureData.url)
            }
            downloadValue.text = pictureData.downloadUrl
            backPage.setOnClickListener {
                if (position > 0) {
                    presenter.getPictureInfo(dataSet, position - 1)
                } else {
                    showErrorToast("처음 페이지 입니다.")
                }
            }
            nextPage.setOnClickListener {
                if (position < dataSet.size - 1) {
                    presenter.getPictureInfo(dataSet, position + 1)
                } else {
                    showErrorToast("마지막 페이지 입니다.")
                }
            }
            currentPicture.load(pictureData.downloadUrl)
            showPreview(previousPicture, dataSet, position - 1)
            showPreview(nextPicture, dataSet, position + 1)
        }
    }
}