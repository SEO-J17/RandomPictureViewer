package project.seo.pictureviewer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import project.seo.pictureviewer.databinding.ActivityDetailBinding

class DetailPicture : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //메인 액티비티에서 리사이클러뷰에서 클릭된  위치를 받고 int로 캐스팅 한다.
        // 캐스팅을 안하면 Serialize 객체로 사용된다. 그렇기 때문에 사용하려면 캐스팅은 필수다.
        val position = (intent.getSerializableExtra("picturePosition") as Int)
        //클릭된 위치에 해당하는 데이터 세트를 가져온다. 여기서 데이터 세트는 이미지의 정보이다.
        val dataSet = QueryUtils.dataSet[position]
        //이전 사진의 정보를 받아와야 하기 때문에 현재 포지션의 -1을 하여 이전 사진의 위치를 저장했다.
        val backPosition = position - 1
        //다음 사진의 정보를 받아와야 하기 때문에 현재 포지션의 +1을 하여 다음 사진의 위치를 저장했다.
        val nextPosition = position + 1

        with(dataSet) {
            binding.detailImage.load(imageUrl)
            binding.idValue.text = id
            binding.authorValue.text = author
            binding.widthValue.text = width
            binding.heightValue.text = height
            binding.urlValue.text = pageUrl
            //url을 클릭하면 해당하는 그림의 사이트로 이동할수 있도록 인텐트와 인텐트 액션, uri파서를 이용했다.
            binding.urlValue.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl)))
            }
            //TODO: 링크 클릭시 그림 다운로드해서 갤러리에 저장하기
            binding.downloadValue.text = imageUrl
            //뒤로가기 그림이 있는 이미지뷰를 클릭하면 이전 페이지로 갈 수 있도록 클릭 리스너를 작성했다.
            binding.backPage.setOnClickListener {
                //현재 위치가 만약 0이라면 이전 페이지가 없으므로 이전 페이지로 가는 startPage메소드를 호출 하지 않도록 했다.
                if (position > 0) {
                    startPage(backPosition)
                } else {
                    //현재 위치가 0인데 이전페이지로 가는 버튼을 클릭했다면 toast메시지.
                    Toast.makeText(this@DetailPicture, "처음 페이지 입니다.", Toast.LENGTH_SHORT).show()
                }
            }
            //다음 화살표 그림이 있는 이미지뷰를 클릭하면 다음 페이지로 갈 수 있도록 클릭 리스너를 작성했다.
            binding.nextPage.setOnClickListener {
                //현재 위치가 전체 데이터의 개수보다 크면 다음 페이지로 가는 기능이 실행 되면 안되므로 if로 제한했다.
                //데이터 세트의 인덱스는 0부터 시작하므로 -1을 해줬다.
                if (position < QueryUtils.dataSet.size - 1) {
                    startPage(nextPosition)
                } else {
                    Toast.makeText(this@DetailPicture, "마지막 페이지 입니다.", Toast.LENGTH_SHORT).show()
                }
            }
            //현재 페이지의 그림을 나타내기 위한 코드
            binding.currentPicture.load(imageUrl)
            //만약 이전 위치가  0보다 크거나 같으면 그림이 있는 것이므로 그림을 load했다. Coil을 이용했다.
            if (backPosition >= 0) {
                binding.previousPicture.load(QueryUtils.dataSet[backPosition].imageUrl)
            }
            //만약 다음 위치가 전체 데이터세트의 개수보다 작으면 그림이 있는 것이므로 그림을 load했다.
            if (nextPosition < QueryUtils.dataSet.size) {
                binding.nextPicture.load(QueryUtils.dataSet[nextPosition].imageUrl)
            }
        }
    }
    //이전,다음 그림으로 이동 할 때  startActivity 메소드가 공통적으로 실행되므로 함수로 따로 분리했다.
    //intent에 putExtra를 통해 현재 위치를 전달하여 다음 액티비티가 실행 될 때 위치를 업데이트 하도록 했다.
    private fun startPage(position: Int) {
        startActivity(Intent(this@DetailPicture,
            DetailPicture::class.java).putExtra("picturePosition", position))
        finish()
    }
}