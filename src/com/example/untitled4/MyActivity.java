package com.example.untitled4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    public static MyActivity t;
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
    MyThread m  = new MyThread();
    UpdateThread n = new UpdateThread();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        t = this;
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
                new Post((EditText)findViewById(R.id.editText)).execute(new Instruction("http://idrivedjango-env-qrs5vkxvvi.elasticbeanstalk.com/api/partyuser/",Username,1));
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
        m.run();
        n.run();
    }

    public void registerJoin(){
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = Integer.parseInt(((EditText)findViewById(R.id.Name)).getText().toString());
                new Post((EditText)findViewById(R.id.editText)).execute(new Instruction("http://idrivedjango-env-qrs5vkxvvi.elasticbeanstalk.com/api/partyuser/",Username,2));



                AlertDialog alertDialog = new AlertDialog.Builder(MyActivity.this).create();

                // Setting Dialog Title
                alertDialog.setTitle("Error");

                // Setting Dialog Message
                alertDialog.setMessage("Party Code Does Not Exist");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.logo);

                // Setting OK Button
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog closed
                        Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                    }
                });

                // Showing Alert Message
                alertDialog.show();

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
    public void update(){
        View v = getWindow().getDecorView().getRootView();
        if(v.getId() == R.layout.lobby){
            //Update Lobby

            final ArrayList<String> list = new ArrayList<String>();
            Party p = null;
            for(Party s : parties){
                if(s.getCode() == code){
                    p = s;
                    break;
                }
            }
            for (int i : p.listOfPartyUsersId) {
                for(PartyUser z : users){
                    if(z.getId() == i){
                        list.add(z.getName());
                        break;
                    }
                }
            }
            ((ListView)findViewById(R.id.listView)).setAdapter(new StableArrayAdapter(this, android.R.layout.simple_list_item_1,list ));
        }else if(v.getId() == R.layout.deals){
            //Update Deals

            final ArrayList<String> list = new ArrayList<String>();
            for(Promotion s : promos){
                list.add(s.getDescription());
            }
            ((ListView)findViewById(R.id.listView)).setAdapter(new StableArrayAdapter(this, android.R.layout.simple_list_item_1,list ));

        }
    }

}
class StableArrayAdapter extends ArrayAdapter<String> {

    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();


    public StableArrayAdapter(Context context, int textViewResourceId,
                              List<String> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        String item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}


