package project.seo.pictureviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import project.seo.pictureviewer.data.PictureInfo
import project.seo.pictureviewer.databinding.ActivityMainBinding
import project.seo.pictureviewer.network.PicturesAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val requestUrl = "https://picsum.photos"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this)
        val retrofit = Retrofit.Builder().baseUrl(requestUrl).addConverterFactory(
            GsonConverterFactory.create()).build()
        val api = retrofit.create(PicturesAPI::class.java)

        api.getPicture(1, 100).enqueue(object : Callback<PictureInfo> {
            override fun onResponse(call: Call<PictureInfo>, response: Response<PictureInfo>) {
                if (response.isSuccessful) {
                    binding.recyclerView.adapter =
                        ListAdapter(QueryUtils.extractData(response.body()))
                    //Log.d("MainActivity", response.body().toString())
                } else {
                    Log.e("MainActivity", "실패")
                }
            }

            override fun onFailure(call: Call<PictureInfo>, t: Throwable) {
                Log.e("MainActivity", "onFailure 에러: ${t.message.toString()}")
            }

        })
    }
}