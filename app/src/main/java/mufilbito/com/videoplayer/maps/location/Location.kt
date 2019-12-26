//package mufilbito.com.videoplayer.maps.location
//
//import android.content.Intent
//import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
//import android.view.View
//import android.widget.TextView
//import butterknife.BindView
//import mufilbito.com.videoplayer.R
//import com.google.android.libraries.places.internal.i
//import com.sun.xml.internal.bind.v2.model.core.ID
//import java.util.Arrays.asList
//import android.R
//import com.google.android.libraries.places.widget.AutocompleteSupportFragment
//import android.R
//
//
//
//
//
//class Location : AppCompatActivity() {
//    @BindView(R.id.tv_animation)
//    internal var tv_animation: TextView? = null
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContentView(R.layout.location_screen)
//
//        tv_animation?.setOnClickListener {
//
//
//
//            /*val intent =
//                    Intent(this@Location, ThirdActivity::class.java)
//            intent.putExtra("key", "Kotlin")
//            startActivity(intent)*/
//        }
//
//    }
//
//
//    fun setPlaces(){
//        /**
//         * Initialize Places. For simplicity, the API key is hard-coded. In a production
//         * environment we recommend using a secure mechanism to manage API keys.
//         */
//        if (!Places.isInitialized()) {
//            Places.initialize(applicationContext, "YOUR_API_KEY")
//        }
//
//// Initialize the AutocompleteSupportFragment.
//        val autocompleteFragment = supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment
//
//        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME))
//
//        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener() {
//            fun onPlaceSelected(place: Place) {
//                // TODO: Get info about the selected place.
//                Log.i(FragmentActivity.TAG, "Place: " + place.getName() + ", " + place.getId())
//            }
//
//            fun onError(status: Status) {
//                // TODO: Handle the error.
//                Log.i(FragmentActivity.TAG, "An error occurred: $status")
//            }
//        })
//    }
//
//
//    /*override fun onClick(view: View?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//
//       *//* when (view?.id) {
//            R.id.tv_animation -> startActivity()
//
//
//        }*//*
//
//    }*/
//}