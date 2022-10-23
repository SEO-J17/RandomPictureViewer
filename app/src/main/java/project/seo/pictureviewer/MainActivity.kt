package project.seo.pictureviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
        val recyclerView = binding.recyclerView

        recyclerView.layoutManager =
            LinearLayoutManager(this)
        val retrofit = Retrofit.Builder().baseUrl(requestUrl).addConverterFactory(
            GsonConverterFactory.create()).build()
        val api = retrofit.create(PicturesAPI::class.java)

        api.getPicture(1, 100).enqueue(object : Callback<PictureInfo> {
            override fun onResponse(call: Call<PictureInfo>, response: Response<PictureInfo>) {
                if (response.isSuccessful) {
                    binding.loadingBar.visibility = View.GONE
                    val recyclerAdapter = ListAdapter(QueryUtils.extractData(response.body()))
                    recyclerView.adapter = recyclerAdapter

                    recyclerAdapter.setItemClickListener(object :
                        ListAdapter.OnItemClickListener {
                        override fun onClick(view: View, position: Int) {
                            startActivity(Intent(this@MainActivity,
                                DetailPicture::class.java).putExtra("picturePosition",
                                position))
                        }
                    })
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