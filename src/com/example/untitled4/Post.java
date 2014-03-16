package com.example.untitled4;

import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
        HttpResponse response;

        c.d = d.getText().toString();
        try {
            // Add your data
            JSONObject temp = jsonResult(c.d);

            HttpPost post = new HttpPost(("http://idrivedjango-env-qrs5vkxvvi.elasticbeanstalk.com/api/partyuser/"));
            StringEntity se = new StringEntity(temp.toString());
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);
            response = httpclient.execute(post);

                    /*Checking response */
            if(response!=null){
                InputStream in = response.getEntity().getContent(); //Get the data in the entity
            }
            // Execute HTTP Post Request
            //HttpResponse response = httpclient.execute(httppost);

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
        JSONObject json = new JSONObject();
        int[] past_parties = new int[1];
        int[] friends = new int[1];
        json.put("cur_party", 1);
        json.put("past_parties", java.util.Arrays.toString(past_parties));
        json.put("friends", java.util.Arrays.toString(friends));
        json.put("name", Name);
        System.out.println(json.toString());
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