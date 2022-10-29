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

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startPage(intent.getSerializableExtra(MainActivity.EXTRA_NAME) as Int)
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
                        startPage(backPosition)
                    } else {
                        showToast("처음 페이지 입니다.")
                    }
                }
                nextPage.setOnClickListener {
                    if (position < QueryUtils.getSize() - 1) {
                        startPage(nextPosition)
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
        Toast.makeText(this@DetailActivity, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun showPreview(previewPicture: ImageView, position: Int) {
        previewPicture.load(getDataSet(position)?.downloadUrl ?: R.drawable.no_image)
    }


}