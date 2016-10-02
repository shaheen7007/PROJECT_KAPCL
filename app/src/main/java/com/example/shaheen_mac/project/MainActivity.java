package com.example.shaheen_mac.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import projtetst.shaheen.com.pro.R;

public class MainActivity extends AppCompatActivity {
    EditText us,ps;
    Button loginBT,SignBT;
    BackgroundWorker ob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        us=(EditText)findViewById(R.id.etUSR);
        ps=(EditText)findViewById(R.id.etPS);
        loginBT=(Button)findViewById(R.id.buttonLogin);
        SignBT=(Button)findViewById(R.id.buttonSignup);




    }
    public void onlogin(View view)
    {

        String ustring,pstring;
        ustring=us.getText().toString();
        pstring=ps.getText().toString();
        String type="login";
        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute(type,ustring,pstring);
       // String check=ob.checkfn();
        //


    }
    public void openReg(View view)
    {


        startActivity(new Intent(this,SignupActtivity.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
