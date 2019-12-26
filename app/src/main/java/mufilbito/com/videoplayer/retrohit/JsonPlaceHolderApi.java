package mufilbito.com.videoplayer.retrohit;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Posts>> getposts();

    @GET("posts/{id}/comments")
    Call<List<Comments>> getcomments(@Path("id") int postid);

}
