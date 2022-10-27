package project.seo.pictureviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import project.seo.pictureviewer.data.PictureInfo
import project.seo.pictureviewer.databinding.ActivityMainBinding
import project.seo.pictureviewer.network.RetrofitBuild
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager =
            GridLayoutManager(this, 2)
        RetrofitBuild.api
            .getPicture(1, 100)
            .enqueue(object : Callback<PictureInfo> {

                override fun onResponse(
                    call: Call<PictureInfo>,
                    response: Response<PictureInfo>,
                ) {
                    if (response.isSuccessful) {
                        //데이터를 다 받아와서 뷰에 표시 되면 로딩바 제거.
                        binding.loadingBar.visibility = View.GONE
                        //
                        val recyclerAdapter = ListAdapter(QueryUtils.extractData(response.body()))
                        //리사이클러 어댑터에 JSON을 리스트로 변환한 데이터 세트를 넣는다.
                        recyclerView.adapter = recyclerAdapter
                        //리스트에서 데이터가 클릭되면 실행된다.
                        //클릭되면 그림에 대한 상세페이지로 이동하게 한 커스텀 클릭리스너이다.
                        recyclerAdapter.setItemClickListener(object : //무명 객체 사용.
                            ListAdapter.OnItemClickListener {
                            override fun onClick(view: View, position: Int) {
                                //Detail액티비티가 실행됨가 동시에 인텐트에 클릭된 위치(인덱스)값을 넣어서 액티비티에 보낸다.
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