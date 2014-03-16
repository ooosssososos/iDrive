package com.example.untitled4;

import android.os.AsyncTask;
import oliver.DrinkOrDriveWebService;
import oliver.Party;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ics on 15/03/14.
 */
public class mthr extends AsyncTask<Void, Void, Void>{


    @Override
    protected Void doInBackground(Void... voids) {
        while(true){
            DrinkOrDriveWebService wb = new DrinkOrDriveWebService();
            wb.parseParty();
            MyActivity.parties = wb.getParties();
            wb.parsePartyUsers();
            MyActivity.users = wb.getPartyUsers();
            wb.parsePromotion();
            MyActivity.promos =  wb.getPromos();
            try{
                Thread.sleep(10000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

