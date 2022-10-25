package project.seo.pictureviewer.network

import project.seo.pictureviewer.data.PictureInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
//명시된 API에 따라서 통신해야 하기 때문에 레트로핏 객체가 구현할 API 인터페이스.
interface PicturesAPI {
    //uri의 엔드포인트 설정
    @GET("/v2/list")
    fun getPicture(
        //단일 파라미터를 사용하기 때문에 @query이용.
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Call<PictureInfo> //call 객체 반환, 타입은 PictureInfo이다.
}