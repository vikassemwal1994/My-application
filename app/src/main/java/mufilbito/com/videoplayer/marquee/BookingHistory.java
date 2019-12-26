//package mufilbito.com.videoplayer.marquee;
//
//import android.app.Dialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.Paint;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RatingBar;
//import android.widget.TextView;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.VolleyLog;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import com.risingpole.R;
//import com.risingpole.UrlConstant;
//import com.risingpole.utility.CheckVolleyErrors;
//import com.risingpole.utility.ConvertDateFormat;
//import com.risingpole.utility.ShowToast;
//import com.squareup.picasso.Picasso;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class BookingHistory extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private ArrayList<OrderModel> planModelList;
//    private LinearLayout ll_no_data_found;
//    BidAdapter mAdapter;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.bid_screen);
//
//        Toolbar toolbarTop = (Toolbar) findViewById(R.id.tool);
//        ll_no_data_found = (LinearLayout) findViewById(R.id.ll_no_data_found);
//        recyclerView = (RecyclerView) findViewById(R.id.rv_cart);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
//        mTitle.setText("Order History");
//        ImageView mback = (ImageView) toolbarTop.findViewById(R.id.back_img);
//        mback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        SharedPreferences prefs = getSharedPreferences("RisingPole", MODE_PRIVATE);
//        getOrders(prefs.getString("CustomerCode", ""));
//
//
//    }
//
//
//    public void getOrders(String id) {
//        final ProgressDialog pDialog = new ProgressDialog(BookingHistory.this);
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//        Log.e("url", "-- Response --" + UrlConstant.getOrder + id);
//        JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
//                UrlConstant.getOrder + id, null, new Response.Listener<JSONArray>() {
//
//            @Override
//            public void onResponse(JSONArray response) {
//                Log.e("industry", "-- Response --" + response.toString());
//                Log.e("lenght", "-- Response --" + response.length());
//                if (response.length() == 0) {
//                    ll_no_data_found.setVisibility(View.VISIBLE);
//                    recyclerView.setVisibility(View.GONE);
//                    pDialog.dismiss();
//                } else {
//                    try {
//                        planModelList = new ArrayList<>();
//                        for (int i = 0; i < response.length(); i++) {
//                            OrderModel tempPlan = new OrderModel();
//                            JSONObject temp = response.getJSONObject(i);
////                            tempPlan.setServiceId(temp.getString("ServiceId"));
//                            tempPlan.setServiceName(temp.getString("ServiceName"));
//                            tempPlan.setDescription(temp.getString("ServiceDescription"));
//                            tempPlan.setServiceImage(temp.getString("ServiceImage"));
//                            tempPlan.setOrderId(temp.getString("OrderId"));
//                            tempPlan.setShippementId(temp.getString("ShippingId"));
//                            tempPlan.setShippingMobileNo(temp.getString("ShippingMobileNo"));
//                            tempPlan.setShippingAddress(temp.getString("ShippingAddress"));
//                            tempPlan.setShippementId(temp.getString("ShippingCharges"));
//                            tempPlan.setShippingEmailId(temp.getString("ShippingEmailId"));
//                            tempPlan.setOrderBy(temp.getString("OrderBy"));
//                            tempPlan.setShippingName(temp.getString("ShippingName"));
//                            tempPlan.setShippingPincode(temp.getString("ShippingPincode"));
//                            tempPlan.setShippingState(temp.getString("ShippingState"));
//                            tempPlan.setPrice(temp.getString("Price"));
//                            tempPlan.setSubTotal(String.valueOf(temp.get("SubTotal")));
//                            tempPlan.setVendorName(temp.getString("VendorName"));
//                            tempPlan.setVendorMobile(temp.getString("VendorMobile"));
////                            tempPlan.setPaymentStatus(temp.getString("PaymentType"));
////                            tempPlan.setPaymentStatus(temp.getString("PaymentStatus"));
//                            tempPlan.setOrderStatus(temp.getString("OrderStatus"));
//                            tempPlan.setCreatedDate(temp.getString("CreatedDate"));
//                            tempPlan.setIndustryId(String.valueOf(temp.get("IndustryId")));
//                            tempPlan.setSubindustryId(String.valueOf(temp.get("SubindustryId")));
//                            tempPlan.setVendorCode(temp.getString("VendorCode"));
//                            tempPlan.setIsfavourite(String.valueOf(temp.get("Isfavourite")));
//                            tempPlan.setIsRating(String.valueOf(temp.get("IsRating")));
//                            tempPlan.setExtraServiceName(temp.getString("ExtraServiceName"));
//                            tempPlan.setExtraServicePrice(temp.getString("ExtraServicePrice"));
//
//                            /*JSONObject temp = response.getJSONObject(i);
//                            tempPlan.setServiceId(temp.getString("ServiceId"));
//                            tempPlan.setServiceName(temp.getString("ServiceName"));
//                            tempPlan.setDescription(temp.getString("Description"));
//                            tempPlan.setServiceImage(temp.getString("ServiceImage"));
//                            tempPlan.setOrderId(temp.getString("OrderId"));
//                            tempPlan.setShippementId(temp.getString("ShippingId"));
//                            tempPlan.setShippingMobileNo(temp.getString("ShippingMobileNo"));
//                            tempPlan.setShippingAddress(temp.getString("ShippingAddress"));
//                            tempPlan.setShippementId(temp.getString("ShippingCharges"));
//                            tempPlan.setShippingEmailId(temp.getString("ShippingEmailId"));
//                            tempPlan.setOrderBy(temp.getString("OrderBy"));
//                            tempPlan.setShippingName(temp.getString("ShippingName"));
//                            tempPlan.setShippingPincode(temp.getString("ShippingPincode"));
//                            tempPlan.setShippingState(temp.getString("ShippingState"));
//                            tempPlan.setPrice(String.valueOf(temp.get("Amount")));
//                            tempPlan.setSubTotal(temp.getString("SubTotal"));
//                            tempPlan.setPaymentStatus(temp.getString("PaymentType"));
//                            tempPlan.setPaymentStatus(temp.getString("PaymentStatus"));
//                            tempPlan.setOrderStatus(temp.getString("OrderStatus"));
//                            tempPlan.setCreatedDate(temp.getString("CreatedDate"));
//                            tempPlan.setIndustryId(temp.getString("Industry"));
//                            tempPlan.setSubindustryId(temp.getString("Subindustry"));
//                            tempPlan.setVendorCode(temp.getString("VendorCode"));
//                            tempPlan.setIsfavourite(String.valueOf(temp.get("Isfavourite")));
//                            tempPlan.setIsRating(String.valueOf(temp.get("IsRating")));
//                            tempPlan.setExtraServiceName(temp.getString("ExtraServiceName"));
//                            tempPlan.setExtraServicePrice(temp.getString("ExtraServicePrice"));*/
//
//                            planModelList.add(tempPlan);
//
//                        }
//                        mAdapter = new BidAdapter(BookingHistory.this);
//                        recyclerView.setAdapter(mAdapter);
//                        pDialog.dismiss();
//
//                    } catch (Exception e) {
//
//                    }
//                }
//
//            }
//
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(final VolleyError error) {
//                VolleyLog.e("Error", "Error: " + error.getMessage());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ll_no_data_found.setVisibility(View.VISIBLE);
//                        recyclerView.setVisibility(View.GONE);
//
//                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(BookingHistory.this);
//                        dlgAlert.setMessage(CheckVolleyErrors.volleyErrors(error));
//                        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                            }
//                        });
//                        dlgAlert.setCancelable(false);
//                        dlgAlert.create().show();
//                        pDialog.dismiss();
//                    }
//                });
//
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                return headers;
//            }
//        };
//        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
//                10000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        RequestQueue queue = Volley.newRequestQueue(BookingHistory.this);
//        queue.add(jsonObjReq);
//
//    }
//
//    public class BidAdapter extends RecyclerView.Adapter<BidAdapter.MyViewHolder> {
//
//        Context context;
//
//        public BidAdapter(Context context) {
//            this.context = context;
//        }
//
//        public class MyViewHolder extends RecyclerView.ViewHolder {
//            public TextView tv_service_name, tv_Actual_Amount, tv_bid_amount, tv_description, tv_status
//                    , tv_previous_amt, tv_order_id, tv_order_detail, tv_sub_insdustry, tv_extraService
//                    , tv_extra_Amount, tv_Total_Amount, tv_cancel_order;
//            public LinearLayout ll_main, ll_extraService, ll_extraAmount, ll_TotalAmount;
//            private ImageView iv_image, img_fav, ratingBar3;
//
//            public MyViewHolder(View view) {
//                super(view);
//                tv_service_name = (TextView) view.findViewById(R.id.tv_service_name);
//                tv_Actual_Amount = (TextView) view.findViewById(R.id.tv_Actual_Amount);
//                tv_bid_amount = (TextView) view.findViewById(R.id.tv_bid_amount);
//                tv_description = (TextView) view.findViewById(R.id.tv_description);
//                tv_status = (TextView) view.findViewById(R.id.tv_status);
//                tv_cancel_order = (TextView) view.findViewById(R.id.tv_cancel_order);
//                tv_previous_amt = (TextView) view.findViewById(R.id.tv_previous_amt);
//                tv_order_id = (TextView) view.findViewById(R.id.tv_order_id);
//                tv_order_detail = (TextView) view.findViewById(R.id.tv_order_detail);
//                tv_sub_insdustry = (TextView) view.findViewById(R.id.tv_sub_insdustry);
//                tv_extraService = (TextView) view.findViewById(R.id.tv_extraService);
//                tv_extra_Amount = (TextView) view.findViewById(R.id.tv_extra_Amount);
//                tv_Total_Amount = (TextView) view.findViewById(R.id.tv_Total_Amount);
//                ll_extraService = (LinearLayout) view.findViewById(R.id.ll_extraService);
//                ll_extraAmount = (LinearLayout) view.findViewById(R.id.ll_extraAmount);
//                ll_TotalAmount = (LinearLayout) view.findViewById(R.id.ll_TotalAmount);
//                iv_image = (ImageView) view.findViewById(R.id.iv_image);
//                img_fav = (ImageView) view.findViewById(R.id.img_rating);
//                ratingBar3 = (ImageView) view.findViewById(R.id.ratingBar3);
//
//
//            }
//        }
//
//        @Override
//        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View itemView = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.booking_history_single_row, parent, false);
//            return new MyViewHolder(itemView);
//
//        }
//
//        @Override
//        public void onBindViewHolder(final MyViewHolder holder, final int position) {
//            holder.tv_service_name.setText(planModelList.get(position).getServiceName());
//            holder.tv_description.setText(planModelList.get(position).getPaymentType());    //
//            holder.tv_description.setVisibility(View.GONE);
//            holder.tv_status.setText(planModelList.get(position).getOrderStatus());
//            holder.tv_order_id.setText(planModelList.get(position).getOrderId());
//            holder.tv_sub_insdustry.setText(ConvertDateFormat.formatDate(planModelList.get(position).getCreatedDate())
//                    + " " + "(" + ConvertDateFormat.formatTime(planModelList.get(position).getCreatedDate()) + ")");
//
//            holder.tv_previous_amt.setPaintFlags(holder.tv_previous_amt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//            holder.tv_Actual_Amount.setText(getResources().getString(R.string.rupeeSymbol_green)
//                    + planModelList.get(position).getPrice());
//            Picasso.get().load(planModelList.get(position).getServiceImage())
//                    .placeholder(R.drawable.image_not_available).into(holder.iv_image);
//
//            holder.tv_order_detail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(BookingHistory.this, OrderDetails.class);
//                    intent.putExtra("value", planModelList.get(position));
//                    startActivity(intent);
//
//                }
//            });
//
//            if (planModelList.get(position).getOrderStatus().equals(UrlConstant.order_status_delivered)) {
//                holder.ratingBar3.setVisibility(View.VISIBLE);
//                holder.img_fav.setVisibility(View.VISIBLE);
//                if (planModelList.get(position).getIsfavourite().equals("1")) {
//                    holder.img_fav.setImageDrawable(getResources().getDrawable(R.drawable.ic_fav_fill));
//                } else {
//                    holder.img_fav.setImageDrawable(getResources().getDrawable(R.drawable.ic_fav_blank));
//                }
//
//                if (planModelList.get(position).getIsRating().equals("0")) {
//                    holder.ratingBar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_white));
//                } else {
//                    holder.ratingBar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star));
//                }
//
//                if(planModelList.get(position).getExtraServiceName().equals("")){
//
//                }else{
//                    holder.ll_extraService.setVisibility(View.VISIBLE);
//                    holder.ll_extraAmount.setVisibility(View.VISIBLE);
//                    holder.ll_TotalAmount.setVisibility(View.VISIBLE);
//                    holder.tv_extraService.setText(planModelList.get(position).getExtraServiceName());
//                    holder.tv_extra_Amount.setText(getResources().getString(R.string.rupeeSymbol_green)
//                            +planModelList.get(position).getExtraServicePrice());
//                    int total = Math.round(Float.parseFloat(planModelList.get(position).getPrice()))
//                            + Integer.parseInt(planModelList.get(position).getExtraServicePrice());
//                    holder.tv_Total_Amount.setText(getResources().getString(R.string.rupeeSymbol_green)
//                            +String.valueOf(total));
//
//                }
//
//            }else if (planModelList.get(position).getOrderStatus().equals(UrlConstant.order_status_pending)){
//                holder.tv_cancel_order.setVisibility(View.VISIBLE);
//            }else {
//                holder.tv_cancel_order.setVisibility(View.GONE);
//            }
//
//            holder.tv_cancel_order.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    cacelOrder(position);
//                }
//            });
//
//            holder.img_fav.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (planModelList.get(position).getIsfavourite().equals("1")) {
//                        new ShowToast(BookingHistory.this).showToast("Already added in Favourite");
//                    } else {
//                        addToFavourite(position);
//                    }
////                    holder.img_fav.setImageDrawable(getResources().getDrawable(R.drawable.ic_fav_fill));
//
//                }
//            });
//
//            holder.ratingBar3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (planModelList.get(position).getIsRating().equals("0")) {
//                        alertRating(position);
//                    } else {
//                        new ShowToast(BookingHistory.this).showToast("Rating already given");
//                    }
//
//                }
//            });
//
//
//        }
//
//
//        @Override
//        public int getItemCount() {
//            return planModelList.size();
//        }
//
//    }
//
//    private void alertRating(final int position) {
//        final Dialog dialog = new Dialog(BookingHistory.this);
//        // Include dialog.xml file
//        dialog.setContentView(R.layout.dialogue_rating);
//        dialog.setCancelable(true);
//        // set values for custom dialog components - text, image and button
//        TextView tv_heading = dialog.findViewById(R.id.tv_heading);
//        TextView tv_message = dialog.findViewById(R.id.tv_message);
//        TextView tv_submit = dialog.findViewById(R.id.tv_submit);
//        TextView tv_cancel = dialog.findViewById(R.id.tv_cancel);
//        final EditText et_review = dialog.findViewById(R.id.et_review);
//        final RatingBar ratingBar3 = dialog.findViewById(R.id.ratingBar3);
//
//        tv_submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                float ratingFloat = ratingBar3.getRating();
//                if (ratingFloat > 0.5) {
//                    String review = et_review.getText().toString();
//                    setRating(ratingFloat, review, position);
//                    dialog.dismiss();
//                } else {
//                    new ShowToast(BookingHistory.this).showToast("Please add rating before submit");
//                }
//
//            }
//        });
//        tv_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//
//    }
//
//
//    private void addToFavourite(final int postition) {
//        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                switch (which) {
//                    case DialogInterface.BUTTON_POSITIVE:
//                        //Yes button clicked
//                        setFavourite(postition);
//                        dialog.dismiss();
//                        break;
//
//                    case DialogInterface.BUTTON_NEGATIVE:
//                        //No button clicked
//                        dialog.dismiss();
//                        break;
//                }
//            }
//        };
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(BookingHistory.this);
//        builder.setMessage("Are you sure, you want to add this partner to your favourite?")
//                .setPositiveButton("Yes", dialogClickListener)
//                .setNegativeButton("No", dialogClickListener).show();
//
//    }
//
//    public void setRating(float rating, String review, final int position) {
//        // Tag used to cancel the request
//        String tag_json_obj = "json_obj_req";
//        JSONObject object = new JSONObject();
//        try {
//            JSONObject mainObject = new JSONObject();
//            JSONArray array = new JSONArray();
//
//            SharedPreferences prefs = getSharedPreferences("RisingPole", MODE_PRIVATE);
//            object.put("VendorCode", planModelList.get(position).getVendorCode());
//            object.put("CustomerCode", prefs.getString("CustomerCode", ""));
//            object.put("OrderId", planModelList.get(position).getOrderId());
//            object.put("Rating", String.valueOf(rating));
//            object.put("Review", review);
//
//            Log.e("Requst add industry- ", "vendor ---" + object.toString());
//
//        } catch (Exception e) {
//
//        }
//
//        final ProgressDialog pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//        Log.e("url --- ", "url ---" + UrlConstant.do_rating_to_patner);
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
//                UrlConstant.do_rating_to_patner, object,
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        planModelList.get(position).setIsRating("1");
//                        mAdapter.notifyDataSetChanged();
//                        Log.e("Response --- ", "Response ---" + response.toString());
//                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(BookingHistory.this);
//                        try {
//                            dlgAlert.setMessage(response.getString("Message"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
////                                getCartValue();
//                            }
//                        });
//                        dlgAlert.setCancelable(false);
//                        dlgAlert.create().show();
//                        pDialog.dismiss();
//
////                        cartModelList.remove(position);
////                        mAdapter.notifyDataSetChanged();
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(final VolleyError error) {
//                VolleyLog.e("", "Error: " + error.getMessage());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(BookingHistory.this);
//                        dlgAlert.setMessage(CheckVolleyErrors.volleyErrors(error));
//                        dlgAlert.setPositiveButton("OK", null);
//                        dlgAlert.setCancelable(false);
//                        dlgAlert.create().show();
//                        pDialog.dismiss();
//                    }
//                });
//                pDialog.hide();
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                return headers;
//            }
//        };
//        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
//                10000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(jsonObjReq);
//
//    }
//
//    public void cacelOrder(final int position) {
//        // Tag used to cancel the request
//        SharedPreferences prefs = getSharedPreferences("RisingPole", MODE_PRIVATE);
//        final ProgressDialog pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//        Log.e("url --- ", "url ---" + UrlConstant.cancel_Order + prefs.getString("CustomerCode", "")
//                + UrlConstant.cancel_Order2 + planModelList.get(position).getOrderId());
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
//                UrlConstant.cancel_Order + prefs.getString("CustomerCode", "")
//                        + UrlConstant.cancel_Order2 + planModelList.get(position).getOrderId(), null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.e("Response --- ", "Response ---" + response.toString());
//                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(BookingHistory.this);
//                        try {
//                            dlgAlert.setMessage(response.getString("Message"));
//                            if (response.getString("Status").equals("true")){
//                                planModelList.get(position).setOrderStatus(UrlConstant.order_status_canceled);
//                                mAdapter.notifyDataSetChanged();
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
////                                getCartValue();
//                            }
//                        });
//                        dlgAlert.setCancelable(false);
//                        dlgAlert.create().show();
//                        pDialog.dismiss();
//
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(final VolleyError error) {
//                VolleyLog.e("", "Error: " + error.getMessage());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(BookingHistory.this);
//                        dlgAlert.setMessage(CheckVolleyErrors.volleyErrors(error));
//                        dlgAlert.setPositiveButton("OK", null);
//                        dlgAlert.setCancelable(false);
//                        dlgAlert.create().show();
//                        pDialog.dismiss();
//                    }
//                });
//                pDialog.hide();
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                return headers;
//            }
//        };
//        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
//                10000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(jsonObjReq);
//
//    }
//
//    public void setFavourite(final int position) {
//        // Tag used to cancel the request
//        String tag_json_obj = "json_obj_req";
//        JSONObject object = new JSONObject();
//        try {
//            JSONObject mainObject = new JSONObject();
//            JSONArray array = new JSONArray();
//
//            SharedPreferences prefs = getSharedPreferences("RisingPole", MODE_PRIVATE);
//
//            object.put("Industry", planModelList.get(position).getIndustryId());
//            object.put("SubIndustry", planModelList.get(position).getSubindustryId());
//            object.put("ServiceName", planModelList.get(position).getServiceName());
//            object.put("CustomerCode", prefs.getString("CustomerCode", ""));
//            object.put("ServiceId", planModelList.get(position).getServiceId());
//            object.put("VendorCode", planModelList.get(position).getVendorCode());
//            object.put("OrderId", planModelList.get(position).getOrderId());
//
//            Log.e("Requst add industry- ", "vendor ---" + object.toString());
//
//        } catch (Exception e) {
//
//        }
//
//        final ProgressDialog pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//        Log.e("url --- ", "url ---" + UrlConstant.set_create_favourite_vendor);
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
//                UrlConstant.set_create_favourite_vendor, object,
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        planModelList.get(position).setIsfavourite("1");
//                        mAdapter.notifyDataSetChanged();
//                        Log.e("Response --- ", "Response ---" + response.toString());
//                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(BookingHistory.this);
//                        try {
//                            dlgAlert.setMessage(response.getString("Message"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
////                                getCartValue();
//                            }
//                        });
//                        dlgAlert.setCancelable(false);
//                        dlgAlert.create().show();
//                        pDialog.dismiss();
//
////                        cartModelList.remove(position);
////                        mAdapter.notifyDataSetChanged();
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(final VolleyError error) {
//                VolleyLog.e("", "Error: " + error.getMessage());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(BookingHistory.this);
//                        dlgAlert.setMessage(CheckVolleyErrors.volleyErrors(error));
//                        dlgAlert.setPositiveButton("OK", null);
//                        dlgAlert.setCancelable(false);
//                        dlgAlert.create().show();
//                        pDialog.dismiss();
//                    }
//                });
//                pDialog.hide();
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                return headers;
//            }
//        };
//        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
//                10000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(jsonObjReq);
//
//    }
//
//}
