package com.example.tanmay.myapp;


public class GridStatus {
    public int[] thisplayer=new int[25];
    public int[] otherplayer=new int[25];
    public int[] myships=new int[5];
    public int[] hisships=new int[5];
    public int[] mydestroyedships=new int[5];
    public int[] hisdestroyedships=new int[5];
    public GridStatus(int[] myships,int[] hisships)
    {
        for (int i = 0; i < 25; i++) {
            this.thisplayer[i]=-1;
            this.otherplayer[i]=-1;
        }
        for (int i = 0; i < 5; i++) {
            this.myships[i] = myships[i];
            this.hisships[i]=hisships[i];
            mydestroyedships[i]=-1;
            hisdestroyedships[i]=-1;
        }

    }
    public void destroymyship(int position)
    {
        for (int i = 0; i < 5; i++) {
            if(myships[i]==position)
                mydestroyedships[i]=1;
        }
    }
    public void destroyhisship(int position)
    {
        for (int i = 0; i < 5; i++) {
            if(hisships[i]==position)
                hisdestroyedships[i]=1;
        }
    }
    public boolean checkforwin(int[] hisdestroyedships)
    {
        if (hisdestroyedships[0]==1 && hisdestroyedships[1]==1 && hisdestroyedships[2]==1 && hisdestroyedships[3]==1 && hisdestroyedships[4]==1)
            return true;
        else
            return false;
    }
    public boolean checkforlose(int[] mydestroyedships)
    {
        if (mydestroyedships[0]==1 && mydestroyedships[1]==1 && mydestroyedships[2]==1 && mydestroyedships[3]==1 && mydestroyedships[4]==1)
            return true;
        else
            return false;
    }
}
