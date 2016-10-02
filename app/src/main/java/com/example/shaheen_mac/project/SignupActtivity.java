package com.example.shaheen_mac.project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import projtetst.shaheen.com.pro.R;

public class SignupActtivity extends AppCompatActivity {

    EditText name,address,pincode,mail,phone,pass;
    Button submitBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_acttivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name=(EditText)findViewById(R.id.nameET);
        address=(EditText)findViewById(R.id.AddressET);
        pincode=(EditText)findViewById(R.id.PinET);
        mail=(EditText)findViewById(R.id.MailET);
        phone=(EditText)findViewById(R.id.phoneET);
        pass=(EditText)findViewById(R.id.passET);
        submitBT=(Button)findViewById(R.id.Subitbutton);



    }


    public void onReg(View view)
    {

        String nameS,addressS,pinS,mailS,phoneS,passS;
        nameS=name.getText().toString();
        addressS=address.getText().toString();
        pinS=pincode.getText().toString();
        mailS=mail.getText().toString();
        phoneS=phone.getText().toString();
        passS=pass.getText().toString();
        String type="register";
        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute(type,nameS,addressS,pinS,mailS,phoneS,passS);


    }

}
