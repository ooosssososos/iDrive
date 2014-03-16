package com.example.untitled4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import oliver.DrinkOrDriveWebService;
import oliver.Party;
import oliver.PartyUser;
import oliver.Promotion;
import org.json.JSONException;
import org.json.JSONObject;

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
    public static String codeString = "";
    public static int ID = -1;
    public static List<Promotion> promos;
    public static int status = -1;
    public static List<Party> parties;
    public static boolean a = false;
    public static JSONObject partyJSONObject;
    public static Party partyRealObject;
    public static List<PartyUser> users;
    public static int party = -1;
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";
    private static String KEY_UID = "uid";
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
        new MyThread().start();

    }


    public void register(){
        ((ImageButton)findViewById(R.id.imageButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Post((EditText)findViewById(R.id.editText)).execute(new Instruction("http://idrivedjango-env-qrs5vkxvvi.elasticbeanstalk.com/api/party/",Username,1));
                setContentView(R.layout.lobby);
                ((TextView)findViewById(R.id.textView)).setText(Username + "'s ");
                registerLobby();
                status = 1;
            }
        });
        ((ImageButton)findViewById(R.id.imageButton2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.join);
                registerJoin();
            }
        });
        new UpdateThread().start();
    }

    public void registerJoin(){
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = Integer.parseInt(((EditText) findViewById(R.id.Name)).getText().toString());
                codeString = ((EditText)findViewById(R.id.Name)).getText().toString();
                Post post = new Post();
                post.execute(new Instruction("http://idrivedjango-env-qrs5vkxvvi.elasticbeanstalk.com/api/party/",codeString,2)); 
                while(a == false);
                System.out.println("irfe: " + partyJSONObject);
                try {

                    int partyId = partyJSONObject.getInt("id");

                    int barId = partyJSONObject.getInt("cur_checkin_bar");

                    boolean isClosed = partyJSONObject.getBoolean("closed");
                    int code = partyJSONObject.getInt("code");
                    partyRealObject = new Party(partyId, barId, isClosed, code);
                    System.out.println("irfe: " + partyId + "check + " + partyRealObject);

                } catch(Exception je) {
                    partyRealObject = null;
                }

                if (partyJSONObject == null){


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
                } else {
                    setContentView(R.layout.lobby);
                    registerLobby();

                }
                //Fill lobby with info TODO


            }
        });
    }
    public void registerLobby(){
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = -1;
                setContentView(R.layout.modes);
                registerModes();
            }
        });
    }
    public void registerModes(){
        ((Button)findViewById(R.id.Volunteer)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 2;
                setContentView(R.layout.deals);
                ((TextView)findViewById(R.id.textView)).setText(MyActivity.Username);



            }
        });
    }
    public void update(){
        System.out.println("UPDATED");
        if(MyActivity.status == 1){
            MyActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    ((TextView) findViewById(R.id.textView4)).setText(MyActivity.code + "");
                    final ArrayList<String> list = new ArrayList<String>();
                    Party p = null;
                    for (Party s : parties) {
                        if (s.getCode() == code) {
                            p = s;
                            break;
                        }
                    }
                    try{
                    for (int i : p.listOfPartyUsersId) {
                        for (PartyUser z : users) {
                            if (z.getId() == i) {
                                list.add(z.getName());
                                break;
                            }
                        }
                    }
                    }catch(NullPointerException e){
                        e.printStackTrace();
                    }
                    ((ListView) findViewById(R.id.listView)).setAdapter(new StableArrayAdapter(MyActivity.this, android.R.layout.simple_list_item_1, list));
                }
            });
            //Update Lobby


        }else if(MyActivity.status == 2){
            //Update Deals
            MyActivity.this.runOnUiThread(new Runnable() {
                public void run() {
            final ArrayList<String> list = new ArrayList<String>();
            for(Promotion s : promos){
                list.add(s.getDescription());
            }
            ((ListView)findViewById(R.id.listView)).setAdapter(new StableArrayAdapter(MyActivity.this, android.R.layout.simple_list_item_1,list ));
                }
            });
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
class UpdateThread extends Thread {


    @Override
    public void run() {
        while(true){
            MyActivity.t.update();

            try{
                Thread.sleep(1000);
            }catch(Exception e){

            }
        }
    }
}
class MyThread extends Thread{



    @Override
    public void run() {
        while(true){
            DrinkOrDriveWebService wb = new DrinkOrDriveWebService();
            wb.parseParty();
            MyActivity.parties = wb.getParties();
            wb.parsePartyUsers();
            MyActivity.users = wb.getPartyUsers();
            wb.parsePromotion();
            MyActivity.promos =  wb.getPromos();
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}


