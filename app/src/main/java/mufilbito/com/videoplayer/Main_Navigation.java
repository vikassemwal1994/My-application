package mufilbito.com.videoplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import mufilbito.com.videoplayer.Utility.ShowToast;
import mufilbito.com.videoplayer.collapsing_toolbar.CollapsingToolbar;
import mufilbito.com.videoplayer.createpdf.CreatePdf;
import mufilbito.com.videoplayer.expandablelistview.*;
import mufilbito.com.videoplayer.animation.*;
import mufilbito.com.videoplayer.full_screen_videoview.FullScreenVideoActivity;
import mufilbito.com.videoplayer.introslider.*;
import mufilbito.com.videoplayer.kotlinClasses.recyclerview.RecyclerviewKotlin;
import mufilbito.com.videoplayer.kotlinClasses.retrohit.KotRetroHit;
import mufilbito.com.videoplayer.kotlinClasses.sqlitedatabase.SQLiteKotlinClass;
import mufilbito.com.videoplayer.maps.Location;
import mufilbito.com.videoplayer.maps.MapsMarkerActivity;
import mufilbito.com.videoplayer.servicesexample.JobIntentService.MyJobIntentServiceActivity;
import mufilbito.com.videoplayer.servicesexample.forgroundservice.ForegroundServiceActivity;
import mufilbito.com.videoplayer.servicesexample.freefallservice.FreeFallService;
import mufilbito.com.videoplayer.speech_to_text.*;
import mufilbito.com.videoplayer.retrohit.*;
import mufilbito.com.videoplayer.webview.Webview;

public class Main_Navigation extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.tv_animation)
    TextView tv_animation;@BindView(R.id.tv_webview)
    TextView tv_webview;@BindView(R.id.tv_bottom_sheet)
    TextView tv_bottom_sheet;@BindView(R.id.tv_download_file)
    TextView tv_download_file;@BindView(R.id.tv_expandable_list)
    TextView tv_expandable_list;@BindView(R.id.tv_intro_slider)
    TextView tv_intro_slider;@BindView(R.id.tv_retrofit)
    TextView tv_retrofit;@BindView(R.id.tv_speech_to_text)
    TextView tv_speech_to_text;@BindView(R.id.tv_volley_gson)
    TextView tv_volley_gson;@BindView(R.id.tv_forground_service)
    TextView tv_forground_service;@BindView(R.id.tv_FreeFallService)
    TextView tv_FreeFallService;@BindView(R.id.tv_MyJobIntentServiceActivity)
    TextView tv_MyJobIntentServiceActivity;@BindView(R.id.tv_create_pdf)
    TextView tv_create_pdf;@BindView(R.id.tv_kot_recycler)
    TextView tv_kot_recycler;@BindView(R.id.tv_kot_sqlite)
    TextView tv_kot_sqlite;@BindView(R.id.tv_kot_retroHit)
    TextView tv_kot_retroHit;@BindView(R.id.tv_kot_add_map)
    TextView tv_kot_add_map;@BindView(R.id.tv_kot_places)
    TextView tv_kot_places;@BindView(R.id.tv_collapsing)
    TextView tv_collapsing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_navigation);

        ButterKnife.bind(this);

        tv_expandable_list.setOnClickListener(this);
        tv_animation.setOnClickListener(this);
        tv_intro_slider.setOnClickListener(this);
        tv_speech_to_text.setOnClickListener(this);
        tv_retrofit.setOnClickListener(this);
        tv_volley_gson.setOnClickListener(this);
        tv_forground_service.setOnClickListener(this);
        tv_FreeFallService.setOnClickListener(this);
        tv_MyJobIntentServiceActivity.setOnClickListener(this);
        tv_create_pdf.setOnClickListener(this);
        tv_kot_recycler.setOnClickListener(this);
        tv_kot_sqlite.setOnClickListener(this);
        tv_kot_retroHit.setOnClickListener(this);
        tv_kot_add_map.setOnClickListener(this);
        tv_kot_places.setOnClickListener(this);
        tv_collapsing.setOnClickListener(this);
        tv_webview.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_webview:
                startActivity(new Intent(Main_Navigation.this, Webview.class));
                break;
                case R.id.tv_expandable_list:
                startActivity(new Intent(Main_Navigation.this, ExpandableActivity.class));
                break;

                case R.id.tv_animation:
                startActivity(new Intent(Main_Navigation.this, AnimationActivity.class));
                break;

                case R.id.tv_intro_slider:
                startActivity(new Intent(Main_Navigation.this, WelcomeActivity.class));
                break;

                case R.id.tv_speech_to_text:
                startActivity(new Intent(Main_Navigation.this, Speech_to_Text.class));
                break;

                case R.id.tv_retrofit:
                startActivity(new Intent(Main_Navigation.this, RetroHit.class));
                break;

                case R.id.tv_volley_gson:
//                startActivity(new Intent(Main_Navigation.this, PlayVideo2.class));
                startActivity(new Intent(Main_Navigation.this, FullScreenVideoActivity.class));
//                    new ShowToast(Main_Navigation.this).showToast("api is disabled...");
                break;

                case R.id.tv_forground_service:
                startActivity(new Intent(Main_Navigation.this, ForegroundServiceActivity.class));
                break;

                case R.id.tv_FreeFallService:
                startActivity(new Intent(Main_Navigation.this, FreeFallService.class));
                break;

                case R.id.tv_MyJobIntentServiceActivity:
                startActivity(new Intent(Main_Navigation.this, MyJobIntentServiceActivity.class));
                break;

                case R.id.tv_create_pdf:
                startActivity(new Intent(Main_Navigation.this, CreatePdf.class));
                break;

                case R.id.tv_kot_recycler:
                startActivity(new Intent(Main_Navigation.this, RecyclerviewKotlin.class));
                break;

                case R.id.tv_kot_sqlite:
                startActivity(new Intent(Main_Navigation.this, SQLiteKotlinClass.class));
                break;

                case R.id.tv_kot_retroHit:
                startActivity(new Intent(Main_Navigation.this, KotRetroHit.class));
                break;

                case R.id.tv_kot_add_map:
                startActivity(new Intent(Main_Navigation.this, MapsMarkerActivity.class));
                break;

                case R.id.tv_kot_places:
                startActivity(new Intent(Main_Navigation.this, Location.class));
                break;

                case R.id.tv_collapsing:
                startActivity(new Intent(Main_Navigation.this, CollapsingToolbar.class));
                break;

        }
    }
}
