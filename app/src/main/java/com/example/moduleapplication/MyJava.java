package com.example.moduleapplication;

import android.os.Handler;
import android.os.Message;

import java.util.*;

public class MyJava {

    public static void main(String[] args) {
        Test.Companion.create();
        String str="&&&123&&";
        String[] strs=str.split("&");
        for (String s:strs){
            System.out.println(s+"-");
        }
        Map<Integer,String> map=new HashMap<>();
        System.out.println("1"=="1");
        Set set=new HashSet();

        new Thread(){
            @Override
            public void run() {
                this.getId();
            }
        }.start();

        System.out.println(Float.valueOf("0.00").intValue());
    }

    static void create(int... ints){

    }

    static class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }
}
