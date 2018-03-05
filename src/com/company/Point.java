package com.company;

public class Point {
    public char[] x=new char[3];
    public char[] y=new char[3];
    Point()
    {
        for (int i = 0; i < 3; i++) {
            x[0]=' ';
            y[0]=' ';
        }
    }
   public void  setPoint(char[] x,char[] y)
    {
        this.x=x;
        this.y=y;
    }

    public void setX(char[]x) {
        this.x = x;
    }

    public void setY(char[] y) {

        this.y = y;

    }
}
