package mufilbito.com.videoplayer.retrohit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.List;
import mufilbito.com.videoplayer.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetroHit extends AppCompatActivity {

    private TextView textView;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retro_hit);

        textView = (TextView) findViewById(R.id.tv_response);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


//        getposts();


        getcomments();


    }

    // if wants posts
    public void getposts(){
        Call<List<Posts>> call = jsonPlaceHolderApi.getposts();


        final ProgressDialog pDialog = new ProgressDialog(RetroHit.this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                pDialog.dismiss();
                if (!response.isSuccessful()){
                    textView.setText(response.toString());
                    return;
                }

                List<Posts> posts = response.body();
                for (Posts posts1 : posts){
                    String content = "";
                    content += "id: "+posts1.getId() +"\n";
                    content += "userId: "+posts1.getUserId() +"\n";
                    content += "title: "+posts1.getTitle() +"\n";
                    content += "body: "+posts1.getBody() +"\n\n";

                    textView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }


    // if wants comments as per post
    public void getcomments(){
        Call<List<Comments>> call = jsonPlaceHolderApi.getcomments(3);

        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if (!response.isSuccessful()){
                    textView.setText(response.toString());
                    return;
                }

                List<Comments> posts = response.body();
                for (Comments posts1 : posts){
                    String content = "";
                    content += "id: "+posts1.getId() +"\n";
                    content += "userId: "+posts1.getName() +"\n";
                    content += "title: "+posts1.getEmail() +"\n";
                    content += "body: "+posts1.getBody() +"\n\n";

                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

}
