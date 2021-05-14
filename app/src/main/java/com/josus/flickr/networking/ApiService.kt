package com.josus.flickr.networking

import retrofit2.Call
import retrofit2.http.GET


private const val BASE_URL = "https://api.flickr.com/services/rest/"




interface ApiService {
    @GET("?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s")
  fun getPhotos(): Call <PhotoDirectory>


}