package com.example.untitled4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        ImageButton bu = (ImageButton)findViewById(R.id.imageButton);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inform the user the button has been clicked
                setContentView(R.layout.main);
            }
        });
    }
}
