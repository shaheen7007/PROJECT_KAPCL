package com.example.shaheen_mac.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import projtetst.shaheen.com.pro.R;

/**
 * Created by ADMIN on 10/2/2016.
 */
public class prodListAdapter extends ArrayAdapter {

    List list= new  ArrayList();
    public prodListAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(viewClass object) {
        super.add(object);
        list.add(object);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View row;
        row=convertView;
        contactholder ch;
        if (row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            ch=new contactholder();
            ch.tx_n=(TextView)row.findViewById(R.id.lst);
            ch.Tx_p=(TextView)row.findViewById(R.id.lst1);
            ch.tx_e=(TextView)row.findViewById(R.id.lst2);
            row.setTag(ch);

        }
        else
        {

            ch=(contactholder)row.getTag();
            viewClass v=(viewClass)this.getItem(position);
            ch.tx_n.setText(v.getA());
            ch.Tx_p.setText(v.getB());
            ch.tx_e.setText(v.getC());
            return row;





        }

        return super.getView(position, convertView, parent);
    }

    static class contactholder
    {

        TextView tx_n,Tx_p,tx_e;


    }

}
