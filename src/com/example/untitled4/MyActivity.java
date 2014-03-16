package com.example.untitled4;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import oliver.Party;
import oliver.PartyUser;
import oliver.Promotion;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    public static String Username = "";
    public static int code = 0;
    public static int ID = -1;
    public static List<Promotion> promos;
    public static List<Party> parties;
    public static List<PartyUser> users;
    public static int party = -1;
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";
    private static String KEY_UID = "uid";
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        Button bu = (Button)findViewById(R.id.button);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = ((EditText)findViewById(R.id.editText)).getText().toString();
                new Post((EditText)findViewById(R.id.editText)).execute(new Instruction("http://idrivedjango-env-qrs5vkxvvi.elasticbeanstalk.com/api/partyuser/",Username,0));
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
