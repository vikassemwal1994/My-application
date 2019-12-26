package mufilbito.com.videoplayer.kotlinClasses.retrohit

import mufilbito.com.videoplayer.retrohit.Comments
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceHolderApi {
    @GET("posts")
    abstract fun getposts(): Call<List<Model.Posts>>

    @GET("posts/{id}/comments")
    abstract fun getcomments(@Path("id") postid: Int): Call<List<Model.Comments>>

}