package mufilbito.com.videoplayer.Utility;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ShowToast {

    Context context;

    public ShowToast(Context context){
        this.context = context;
    }

    public void showToast(String str) {
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

}
