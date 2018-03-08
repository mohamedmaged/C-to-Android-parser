package com.company;

public class MyLabel {
    String text;
    String name;
    Point location;
    Point size;
    Boolean date;
    Boolean time;
    Boolean seconds;
    MyLabel(String text,String name,Point size,Point x)
    {
        this.text=text;
        this.name=name;
        this.size=size;
        this.location=x;
        date=false;
        time=false;
        seconds=false;
    }

}
