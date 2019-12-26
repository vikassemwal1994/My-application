package mufilbito.com.videoplayer.kotlinClasses.retrohit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import mufilbito.com.videoplayer.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KotRetroHit : AppCompatActivity(){

    private lateinit var textView : TextView;
    private lateinit var jsonPlaceHolderApi: JsonPlaceHolderApi;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.retro_hit);

        textView = findViewById(R.id.tv_response) as TextView

        val retrofit = Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        jsonPlaceHolderApi = retrofit.create<JsonPlaceHolderApi>(JsonPlaceHolderApi::class.java)

        getposts();


    }

    // if wants posts
    fun getposts() {
        val call = jsonPlaceHolderApi.getposts()



        call.enqueue(object : Callback<List<Model.Posts>> {
            override fun onResponse(call: Call<List<Model.Posts>>, response: Response<List<Model.Posts>>) {

                if (!response.isSuccessful) {
                    textView.text = response.toString()
                    return
                }

                val posts = response.body()
                for (posts1 in posts) {
                    var content = ""
                    content += "id: " + posts1.id + "\n"
                    content += "userId: " + posts1.userId + "\n"
                    content += "title: " + posts1.title + "\n"
                    content += "body: " + posts1.body + "\n\n"

                    textView.append(content)
                }

            }

            override fun onFailure(call: Call<List<Model.Posts>>, t: Throwable) {
                textView.text = t.message
            }
        })
    }

    // if wants comments as per post
    fun getcomments() {
        val call = jsonPlaceHolderApi.getcomments(3)

        call.enqueue(object : Callback<List<Model.Comments>> {
            override fun onResponse(call: Call<List<Model.Comments>>, response: Response<List<Model.Comments>>) {
                if (!response.isSuccessful) {
                    textView.text = response.toString()
                    return
                }

                val posts = response.body()
                for (posts1 in posts) {
                    var content = ""
                    content += "id: " + posts1.id + "\n"
                    content += "userId: " + posts1.name + "\n"
                    content += "title: " + posts1.email + "\n"
                    content += "body: " + posts1.body + "\n\n"

                    textView.append(content)
                }
            }

            override fun onFailure(call: Call<List<Model.Comments>>, t: Throwable) {
                textView.text = t.message
            }
        })
    }
}