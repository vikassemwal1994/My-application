package mufilbito.com.videoplayer.volleygson;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mufilbito.com.videoplayer.R;

public class GsonWithVolley extends AppCompatActivity {

    String apiUrl = "http://api.utsavcard.com/api/Order/GetCart?vendorId=1";
    RecyclerView recyclerView;
    private Gson gson;
    List<CartModel> CartList;
    private VideoListAdatper mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gson_with_volley);

        recyclerView = (RecyclerView)findViewById(R.id.rv_video_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        getCartValue();

    }

    public void getCartValue() {
        final ProgressDialog pDialog = new ProgressDialog(GsonWithVolley.this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        Log.e("url","-- Response --" +apiUrl);
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                apiUrl,null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.e("industry","-- Response --" +response.toString());
                Log.e("lenght","-- Response --" +response.length());

                if (response.length()==0){
                    pDialog.dismiss();
                }else{
                    try {
                        CartList = new ArrayList<>();
                        CartList = Arrays.asList(gson.fromJson(response.toString(), CartModel[].class));

                        mAdapter = new VideoListAdatper(GsonWithVolley.this);
                        recyclerView.setAdapter(mAdapter);

                        /*for(CartModel model: CartList){
                            // GOT THE OBJECT of PEOPLE

                        }*/
                        pDialog.dismiss();

                    } catch (Exception e) {

                    }
                }

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(final VolleyError error) {
                VolleyLog.e("Error", "Error: " + error.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        ll_no_data_found.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);

                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(GsonWithVolley.this);
                        dlgAlert.setMessage("");
                        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        dlgAlert.setCancelable(false);
                        dlgAlert.create().show();
                        pDialog.dismiss();
                    }
                });

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(GsonWithVolley.this);
        queue.add(jsonObjReq);

    }

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
            CartModel temp;
            temp = CartList.get(position);

            holder.tv_name.setText(temp.getProductName());
//            holder.tv_date.setText(temp.getVideo_name());
            Picasso.get().load(temp.getImage())
                    .placeholder(R.drawable.placeholder).into(holder.iv_image);

            holder.ll_play_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });


        }


        @Override
        public int getItemCount() {
            return CartList.size();
        }

    }
}
