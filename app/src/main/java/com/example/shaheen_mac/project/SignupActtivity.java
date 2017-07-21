package com.example.shaheen_mac.project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import projtetst.shaheen.com.pro.R;

public class SignupActtivity extends AppCompatActivity {

    EditText name,address,pincode,mail,phone,pass;
    ImageButton submitBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsignup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name=(EditText)findViewById(R.id.namei);
        address=(EditText)findViewById(R.id.addressi);
        pincode=(EditText)findViewById(R.id.pini);
        mail=(EditText)findViewById(R.id.emaili);
        phone=(EditText)findViewById(R.id.phoni);
        pass=(EditText)findViewById(R.id.passwordi);
        submitBT=(ImageButton)findViewById(R.id.singupi);

        submitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String nameS,addressS,pinS,mailS,phoneS,passS;
                nameS=name.getText().toString();
                addressS=address.getText().toString();
                pinS=pincode.getText().toString();
                mailS=mail.getText().toString();
                phoneS=phone.getText().toString();
                passS=pass.getText().toString();
                String type="register";

                if (phoneS.length()!=10)
                {
                    phone.setError("Enter valid phone number");
                }
                else if (passS.equals(""))
                {
                    pass.setError("Enter valid password");
                }
                else if (mailS.equals(""))
                {
                    mail.setError("Enter valid e-mail id");
                }
                else if (pinS.equals(""))
                {
                    pincode.setError("Enter valid pincode");
                }
                else if (addressS.equals(""))
                {
                    address.setError("Enter valid address");
                }
                else if (nameS.equals(""))
                {
                    name.setError("Enter valid Name");
                }



                else {

                    BackgroundWorker backgroundWorker = new BackgroundWorker(getApplicationContext());
                    backgroundWorker.execute(type, nameS, addressS, pinS, mailS, phoneS, passS);
                }



            }
        });




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
