package com.example.shaheen_mac.project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import projtetst.shaheen.com.pro.R;

public class dispctivity extends AppCompatActivity {

    String j;
    JSONObject jsonObject;
    JSONArray jsonArray;
    prodListAdapter pr;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispctivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView=(ListView)findViewById(R.id.listView);

        //
        pr=new prodListAdapter(this,R.layout.row_layout);
        listView.setAdapter(pr);

        j=getIntent().getExtras().getString("k");
        Toast.makeText(this,j,Toast.LENGTH_LONG).show();

        try {
            jsonObject=new JSONObject(j);


            //change arrayname
            jsonArray=jsonObject.getJSONArray("items");

            int count=0;
            String n,p,e;


            while (count<jsonArray.length())
            {
                JSONObject jo=jsonArray.getJSONObject(count);
//chage key and array name
                n=jo.getString("item_name");
                p=jo.getString("price");
                e=jo.getString("total_stock");
                Toast.makeText(this,n,Toast.LENGTH_LONG).show();


                viewClass v=new viewClass(n,p,e);
                pr.add(v);


            }
            count++;


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this,"ssd",Toast.LENGTH_LONG).show();


        }


    }

}
