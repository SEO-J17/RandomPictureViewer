# 📱RandomPictureViewer

+ 이미지를 불러와 이미지를 나타내는 APP

## 🎤Introduce

+ https://picsum.photos API를 이용하여 Unsplash 사이트의 이미지를 가져와서 화면에 나타낸다.
+ 사진을 클릭하면 사진의 상세정보가 나오고 url을 클릭하면 해당 사이트로 이동한다.
+ MVVM을 기반으로 프로젝트를 완성했다.

## ✨Feature

+ Paging을 이용하여 데이터를 효율적으로 가져오도록 구성.
+ Room을 이용하여 캐시 기능을 구현. 빠른 응답이 가능하도록 구성.
+ 리사이클러 뷰에 DiffUtil을 적용해 효율적인 데이터 업데이트를 하도록 구현.
+ MVVM패턴과 DI,Google Recomanded Architecture를 이용해 의존성을 낮추고 응집도를 높임

## 🛠Tech & Library

+ Kotlin 1.70
+ Retrofit 2.9.0
+ OkHttp 4.10.0
+ Moshi 1.14.0
+ Hilt 2.44
+ Glide 4.14.2
+ flavor
+ DataBinding
+ ViewBinding
+ Coroutine 1.6.4
+ Paging 3.1.1
+ Navigation 2.5.3
+ SafeArgs
+ Room 2.4.3
+ LiveData

## 🎨Structure

+ 패키지 구조는 다음과 같다.

  <img src=https://user-images.githubusercontent.com/59912150/213119427-fee58574-f103-40e3-a6de-f232f9043a9c.png>

## 🎉Result

<table>
<tr>
<td><img src=https://user-images.githubusercontent.com/59912150/213112316-b4144902-dbd4-4cba-9adc-c4e41f2b18b4.png></td>
<td><img src=https://user-images.githubusercontent.com/59912150/213112306-c48539af-b72b-4f08-81b1-71b56d415f92.png></td>
</tr>
</table>
