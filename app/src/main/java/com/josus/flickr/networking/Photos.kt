package com.josus.flickr.networking

data class PhotoDirectory(val photos:Photos, val stat: String)

data class Photos(
 val page: Int,
 val pages: Int,
 val perpage: Int,
 val total: Int,
 val photo: List<Photo>
)

data class Photo(
 val id: String,
 val title: String,
 val url_s: String
)

