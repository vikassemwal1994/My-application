package mufilbito.com.videoplayer.servicesexample.JobIntentService;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import mufilbito.com.videoplayer.R;

/**
 * All this fragment does is take in a number and start up the jobIntent service to complete the work.
 */
public class MainFragment extends Fragment {

    EditText et_input;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.myjob_intent_service_fragment, container, false);

        et_input = myView.findViewById(R.id.editText);
        myView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), MyJobIntentService.class);  //is any of that needed?  idk.
                //note, putExtra remembers type and I need this to be an integer.  so get an integer first.
                i.putExtra("times", Integer.parseInt(et_input.getText().toString()));  //should do error checking here!
                MyJobIntentService.enqueueWork(getContext(), i);
            }
        });
        return myView;
    }
}
