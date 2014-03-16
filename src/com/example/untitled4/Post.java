package com.example.untitled4;

import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
        c.d = d.getText().toString();
        try {
            // Add your data
            jsonResult(c.d);

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

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
        json = new JSONObject("{\"" + "name" + "\":" + "\"" + Name+ "\"" + "}");
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
