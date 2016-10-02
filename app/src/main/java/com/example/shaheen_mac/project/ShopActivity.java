package com.example.shaheen_mac.project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import projtetst.shaheen.com.pro.R;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);








    }
    String json_string;

    class Backjason extends AsyncTask<Void,Void,String> {

        String json_url;

        @Override
        protected String doInBackground(Void... params) {
            //return null;


            URL url = null;
            try {
                url = new URL(json_url);
                String JSON_STRING;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStreamReader = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamReader));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");

                }
                bufferedReader.close();
                inputStreamReader.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

return null;
        }




        @Override
        protected void onPreExecute() {
            //super.onPreExecute();


            //change url
            json_url="http://kapcl.net16.net/wc/show.php";

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String res) {
           // super.onPostExecute(res);
            TextView textView=(TextView)findViewById(R.id.textView);
            textView.setText(res);
            json_string=res;


        }
    }

    public void getjason(View view)
    {

        new Backjason().execute();

    }

    public void parse(View view)
    {
        if (json_string !=null)
        {

            Intent intent=new Intent(ShopActivity.this,dispctivity.class);
            intent.putExtra("k",json_string);
            startActivity(intent);



        }


    }


}

