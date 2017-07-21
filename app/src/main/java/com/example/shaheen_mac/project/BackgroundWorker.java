package com.example.shaheen_mac.project;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ADMIN on 9/28/2016.
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
     ProgressDialog pd;
    String result="";
    sqlite mydb;
    String user_name,oid;
    Context c1;
    BackgroundWorker(Context ctx)
    {
        context=ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type=params[0];



        String login_url="http://kapcl.esy.es/wc/signin.php";

        //change url
        String reg_url="http://kapcl.esy.es/wc/signup.php";

        String checkout_url="http://kapcl.esy.es/wc/buy.php";
        String myorder_url="http://kapcl.esy.es/wc/purchaselog.php";
        String cancel_url="http://kapcl.esy.es/wc/cancelorders.php";

        if(type.equals("login"))
        {

            try {


//             pd = ProgressDialog.show(context,
  //                  "", "Connecting..", true);


                user_name=params[1];
                    String password=params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data=URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    result+=line;

                }




                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        else if(type.equals("myorders"))
        {

            try {

                user_name=params[1];
                //String password=params[2];
                URL url = new URL(myorder_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data=URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    result+=line;

                }




                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        else if(type.equals("cancelorder"))
        {

            try {

                user_name=params[1];
                oid=params[2];
                //String password=params[2];
                URL url = new URL(cancel_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data=URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+URLEncoder.encode("order_id","UTF-8")+"="+URLEncoder.encode(oid,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    result+=line;

                }




                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }





        else if (type.equals("register"))
        {


            try {

                String name=params[1];
                String address=params[2];
                String pin=params[3];
                String mail=params[4];
                String phone=params[5];
                String pass=params[6];

                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data=URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                        +URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"
                        +URLEncoder.encode("pin","UTF-8")+"="+URLEncoder.encode(pin,"UTF-8")+"&"
                        +URLEncoder.encode("mail","UTF-8")+"="+URLEncoder.encode(mail,"UTF-8")+"&"
                        +URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"
                        +URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="",line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    result+=line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }





        }

        else if (type.equals("checkout"))
        {


            try {

                String jsonarr=params[1];
                String phone=params[2];
                //String pin=params[3];
              //  String mail=params[4];
                //String phone=params[5];
                //String pass=params[6];

                URL url = new URL(checkout_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data=URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"+URLEncoder.encode("items","UTF-8")+"="+URLEncoder.encode(jsonarr,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="",line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    result+=line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }





        }



        return null;


    }

    @Override
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("LOGIN STATUS");

//          pd = ProgressDialog.show(context,
  //           "", "Connecting..", true);


    }

    @Override
    protected void onPostExecute(String result) {

        String togetMyOrders,forcncel;

        togetMyOrders= result.substring(0, 7);
        forcncel= result.substring(0, 5);
//        pd.dismiss();
        String trmdTogetMO=togetMyOrders.trim();



        String trimdresult=result.trim();
       //Toast.makeText(context,trimdresult,Toast.LENGTH_SHORT).show();

        if (trimdresult.equals("Login success"))
        {

           // Toast.makeText(context,"sddsfdffdds",Toast.LENGTH_SHORT).show();

            mydb=new sqlite(context);

            boolean check=mydb.USERInsertdata(user_name);

            if (check==true) {
                final Intent intent = new Intent(context, dispctivity.class);
                context.startActivity(intent);
                ((MainActivity) context).finish();
               // pd.dismiss();
            }
        }

        if (trimdresult.equals("Signup success"))
        {

            Toast.makeText(context,"Signup success\nPlease go back and Login",Toast.LENGTH_SHORT).show();

        }
        if (trimdresult.equals("Account Not Verified"))
        {

            Toast.makeText(context,"Account Not Verified",Toast.LENGTH_SHORT).show();

        }




        if (trimdresult.equals("Incorrect username or password"))
        {



            final AlertDialog.Builder alertDialog= new AlertDialog.Builder(context);
            alertDialog.setTitle("Incorrect phone number or password");
            alertDialog.setMessage("Note that password is case sensitive");

           // final Intent intent = new Intent(context, MainActivity.class);
            //context.startActivity(intent);
            //((MainActivity) context).finish();

            alertDialog.setNegativeButton("OK",new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub

                    dialog.dismiss();
                   //pd.dismiss();

                }
            });

            alertDialog.show();





        }


        if (trimdresult.equals("cancelled"))
        {

            Toast.makeText(context,"Your order has been cancelled",Toast.LENGTH_SHORT).show();


            // if (check==true) {
                final Intent intent = new Intent(context, dispctivity.class);
                context.startActivity(intent);
                ((MyordersActivity) context).finish();
            //}
        }


        if (trmdTogetMO.equals("{\"logs\""))
        {

               // final Intent intent = new Intent(context, MyordersActivity.class);
            Intent i = new Intent(context, MyordersActivity.class);
            i.putExtra("res",result);

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }

        if (trmdTogetMO.equals("stock"))
        {


            Intent i = new Intent(context, dispctivity.class);
            //i.putExtra("res",result);

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

            Toast.makeText(context,"Order Cancelled",Toast.LENGTH_LONG).show();






        }




        //checkout success
       else if (trimdresult.equals("checkout success"))
        {

           // Toast.makeText(context,"sds",Toast.LENGTH_LONG).show();
            Intent i = new Intent(context, ConformActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
         //   pd.dismiss();


        }


     //  Toast.makeText(context,result,Toast.LENGTH_LONG).show();

    //   alertDialog.setMessage(result);

       // Log.i(result, "onPostExecute: ");
        // alertDialog.show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }



}
