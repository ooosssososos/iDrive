package com.example.untitled4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    public static String Username = "";
    public static int code = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        Button bu = (Button)findViewById(R.id.button);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = ((EditText)findViewById(R.id.editText)).getText().toString();;
                setContentView(R.layout.main);
                ((TextView)findViewById(R.id.textView)).setText(Username + "");
                register();
            }
        });

    }
    public void register(){
        ((ImageButton)findViewById(R.id.imageButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.lobby);
                ((TextView)findViewById(R.id.textView)).setText(Username + "'s ");
                registerLobby();
            }
        });
        ((ImageButton)findViewById(R.id.imageButton2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.join);
                registerJoin();
            }
        });
    }
    public void registerJoin(){
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.lobby);
                registerLobby();
                //Fill lobby with info TODO
            }
        });
    }
    public void registerLobby(){
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.modes);
            }
        });
    }
}
