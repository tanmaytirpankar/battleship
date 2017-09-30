package com.example.tanmay.myapp;

/**
 * Created by tanma on 9/28/2017.
 */

public class PositionData {
    public  int[] ships=new int[5];
    public PositionData()
    {

    }
    public PositionData(int[] ships)
    {
        for (int i = 0; i < 5; i++) {
            this.ships[i]=ships[i];
        }
    }
}
