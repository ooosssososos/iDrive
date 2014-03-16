package com.example.untitled4;

import android.os.AsyncTask;

/**
 * Created by ics on 15/03/14.
 */
class temp extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        while(true){

            MyActivity.t.update();

            try{
                Thread.sleep(1000);
            }catch(Exception e){

            }
        }
    }
}
