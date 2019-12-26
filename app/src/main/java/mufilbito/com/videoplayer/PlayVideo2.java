package mufilbito.com.videoplayer;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayVideo2 extends AppCompatActivity{
    VideoView vidView;
    ImageView iv_full;
    private int position=0;
    MediaController mediaController;
    private boolean changeOrNot=true;
    String url= "http://risingpole.com/images/Video/Video_02052019025853.mp4";
    private int click=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.video_play_activity2);
        vidView = (VideoView)findViewById(R.id.myVideo);
//        iv_full = (ImageView) findViewById(R.id.iv_full);


        //set value of current position
//        String vidAddress = list.get(position).getVideo_rendition_path();
        String vidAddress = url;
        Uri vidUri = Uri.parse(vidAddress);

        //setting media controller
        mediaController = new MediaController(this);
        mediaController.setAnchorView(vidView);
        mediaController.setMediaPlayer(vidView); //you probably don't need this
        vidView.setVideoURI(vidUri);
        vidView.setMediaController(mediaController);
        vidView.start();

        /*iv_full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click==1){
                    click=2;
                    fullSize();

                }else {
                    click=1;
                    normalSize();
                }

            }
        });*/



    }

    private void fullSize(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) vidView.getLayoutParams();
        params.width = metrics.widthPixels;
        params.height = metrics.heightPixels;
        params.leftMargin = 0;
        vidView.setLayoutParams(params);
    }

    private void normalSize(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) vidView.getLayoutParams();
        params.width = (int)(300*metrics.density);
        params.height = (int)(250*metrics.density);
        params.leftMargin = 30;
        vidView.setLayoutParams(params);
    }



}
