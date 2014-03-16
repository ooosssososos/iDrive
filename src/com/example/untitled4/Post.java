package com.example.untitled4;

import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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

    JSONObject party = null;
    @Override
    protected Long doInBackground(Instruction... i) {
        for(Instruction c : i){
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(c.a);

            String tmp = "";


            try {
                // Add your data
                StringEntity e = null;
                switch(c.dat){
                    case 0:
                        c.d = d.getText().toString();
                        e = new StringEntity(jsonResult(c.d).toString());

                        // Execute HTTP Post Request
                        httppost.addHeader("content-type", "application/json");
                        HttpResponse response0 = httpclient.execute(httppost);
                        System.out.println("Response : " + response0.getStatusLine() + ", data : ");
                        BufferedReader r = new BufferedReader(new InputStreamReader(response0.getEntity().getContent()));
                        tmp = r.readLine();

                        httppost.setEntity(e);
                        break;
                    case 1:
                        e = new StringEntity(jsonResult1().toString());

                        // Execute HTTP Post Request
                        httppost.addHeader("content-type", "application/json");
                        HttpResponse response1 = httpclient.execute(httppost);
                        System.out.println("Response : " + response1.getStatusLine() + ", data : ");
                        BufferedReader r1= new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
                        tmp = r1.readLine();

                        httppost.setEntity(e);
                        break;
                    case 2:
                        String basePartyUrl = "http://idrivedjango-env-qrs5vkxvvi.elasticbeanstalk.com/api/party/";
                        basePartyUrl.concat(d.toString());
                        System.out.println(basePartyUrl);
                        HttpGet httpget = new HttpGet(basePartyUrl);
                        HttpResponse response2 = httpclient.execute(httpget);

                        BufferedReader r2= new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));
                        tmp = r2.readLine();

                }


                switch(c.dat){
                    case 0:
                        JSONObject obj = new JSONObject(tmp);
                        MyActivity.ID = obj.getInt("id");
                        break;
                    case 1:

                        JSONObject z = new JSONObject(tmp);
                        MyActivity.code = z.getInt("code");
                        break;
                    case 2:
                        party = new JSONObject(tmp);
                        System.out.println(party);

                }

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
        int past_parties[] = {0};
        json = new JSONObject();
        json.put("cur_party",cur_party);
        json.put("past_parties",new JSONArray(past_parties));
        json.put("name",Name);
        System.out.println("Json: " + json);
        return json;
    }
    private JSONObject jsonResult1() throws JSONException {
        JSONObject json = null;
        int past_parties[] = {1};
        int active_member[] = {MyActivity.ID};
        json = new JSONObject();
        json.put("active_members",new JSONArray(active_member));
        System.out.println("Json1: " + json);
        return json;
    }
    private JSONObject jsonResult2(Integer Code) throws JSONException {
        JSONObject json = null;
        json = new JSONObject();
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