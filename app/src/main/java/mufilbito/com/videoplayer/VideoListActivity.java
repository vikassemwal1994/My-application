package mufilbito.com.videoplayer;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mufilbito.com.videoplayer.download_file.*;


public class VideoListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String apiAccesToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjoiMTUxODY5MjIwNjY1NiIsInVzZXJfZGV2aWNlX2NvZGUiOiIzNDA3OEVEMkQyOTUyODUwOTlEQzk4M0FGMDJDOTU2OCIsImN1cnJlbnRfZGF0ZSI6IjIwMTgtMDMtMTkgMTc6MDg6MTIifQ.yS1GX2PCOxbwLLK0LQzohb6St9MfRUZdlINH7nD5k-7MfT0cJxUCdACef7IPs4IAEJuyAEEeGSBIGSWz610w0A";
    private String apiUrl = "https://hoths15xih.execute-api.ap-southeast-1.amazonaws.com/CueStageTesting/playlist";
    private ArrayList<VideoListModel> list;

    private SharedPreferences permissionStatus;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private boolean sentToSettings = false;
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gson_with_volley);

//        apiHit2();

        permissionStatus = getSharedPreferences("permissionStatus",MODE_PRIVATE);
        permission();

        //initialization
        recyclerView = (RecyclerView) findViewById(R.id.rv_video_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    //stroage runtime permission
    private void permission(){
        if (ActivityCompat.checkSelfPermission(VideoListActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(VideoListActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Show Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(VideoListActivity.this);
                builder.setTitle("Need Storage Permission");
                builder.setMessage("This app needs storage permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(VideoListActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else if (permissionStatus.getBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE,false)) {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(VideoListActivity.this);
                builder.setTitle("Need Storage Permission");
                builder.setMessage("This app needs storage permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sentToSettings = true;
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                        Toast.makeText(getBaseContext(), "Go to Permissions to Grant Storage", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                //just request the permission
                ActivityCompat.requestPermissions(VideoListActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
            }

            SharedPreferences.Editor editor = permissionStatus.edit();
            editor.putBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE,true);
            editor.commit();


        } else {
            //You already have the permission, just go ahead.
            proceedAfterPermission();
        }
    }

    private void proceedAfterPermission() {
        //We've got the permission, now we can proceed further
        Toast.makeText(getBaseContext(), "We got the Storage Permission", Toast.LENGTH_LONG).show();

        apiHit2();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == EXTERNAL_STORAGE_PERMISSION_CONSTANT) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //The External Storage Write Permission is granted to you... Continue your left job...
                proceedAfterPermission();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(VideoListActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    //Show Information about why you need the permission
                    AlertDialog.Builder builder = new AlertDialog.Builder(VideoListActivity.this);
                    builder.setTitle("Need Storage Permission");
                    builder.setMessage("This app needs storage permission");
                    builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                            ActivityCompat.requestPermissions(VideoListActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else {
                    Toast.makeText(getBaseContext(),"Unable to get Permission",Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    // api hit for video listing
    public void apiHit2(){
        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                apiUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("volley", "Response 1111: " + response.toString());
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.getInt("status")==0){
                                JSONArray array = object.getJSONObject("data").getJSONObject("data").getJSONArray("newslist");
                                list = new ArrayList<>();

                                /*GsonBuilder gsonBuilder = new GsonBuilder();
                                Gson gson;
                                gson = gsonBuilder.create();
                                list = (ArrayList<VideoListModel>) Arrays.asList(gson.fromJson(array.toString()
                                        , VideoListModel[].class));*/

                                for (int i=0; i<array.length(); i++){
                                    VideoListModel temp = new VideoListModel();
                                    temp.setVideo_name(array.getJSONObject(i).getString("video_name"));
                                    temp.setVideo_rendition_thumbnail(array.getJSONObject(i).getString("video_rendition_thumbnail"));
                                    temp.setVideo_rendition_path(array.getJSONObject(i).getString("video_rendition_path"));
                                    list.add(temp);
                                }

                            }

                            VideoListAdatper mAdapter = new VideoListAdatper(VideoListActivity.this);
                            recyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley", "Error: " + error.getMessage());
                error.printStackTrace();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("lang","en");
                params.put("accessToken", apiAccesToken);

                Log.e("",""+params);
                return params;
            }

        };

        RequestQueue queue = Volley.newRequestQueue(VideoListActivity.this);
        queue.add(jsonObjRequest);

    }


    // Adapter for video listing
    public class VideoListAdatper extends RecyclerView.Adapter<VideoListAdatper.MyViewHolder> {

        Context context;

        public VideoListAdatper(Context context) {
            this.context = context;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView tv_name, tv_date;
            private ImageView iv_image;
            private LinearLayout ll_play_main;

            public MyViewHolder(View view) {
                super(view);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
                tv_date = (TextView) view.findViewById(R.id.tv_date);
                iv_image = (ImageView) view.findViewById(R.id.iv_image);
                ll_play_main = (LinearLayout) view.findViewById(R.id.ll_play_main);


            }
        }

        @Override
        public VideoListAdatper.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.video_list_single_row, parent, false);
            return new VideoListAdatper.MyViewHolder(itemView);

        }

        @Override
        public void onBindViewHolder(VideoListAdatper.MyViewHolder holder, final int position) {
            VideoListModel temp = new VideoListModel();
            temp = list.get(position);

            holder.tv_name.setText(temp.getVideo_name());
//            holder.tv_date.setText(temp.getVideo_name());
            Picasso.get().load(list.get(position).getVideo_rendition_thumbnail())
                    .placeholder(R.drawable.placeholder).into(holder.iv_image);

            holder.tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "";
                    url = list.get(position).getVideo_rendition_path();
//                    url = "https://www.youtube.com/watch?v=xTHfby0AMNw";
                    new DownloadFileFromURL(VideoListActivity.this, url).execute(url);


                }
            });

            holder.ll_play_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(VideoListActivity.this, PlayVideo.class);
                    intent.putExtra("value", list);
                    intent.putExtra("position", position);
                    startActivity(intent);

                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

    }


}
