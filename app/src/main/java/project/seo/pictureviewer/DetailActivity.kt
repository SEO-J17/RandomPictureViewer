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
        bind(position)?.let {
            with(it) {
                binding.detailImage.load(download_url)
                binding.idValue.text = id
                binding.authorValue.text = author
                binding.widthValue.text = width
                binding.heightValue.text = height
                binding.urlValue.text = url
                binding.urlValue.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
                //TODO: 링크 클릭시 그림 다운로드해서 갤러리에 저장하기
                binding.downloadValue.text = download_url
                binding.backPage.setOnClickListener {
                    if (position > 0) {
                        startPage(backPosition)
                    } else {
                        showToast("처음 페이지 입니다.")
                    }
                }
                binding.nextPage.setOnClickListener {
                    if (position < QueryUtils.getSize() - 1) {
                        startPage(nextPosition)
                    } else {
                        showToast("마지막 페이지 입니다.")
                    }
                }
                binding.currentPicture.load(download_url)
                showPreview(binding.previousPicture, backPosition)
                showPreview(binding.nextPicture, nextPosition)
            }
        }
    }

    private fun bind(position: Int): PictureData? {
        return QueryUtils.get(position)
    }

    private fun showToast(message: String) {
        Toast.makeText(this@DetailActivity, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun showPreview(previewPicture: ImageView, position: Int) {
        previewPicture.load(bind(position)?.download_url ?: R.drawable.no_image)
    }


}