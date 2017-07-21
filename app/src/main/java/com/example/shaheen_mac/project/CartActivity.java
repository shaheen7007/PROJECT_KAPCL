package com.example.shaheen_mac.project;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import projtetst.shaheen.com.pro.R;

public class CartActivity extends Activity {
    sqlite mydb;
    ImageButton clear,checkout;
    String unam;
    TextView grandT;
ListView cartlst;
    double grandtotal;

    List<String> namez = new ArrayList<String>();
    List<String> qtyz = new ArrayList<String>();
    List<String> itempricelist = new ArrayList<String>();
    List<String> itemidlist = new ArrayList<String>();
    public static Activity fa;

    @Override
    public void onBackPressed()
    {

        startActivity(new Intent(this,dispctivity.class));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        mydb=new sqlite(this);
        grandtotal=0;

        fa = this;
        grandT=(TextView)findViewById(R.id.textView7);
        cartlst=(ListView)findViewById(R.id.cartlist);

        poppulatelist();
        clear=(ImageButton) findViewById(R.id.imageButton3);
        checkout=(ImageButton)findViewById(R.id.imageButton2);




        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                mydb.cleardata();
                Toast.makeText(getApplicationContext(),"Cart Cleared",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(CartActivity.this,CartActivity.class));

            }
        });



        checkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final AlertDialog.Builder alertDialog= new AlertDialog.Builder(CartActivity.this);
                alertDialog.setTitle("Place Order ?");


                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Add your code for the button here.


                        List<String> namelist = new ArrayList<String>();
                        List<String> qtylist = new ArrayList<String>();


                        SQLiteDatabase db = mydb.getWritableDatabase();

                        String[] cc = {sqlite.ItemNamecol,sqlite.QTYcol,sqlite.Multcol};
                        Cursor cursor = db.query(sqlite.TABLE_NAME, cc, null, null, null, null, null);

                        String[] uu = {sqlite.usernameNamecol};
                        Cursor cursor1 = db.query(sqlite.UseerTable_NAME, uu, null, null, null, null, null);



                        while (cursor1.moveToNext()) {
                            int dd = cursor1.getColumnIndex(sqlite.usernameNamecol);
                            //int ee=cursor.getColumnIndex(sqlite.QTYcol);
                            unam = cursor1.getString(dd);
                            //double qt = cursor.getDouble(ee);
                            //String qtt=String.valueOf(qt);
                        }


                        //namelist.add(nam);
                        //qtylist.add(qtt);
                        JSONArray resultSet 	= new JSONArray();
                        JSONObject returnObj 	= new JSONObject();

                        cursor.moveToFirst();
                        while (cursor.isAfterLast() == false) {

                            int totalColumn = cursor.getColumnCount();
                            JSONObject rowObject = new JSONObject();

                            for( int i=0 ;  i< totalColumn ; i++ )
                            {
                                if( cursor.getColumnName(i) != null )
                                {

                                    try
                                    {

                                        if( cursor.getString(i) != null )
                                        {
                                            Log.d("TAG_NAME", cursor.getString(i) );
                                            rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
                                        }
                                        else
                                        {
                                            rowObject.put( cursor.getColumnName(i) ,  "" );
                                        }
                                    }
                                    catch( Exception e )
                                    {
                                        Log.d("TAG_NAME", e.getMessage()  );
                                    }
                                }

                            }

                            resultSet.put(rowObject);
                            cursor.moveToNext();
                        }

                        cursor.close();
                        Log.d("TAG_NAME", resultSet.toString() );
                        // return resultSet;
                        String dataToSend = resultSet.toString();

                        // Toast.makeText(getApplicationContext(),dataToSend,Toast.LENGTH_LONG).show();



                        String type="checkout";
                        BackgroundWorker backgroundWorker=new BackgroundWorker(CartActivity.this);
                        backgroundWorker.execute(type,dataToSend,unam);










                        String[] arrName = namelist.toArray(new String[namelist.size()]);
                        String[] arrQty = qtylist.toArray(new String[qtylist.size()]);
                        ArrayList<String> NAMEList = new ArrayList<String>(Arrays.asList(arrName));
                        ArrayList<String> QUANTITYList = new ArrayList<String>(Arrays.asList(arrQty));




                        // Toast.makeText(getApplicationContext(), "well come", Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.setNegativeButton("No",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                        dialog.dismiss();

                         }
                });

                alertDialog.show();





            }
        });



    }



    public void poppulatelist()
    {
        int i=0;

        String[] cc = {sqlite.ItemNamecol,sqlite.Pricecol,sqlite.QTYcol,sqlite.idcol,sqlite.Multcol};
        SQLiteDatabase db = mydb.getWritableDatabase();
        Cursor cursor = db.query(sqlite.TABLE_NAME, cc, null, null, null, null, null);
        String [] fronfieldnames=new String[] {sqlite.ItemNamecol, sqlite.QTYcol};





        while (cursor.moveToNext()) {
            int dd = cursor.getColumnIndex(sqlite.Multcol);
            int ee = cursor.getColumnIndex(sqlite.QTYcol);
            int nn = cursor.getColumnIndex(sqlite.ItemNamecol);
            int pr = cursor.getColumnIndex(sqlite.Pricecol);
            int idd = cursor.getColumnIndex(sqlite.idcol);


            int mul = cursor.getInt(dd);
            int ids = cursor.getInt(idd);
            double pric = cursor.getDouble(pr);
            double qt = cursor.getDouble(ee);
            String nm = cursor.getString(nn);

            double totqty = mul * qt;
            double totPrice = pric * qt * mul;
            grandtotal=grandtotal+totPrice;


            namez.add(nm);
            qtyz.add(String.valueOf(totqty));
            itempricelist.add(String.valueOf(totPrice));
            itemidlist.add(String.valueOf(ids));
            i++;

        }

        grandT.setText("Rs. "+grandtotal);

       // String[] arrURL = mylist.toArray(new String[mylist.size()]);
        String[] arrName = namez.toArray(new String[namez.size()]);
        String[] arrPrice = itempricelist.toArray(new String[itempricelist.size()]);
        //String[] arrDESC = itemDesclist.toArray(new String[itemDesclist.size()]);
        String[] arrQTY = qtyz.toArray(new String[qtyz.size()]);
        String[] arrID = itemidlist.toArray(new String[itemidlist.size()]);
       // ArrayList<String> srcList = new ArrayList<String>(Arrays.asList(arrURL));
        ArrayList<String> nameList = new ArrayList<String>(Arrays.asList(arrName));
        ArrayList<String> priceList = new ArrayList<String>(Arrays.asList(arrPrice));
        ArrayList<String> qtyList = new ArrayList<String>(Arrays.asList(arrQTY));
        ArrayList<String> idList = new ArrayList<String>(Arrays.asList(arrID));
       // ArrayList<String> descList = new ArrayList<String>(Arrays.asList(arrDESC));

        //similarly do it foer name,price etc
      //  TextView empty = (TextView) findViewById(android.R.id.empty);
        //cartlst.setEmptyView(empty);
        //cartlst.setAdapter(adapter);
        cartlst.setAdapter(new CustomListAdapter(getApplicationContext(),nameList,priceList,qtyList,idList));


      //  if (nameList!=null) {
            if (nameList.size() >0) {
                // listView not empty
            } else {


                final android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(this);
               // alertDialog.setTitle("Exit ?");
                 alertDialog.setMessage("Your cart is empty !");

                // final Intent intent = new Intent(context, MainActivity.class);
                //context.startActivity(intent);
                //((MainActivity) context).finish();

                alertDialog.setPositiveButton("Add Now", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                        Intent intent=new Intent(CartActivity.this,dispctivity.class);

                        startActivity(intent);

                    }


                });


                alertDialog.show();



                // listView  empty
            }






    }


    public class CustomListAdapter extends BaseAdapter {
        private ArrayList<String> NAME;
        private ArrayList<String> PRICE;
        private ArrayList<String> QTY;
        private ArrayList<String> ID;
        private ArrayList<String> listData;



        private LayoutInflater layoutInflater;
        String n;

        public CustomListAdapter(Context context, ArrayList<String> NAME, ArrayList<String> PRICE, ArrayList<String> QTY, ArrayList<String> ID) {
            this.listData = listData;
            this.NAME = NAME;
            this.PRICE = PRICE;
            this.QTY = QTY;
            this.ID = ID;
            layoutInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return ID.size();
        }

        @Override
        public Object getItem(int position) {

            return ID.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            //   myImageView.setImageResource(R.drawable.adefaultimage);
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.cartrow, null);
                holder = new ViewHolder();
               holder.icon = (WebView) convertView.findViewById(R.id.icon);
                holder.cart = (ImageButton) convertView.findViewById(R.id.Rbtn);
               // holder.text = (TextView) convertView.findViewById(R.id.Name);
                holder.textPrice = (TextView) convertView.findViewById(R.id.Price);
                holder.name = (TextView) convertView.findViewById(R.id.jname);
                holder.textqty = (TextView) convertView.findViewById(R.id.QQ);

              //  holder.cart = (ImageButton) convertView.findViewById(R.id.cartbtnidz);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            // holder.icon.setImageDrawable(getDrawable(R.drawable.load));
          //holder.cart.setImageDrawable(getDrawable(R.drawable.rmvcart));
            //holder.text.setText();




           //if (holder.icon != null) {
                // new BitmapWorkerTask(holder.icon).execute(listData.get(position));

                //String html = "<html><body><img src=\"" + listData.get(position) + "\" width=\"100%\" height=\"100%\"\"/></body></html>";
                //holder.icon.loadData(html, "text/html", null);

               // (holder.).setText("ff");
                double realprice=Double.valueOf(PRICE.get(position))/Double.valueOf(QTY.get(position));
                (holder.textPrice).setText("Rs. " + (realprice*Double.valueOf(QTY.get(position))));
                (holder.textqty).setText(QTY.get(position)+" kgs");
                (holder.name).setText(NAME.get(position));
           // }


         holder.cart.setOnClickListener(new View.OnClickListener() {
              public void onClick(View v) {


                  SQLiteDatabase db = mydb.getWritableDatabase();


                  db.delete(sqlite.TABLE_NAME, "_id=?", new String[] {ID.get(position)});



                  Toast.makeText(getApplicationContext(),"Item Removed from Cart",Toast.LENGTH_SHORT).show();

                  startActivity(new Intent(getApplicationContext(),CartActivity.class));




              }
            });




            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                 //   Toast.makeText(getApplicationContext(),NAME.get(position),Toast.LENGTH_LONG).show();

                }
            });


            return convertView;








        }

        class ViewHolder {
            WebView icon;
            ImageButton cart;
            TextView text,name;
            TextView textPrice,textqty,textdesc;
        }
    }






}

