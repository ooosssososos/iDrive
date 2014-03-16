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
import org.apache.http.util.EntityUtils;
import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
                StringEntity e = null;
                switch(c.dat){
                    case 0:
                        e = new StringEntity(jsonResult(c.d).toString());
                        break;
                    case 1:
                        e = new StringEntity(jsonResult1().toString());
                        break;
                }
                httppost.setEntity(e);


                // Execute HTTP Post Request
                httppost.addHeader("content-type", "application/json");
                HttpResponse response = httpclient.execute(httppost);
                System.out.println("Response : " + response.getEntity().getContent().toString());

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
    private JSONObject jsonResult1() throws JSONException {
        JSONObject json = null;
        int past_parties[] = {1};
        int active_member[] = {MyActivity.ID};
        json = new JSONObject();
        json.put("active_members",new JSONArray(active_member));
        json.put("past_members", new JSONArray(past_parties));
        System.out.println("Json: " + json);
        return json;
    }
    private JSONObject jsonResult2(String Name) throws JSONException {
        JSONObject json = null;
        int past_parties[] = {1};
        int active_member[] = {MyActivity.ID};
        json = new JSONObject();
        json.put("active_members",new JSONArray(active_member));
        json.put("past_members", new JSONArray(past_parties));
        System.out.println("Json: " + json);
        return json;
    }
}
class Instruction{
    String a;
    String d;
    int dat;
    public Instruction(String URL, String Data, int in){
        a = URL;
        d = Data;
        dat = in;
    }
}