package com.jjws.model;

/**
 * Created by sk on 16-7-8.
 */
public class Point {

    private int px;
    private int py;

//    public Point() {
//
//    }

    public Point(int px, int py) {
        this.px = px;
        this.py = py;
    }



    public void setPx(int px) {
        this.px = px;
    }

    public void setPy(int py) {
        this.py = py;
    }

    public int getPx() {

        return px;
    }

    public int getPy() {
        return py;
    }
}
