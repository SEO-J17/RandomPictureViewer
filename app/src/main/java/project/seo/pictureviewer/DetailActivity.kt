package project.seo.pictureviewer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import project.seo.pictureviewer.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = (intent.getSerializableExtra("picturePosition") as Int)
        val dataSet = QueryUtils.dataSet[position]
        val backPosition = position - 1
        val nextPosition = position + 1

        with(dataSet) {
            binding.detailImage.load(imageUrl)
            binding.idValue.text = id
            binding.authorValue.text = author
            binding.widthValue.text = width
            binding.heightValue.text = height
            binding.urlValue.text = pageUrl
            binding.urlValue.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl)))
            }
            //TODO: 링크 클릭시 그림 다운로드해서 갤러리에 저장하기
            binding.downloadValue.text = imageUrl
            binding.backPage.setOnClickListener {
                if (position > 0) {
                    startPage(backPosition)
                } else {
                    Toast.makeText(this@DetailActivity, "처음 페이지 입니다.", Toast.LENGTH_SHORT).show()
                }
            }
            binding.nextPage.setOnClickListener {
                if (position < QueryUtils.dataSet.size - 1) {
                    startPage(nextPosition)
                } else {
                    Toast.makeText(this@DetailActivity, "마지막 페이지 입니다.", Toast.LENGTH_SHORT).show()
                }
            }
            binding.currentPicture.load(imageUrl)
            if (backPosition >= 0) {
                binding.previousPicture.load(QueryUtils.dataSet[backPosition].imageUrl)
            }
            if (nextPosition < QueryUtils.dataSet.size) {
                binding.nextPicture.load(QueryUtils.dataSet[nextPosition].imageUrl)
            }
        }
    }
    private fun startPage(position: Int) {
        startActivity(Intent(this@DetailActivity,
            DetailActivity::class.java).putExtra("picturePosition", position))
        finish()
    }
}