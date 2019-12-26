package mufilbito.com.videoplayer.webview;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import mufilbito.com.videoplayer.R;

public class Webview extends AppCompatActivity {
    ProgressDialog progDailog;
    WebView webView_about;

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setContentView(R.layout.webview);
        String url ="http://13.127.184.195/apex/f?p=130:365::::::";
        progDailog = ProgressDialog.show(Webview.this, "Loading", "Please wait...", true);
        progDailog.setCancelable(false);

        webView_about = view.findViewById(R.id.webView);
        webView_about.getSettings().setJavaScriptEnabled(true);
        webView_about.getSettings().setLoadWithOverviewMode(true);
        webView_about.getSettings().setUseWideViewPort(true);
        webView_about.getSettings().setBuiltInZoomControls(true);

        webView_about.setWebViewClient(new CustomWebViewClient(url));

        webView_about.loadUrl(url);
    }

    public class CustomWebViewClient extends WebViewClient
    {
        String currentUrl="";
        public CustomWebViewClient(String currentUrl)
        {
            this.currentUrl = currentUrl;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            if (url.equals(currentUrl))
            {
                Log.e("url matches here","1111111");
                view.loadUrl(url);
            }
            return true;
        }
/*
        @Override
        public void onPageFinished(WebView view, String url)
        {
            super.onPageFinished(view, url);
            progDailog.dismiss();
        }*/
    }
}
