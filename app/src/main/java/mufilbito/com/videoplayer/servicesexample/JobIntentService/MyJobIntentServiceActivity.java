package mufilbito.com.videoplayer.servicesexample.JobIntentService;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import mufilbito.com.videoplayer.R;

//nothing to see here, move along.

public class MyJobIntentServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myjob_intent_service);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment()).commit();
        }
    }

}
