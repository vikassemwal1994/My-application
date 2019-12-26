package mufilbito.com.videoplayer.servicesexample.jobservice;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import mufilbito.com.videoplayer.R;
/*
 * not much to see here. Just the buttons and how to start the jobscheduler
 * references:
 * https://developer.android.com/reference/android/app/job/JobScheduler.html
 * https://developer.android.com/reference/android/app/job/JobInfo.Builder.html
 * https://github.com/googlesamples/android-JobScheduler
 * http://www.vogella.com/tutorials/AndroidTaskScheduling/article.html
*/


public class JobServiceActivity extends AppCompatActivity {
    EditText et_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_service);


        et_input = (EditText) findViewById(R.id.editText);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                myJobService.scheduleJob(JobServiceActivity.this, Integer.valueOf(et_input.getText().toString()), false);  //JobServiceActivity context, not listener...
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                myJobService.scheduleJob(JobServiceActivity.this, Integer.valueOf(et_input.getText().toString()), true);  //JobServiceActivity context, not listener...
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                myJobService.cancelJob(JobServiceActivity.this);
            }
        });

    }
}
