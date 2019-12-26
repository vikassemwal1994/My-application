package mufilbito.com.videoplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class PlayVideo extends AppCompatActivity{

    VideoView vidView;
    private ArrayList<VideoListModel> list;
    private int position=0;
    private ImageView iv_left, iv_right;
    MediaController mediaController;
    private boolean changeOrNot=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.video_play_activity);
        vidView = (VideoView)findViewById(R.id.myVideo);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_right = (ImageView) findViewById(R.id.iv_right);



        //get list from previous video list activity
        Bundle bund = getIntent().getExtras();
        if (bund != null) {
            list = (ArrayList<VideoListModel>) getIntent().getSerializableExtra("value");
            position = getIntent().getIntExtra("position", 0);
        } else {

        }


        //set value of current position
        String vidAddress = list.get(position).getVideo_rendition_path();
        Uri vidUri = Uri.parse(vidAddress);



        //setting media controller
        mediaController = new MediaController(this);
        mediaController.setAnchorView(vidView);
        mediaController.setMediaPlayer(vidView); //you probably don't need this
        vidView.setVideoURI(vidUri);
        vidView.setMediaController(mediaController);
        vidView.start();


        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position==0)
                    changeOrNot = false;
                else
                    changeOrNot = true;
                changeVideo(0);

            }
        });

        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position==list.size()-1)
                    changeOrNot = false;
                else
                    changeOrNot = true;
                changeVideo(1);

            }
        });



    }


    public void changeVideo(int value){
        String vidAddress;

        if(changeOrNot == true){
            if (value==0){
                vidAddress = list.get(position-1).getVideo_rendition_path();
                position = position-1;
            }
            else{
                vidAddress = list.get(position+1).getVideo_rendition_path();
                position = position+1;
            }

            Uri vidUri = Uri.parse(vidAddress);

            mediaController = new MediaController(this);
            mediaController.setAnchorView(vidView);
            mediaController.setMediaPlayer(vidView); //you probably don't need this
            vidView.setVideoURI(vidUri);
            vidView.setMediaController(mediaController);
            vidView.start();
        }


    }





}
