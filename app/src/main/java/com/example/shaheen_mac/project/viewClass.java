package com.example.shaheen_mac.project;

/**
 * Created by ADMIN on 10/2/2016.
 */
public class viewClass {

    public viewClass(String a,String b,String c)
    {
        this.setA(a);
        this.setB(b);
        this.setC(c);

    }

    private String a,b,c;

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}

