package project.seo.pictureviewer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.util.Linkify
import androidx.appcompat.app.AppCompatActivity
import coil.load
import project.seo.pictureviewer.data.PictureData
import project.seo.pictureviewer.databinding.ActivityDetailBinding

class DetailPicture : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dataSet =
            intent.getSerializableExtra("pictureData") as? PictureData //캐스팅 필수! 아니면 Serializable 객체로 된다.

        dataSet?.let {
            with(it) {
                binding.detailImage.load(imageUrl)
                binding.idValue.text = id
                binding.authorValue.text = author
                binding.widthValue.text = width
                binding.heightValue.text = height

                binding.urlValue.text = pageUrl
                binding.urlValue.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl)))
                }

                binding.downloadValue.text = imageUrl
            }

        }
    }
}