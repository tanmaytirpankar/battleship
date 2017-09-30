package com.example.tanmay.myapp;


public class GridStatus {
    public int[] thisplayer=new int[25];
    public int[] otherplayer=new int[25];
    public int[] myships=new int[5];
    public int[] hisships=new int[5];
    public GridStatus(int[] myships,ImageAdapter imgadapter)
    {
        for (int i = 0; i < 25; i++) {
            this.thisplayer[i]=-1;
            this.otherplayer[i]=-1;
        }
        for (int i = 0; i < 5; i++) {
            this.myships[i] = myships[i];
            this.hisships[i]=myships[i];
        }
    }
}
