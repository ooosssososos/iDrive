package com.example.untitled4;

import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by ics on 15/03/14.
 */
public class Post extends AsyncTask<Instruction, Integer, Long>{
    EditText d;
    public Post(EditText v){
        d = v;
    }
    @Override
    protected Long doInBackground(Instruction... i) {
        for(Instruction c : i){
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(c.a);
            c.d = d.getText().toString();
            try {
                // Add your data
                StringEntity e = new StringEntity(jsonResult(c.d).toString());
                httppost.setEntity(e);


                // Execute HTTP Post Request
                httppost.addHeader("content-type", "application/json");
                HttpResponse response = httpclient.execute(httppost);
                System.out.println("Status :" + response.getStatusLine());

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return 1L;
    }


    private JSONObject jsonResult(String Name) throws JSONException {
        JSONObject json = null;
        Object cur_party = JSONObject.NULL;
        int past_parties[] = {1};
        int friends[] = {1};
        json = new JSONObject();
        json.put("cur_party",cur_party);
        json.put("past_parties",new JSONArray(past_parties));
        json.put("name",Name);
        json.put("friends",new JSONArray(friends));
        System.out.println("Json: " + json);
        return json;
    }
}
class Instruction{
    String a;
    String d;
    public Instruction(String URL, String Data){
        a = URL;
        d = Data;
    }
}