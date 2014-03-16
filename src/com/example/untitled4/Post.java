package com.example.untitled4;

import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by ics on 15/03/14.
 */
public class Post extends AsyncTask<Instruction, Integer, Long>{
    EditText d;
    public Post(EditText v){
        d = v;
    }
    public Post(){}

    public JSONObject party = null;
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
                        httppost.setEntity(e);
                        HttpResponse response0 = httpclient.execute(httppost);
                        System.out.println("Response : " + response0.getStatusLine() + ", data : ");
                        BufferedReader r = new BufferedReader(new InputStreamReader(response0.getEntity().getContent()));
                        tmp = r.readLine();


                        break;
                    case 1:
                        e = new StringEntity(jsonResult1().toString());

                        // Execute HTTP Post Request
                        httppost.addHeader("content-type", "application/json");

                        httppost.setEntity(e);
                        HttpResponse response1 = httpclient.execute(httppost);
                        System.out.println("Response : " + response1.getStatusLine() + ", data : ");
                        BufferedReader r1= new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
                        tmp = r1.readLine();

                        break;
                    case 2:








                        String basePartyUrl = "http://idrivedjango-env-qrs5vkxvvi.elasticbeanstalk.com/api/party/";
                        basePartyUrl+="?code=";
                        basePartyUrl+=MyActivity.code;

                        System.out.println("hello: " + basePartyUrl);
                        HttpGet httpget = new HttpGet(basePartyUrl);
                        HttpResponse response2 = httpclient.execute(httpget);

                        BufferedReader r2= new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));
                        tmp = r2.readLine();
                        System.out.println("msg2: " + tmp);
                        break;

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
                        try{
                        MyActivity.partyJSONObject = new JSONObject(tmp.substring(1,tmp.length()-1));
                            JSONArray jarray = (JSONArray)MyActivity.partyJSONObject.get("active_members");
                            int[] zf = new int[jarray.length()+1];
                            for (int fd = 0; fd< jarray.length(); ++fd) {
                                zf[fd] = jarray.optInt(fd);
                            }
                            zf[zf.length-1] = MyActivity.ID;
                            JSONArray abbb = new JSONArray(zf);
                            MyActivity.partyJSONObject.put("active_members", abbb);
                            String url = c.a+MyActivity.partyJSONObject.get("id") + "/";
                            HttpPut putRequest = new HttpPut(url);
                            putRequest.addHeader("content-type", "application/json");
                            StringEntity enti = new StringEntity(MyActivity.partyJSONObject.toString());

                            System.out.println("sdafadadd: " + MyActivity.partyJSONObject.toString() + "      " + Arrays.toString(zf) + "               " + url);
                            putRequest.setEntity(enti);
                            HttpResponse res = httpclient.execute(putRequest);
                            

                        }catch(Exception fe){
                            fe.printStackTrace();
                            MyActivity.partyJSONObject = null;
                        }
                        MyActivity.status = 1;
                        MyActivity.a=true;
                        System.out.println("irfe2: " + MyActivity.partyJSONObject);
                        break;
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
        json = new JSONObject();
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