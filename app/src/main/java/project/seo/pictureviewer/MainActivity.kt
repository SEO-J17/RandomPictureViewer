package project.seo.pictureviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import project.seo.pictureviewer.data.PictureData
import project.seo.pictureviewer.data.PictureInfo
import project.seo.pictureviewer.databinding.ActivityMainBinding
import project.seo.pictureviewer.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = ListAdapter { position ->
            startActivity(Intent(this@MainActivity,
                DetailActivity::class.java)
                .putExtra(EXTRA_NAME,
                    position))
        }

        RetrofitService
            .getPicture()
            .enqueue(object : Callback<PictureInfo> {
                override fun onResponse(
                    call: Call<PictureInfo>,
                    response: Response<PictureInfo>,
                ) {
                    if (response.isSuccessful) {
                        binding.loadingBar.visibility = View.GONE
                        (binding.recyclerView.adapter as? ListAdapter)?.updateData(
                            QueryUtils.extractData(response.body()))
                    } else {
                        Log.e("MainActivity", "실패")
                    }
                }

                override fun onFailure(call: Call<PictureInfo>, t: Throwable) {
                    Log.e("MainActivity", "onFailure 에러: ${t.message.toString()}")
                }
            })
    }

    companion object {
        const val EXTRA_NAME = "picturePosition"
    }
}