package com.example.kushbanbah1.comsiia;

public class Time_Management {

    private int[][] MT = new int[4][2];
    private int[][] Wed = new int[4][2];
    private int[][] Thur = new int[4][2];
   private int[][] Fri = new int[4][2];



    public void setup()
    {
        MT[0][0] = 9;
        MT[0][1] = 30;
        MT[1][0] = 11;
        MT[1][1] = 5;
        MT[2][0] = 12;
        MT[2][1] = 40;
        MT[3][0] = 14;
        MT[3][1] = 40;

        Wed[0][0] = 9;
        Wed[0][1] = 15;
        Wed[1][0] = 11;
        Wed[1][1] = 25;
        Wed[2][0] = 12;
        Wed[2][1] = 45;
        Wed[3][0] = 14;
        Wed[3][1] = 40;

        Thur[0][0] = 10;
        Thur[0][1] = 0;
        Thur[1][0] = 11;
        Thur[1][1] = 25;
        Thur[2][0] = 12;
        Thur[2][1] = 50;
        Thur[3][0] = 14;
        Thur[3][1] = 40;

        Fri[0][0] = 9;
        Fri[0][1] = 20;
        Fri[1][0] = 10;
        Fri[1][1] = 45;
        Fri[2][0] = 12;
        Fri[2][1] = 10;
        Fri[3][0] = 14;
        Fri[3][1] = 0;

    }

    public int[][] getMT()
    {
        setup();
        return MT;
    }
    public int[][] getWed()
    {
        setup();
        return Wed;
    }
    public int[][] getThur()
    {
        setup();
        return Thur;
    }
    public int[][] getFri()
    {
        setup();
        return Fri;
    }

}
