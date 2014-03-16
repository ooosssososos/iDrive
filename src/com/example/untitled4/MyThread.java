package com.example.untitled4;

import oliver.DrinkOrDriveWebService;
import oliver.Party;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ics on 15/03/14.
 */
public class MyThread extends Thread{
    public void run(){
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

    public List<Integer> run2(){
        List<Integer> code = new ArrayList<Integer>();
        while(true){
            DrinkOrDriveWebService wb = new DrinkOrDriveWebService();
            wb.parseParty();
            MyActivity.parties = wb.getParties();
            for(Party p: MyActivity.parties) {
                code.add(p.getCode());
            }
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
            return code;
        }
    }
}

