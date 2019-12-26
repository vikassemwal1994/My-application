package mufilbito.com.videoplayer.introslider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import mufilbito.com.videoplayer.R;

public class IntroSlider extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        findViewById(R.id.btn_play_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We normally won't show the welcome slider again in real app
                // but this is for testing
                /*PrefManager prefManager = new PrefManager(getApplicationContext());

                // make first time launch TRUE
                prefManager.setFirstTimeLaunch(true);

                startActivity(new Intent(GsonWithVolley.this, WelcomeActivity.class));
                finish();*/
            }
        });
    }
}
