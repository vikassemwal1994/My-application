package mufilbito.com.videoplayer.kotlinClasses.recyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import mufilbito.com.videoplayer.R

class RecyclerviewKotlin: AppCompatActivity() {

    private lateinit var mNicolasCageMovies: ArrayList<Movie>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kot_recycler_main);

        setValue();


        //getting recyclerview from xml
        val recyclerView = findViewById(R.id.list_recycler_view) as RecyclerView

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        //creating our adapter
        val adapter = CustomAdapter(mNicolasCageMovies);

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter

    }

    private fun setValue(){


        val mNicolasCageMovies1 = listOf(
                Movie("Raising Arizona", 1987),
                Movie("Vampire's Kiss", 1988),
                Movie("Con Air", 1997),
                Movie("Gone in 60 Seconds", 1997),
                Movie("National Treasure", 2004),
                Movie("The Wicker Man", 2006),
                Movie("Ghost Rider", 2007),
                Movie("Knowing", 2009)
        )

        mNicolasCageMovies = ArrayList(mNicolasCageMovies1);

    }


}