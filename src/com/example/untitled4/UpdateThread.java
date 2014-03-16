package com.example.untitled4;

/**
 * Created by ics on 15/03/14.
 */
public class UpdateThread implements Runnable{
    public void run(){
        while(true){

            MyActivity.t.update();

            try{
                Thread.sleep(1000);
            }catch(Exception e){

            }
        }
    }
}
