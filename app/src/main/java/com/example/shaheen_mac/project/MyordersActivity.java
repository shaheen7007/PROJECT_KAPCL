package com.example.shaheen_mac.project;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import projtetst.shaheen.com.pro.R;

import static android.R.id.list;

public class MyordersActivity extends AppCompatActivity {
    sqlite mydb;
    String unam;
    String j;
ListView myorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);
        j = getIntent().getExtras().getString("res");
      //  Toast.makeText(this,j,Toast.LENGTH_LONG).show();

        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        myorder=(ListView)findViewById(R.id.moderlistid);
        mydb=new sqlite(this);

        new Backjason().execute();





    }

    class Backjason extends AsyncTask<Void,Void,String> {

        String json;
        String oid;





        @Override
        protected String doInBackground(Void... params) {
            json=j;
           // oid=params[0];

            return json;


            //return null;
        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();


            //change url

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String res) {
            JSONObject jsonResponse;

            try {

                /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
                jsonResponse = new JSONObject(res);

                /***** Returns the value mapped by name if it exists and is a JSONArray. Returns null otherwise.*******/
                JSONArray jsonMainNode = jsonResponse.optJSONArray("logs");

                /*********** Process each JSON Node ************/
                int lengthJsonArr = jsonMainNode.length();
                //urls = "";

                List<String> Orderidlist = new ArrayList<String>();
                List<String> Orderidlist1 = new ArrayList<String>();
                List<String> itemNamelist = new ArrayList<String>();
                List<String> itemNamelist1 = new ArrayList<String>();
                //List<String> itemDesclist = new ArrayList<String>();
                List<String> itemPricelist = new ArrayList<String>();
                List<String> itemQtylist = new ArrayList<String>();
                List<String> itemQtylist1 = new ArrayList<String>();
                List<String> datelist = new ArrayList<String>();
                List<String> itemStatus = new ArrayList<String>();
              //  myarray = new String[10];

                for (int i = 0; i < lengthJsonArr; i++) {
                    /****** Get Object for each JSON node.***********/
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

                    /******* Fetch node values **********/
                    //  int song_id = Integer.parseInt(jsonChildNode.optString("song_id").toString());
                    String date = jsonChildNode.optString("Date").toString();
                    String item_name = jsonChildNode.optString("item_name").toString();
                   // String item_name1 = jsonChildNode.optString("item_name").toString();
                    String order_id = jsonChildNode.optString("orderid").toString();
                    String status = jsonChildNode.optString("status").toString();
                    String price = jsonChildNode.optString("price").toString();
                    String quantity = jsonChildNode.optString("qty").toString();
                    //String total_stock = jsonChildNode.optString("total_stock").toString();
                    //String stock_status = jsonChildNode.optString("stock_status").toString();

                    Orderidlist1.add(order_id);
                    // itemNamelist.add(item_name);
                    //itemPricelist.add(price);
                    //datelist.add(date);
                    itemQtylist1.add(quantity);
                    itemNamelist1.add(item_name);
                    //itemStatus.add(status);                      // itemIdlist.add(item_id);


                    if(i!=0) {
                    String g = Orderidlist.get(Orderidlist.size()-1);

                      if(g.equals(order_id))
                      {
                          int l=0;
                      }
                      else
                      {
                          Orderidlist.add(order_id);
                          itemNamelist.add(item_name);
                          itemPricelist.add(price);
                          datelist.add(date);
                          itemQtylist.add(quantity);
                          itemStatus.add(status);



                      }
                  }

                    else
                  {
                      Orderidlist.add(order_id);
                      itemNamelist.add(item_name);
                      itemPricelist.add(price);
                      datelist.add(date);
                      itemQtylist.add(quantity);
                      itemStatus.add(status);
                  }


                }



                String[] arrODR = Orderidlist.toArray(new String[Orderidlist.size()]);
                String[] arrODR1 = Orderidlist1.toArray(new String[Orderidlist1.size()]);
                String[] arrName = itemNamelist.toArray(new String[itemNamelist.size()]);
                String[] arrName1 = itemNamelist1.toArray(new String[itemNamelist1.size()]);
                String[] arrPrice = itemPricelist.toArray(new String[itemPricelist.size()]);
               String[] arrDATE= datelist.toArray(new String[datelist.size()]);
                String[] arrQTY = itemQtylist.toArray(new String[itemQtylist.size()]);
                String[] arrQTY1 = itemQtylist.toArray(new String[itemQtylist1.size()]);
                String[] arrSTS = itemStatus.toArray(new String[itemStatus.size()]);


                ArrayList<String> ODRList = new ArrayList<String>(Arrays.asList(arrODR));
                ArrayList<String> ODRList1 = new ArrayList<String>(Arrays.asList(arrODR1));
                ArrayList<String> nameList = new ArrayList<String>(Arrays.asList(arrName));
                ArrayList<String> nameList1 = new ArrayList<String>(Arrays.asList(arrName1));
                ArrayList<String> priceList = new ArrayList<String>(Arrays.asList(arrPrice));
                ArrayList<String> qtyList = new ArrayList<String>(Arrays.asList(arrQTY));
                ArrayList<String> qtyList1 = new ArrayList<String>(Arrays.asList(arrQTY1));
                ArrayList<String> STSList = new ArrayList<String>(Arrays.asList(arrSTS));
                ArrayList<String> DATEList = new ArrayList<String>(Arrays.asList(arrDATE));

              //  ArrayList<String> values = ... //Your values



                // HashSet<String> uniqueValues = new HashSet<>(ODRList);
                //HashSet<String> DateuniqueValues = new HashSet<>(DATEList);

//                List<String> list = new ArrayList<String>(uniqueValues);
  //              List<String> Dlist = new ArrayList<String>(DateuniqueValues);


                //similarly do it foer name,price etc

                myorder.setAdapter(new CustomListAdapter(getApplicationContext(), ODRList,ODRList1, nameList, priceList,qtyList,nameList1,STSList,DATEList));


                ///cop



            } catch (JSONException e) {

                e.printStackTrace();
            }


        }
    }

    public class CustomListAdapter extends BaseAdapter {
        private ArrayList<String> ID;
        private ArrayList<String> ID1;
        private ArrayList<String> NAME;
        private ArrayList<String> PRICE;
        private ArrayList<String> QTY;
        private ArrayList<String> QTY1;
        private ArrayList<String> STATUS;
       private ArrayList<String> DATE;

        private LayoutInflater layoutInflater;
        String n;

        public CustomListAdapter(Context context, ArrayList<String> ID,ArrayList<String> ID1, ArrayList<String> NAME, ArrayList<String> PRICE, ArrayList<String> QTY,ArrayList<String> QTY1, ArrayList<String> STATUS,ArrayList<String> DATE) {
            //this.OrderIdData = OrderIdData;
            this.NAME = NAME;
            this.DATE = DATE;
            this.PRICE = PRICE;
           this.QTY1 = QTY1;
            this.QTY = QTY;
           this.ID = ID;
           this.ID1 = ID1;
            this.STATUS = STATUS;
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
                convertView = layoutInflater.inflate(R.layout.orderrow, null);
                holder = new ViewHolder();
                holder.textDate = (TextView) convertView.findViewById(R.id.datid);
                holder.textStatus = (TextView) convertView.findViewById(R.id.statid);
                holder.textOdrid = (TextView) convertView.findViewById(R.id.oid);
                holder.CANCEL = (Button) convertView.findViewById(R.id.cancelbtnid);
                //holder.textPrice = (TextView) convertView.findViewById(R.id.qtyid);

              //  holder.cart = (ImageButton) convertView.findViewById(R.id.cartbtnidz);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            // holder.icon.setImageDrawable(getDrawable(R.drawable.load));
            //holder.cart.setImageDrawable(getDrawable(R.drawable.cart));
            //holder.text.setText();




            if (holder.textOdrid != null) {
                // new BitmapWorkerTask(holder.icon).execute(listData.get(position));

               // String html = "<html><body><img src=\"" + listData.get(position) + "\" width=\"100%\" height=\"100%\"\"/></body></html>";
                //holder.icon.loadData(html, "text/html", null);

                //(holder.text).setText(NAME.get(position));

                (holder.textOdrid).setText("KAPCL-"+ID.get(position));
                (holder.textDate).setText(DATE.get(position));
                (holder.textStatus).setText(STATUS.get(position));
                String c=STATUS.get(position).trim();
                if(c.equals("delivered"))
                {
                    holder.CANCEL.setVisibility(View.INVISIBLE);
                   // (holder.textStatus).setText("pending");

                }
               else if(c.equals("cancelled"))
                {
                    holder.CANCEL.setVisibility(View.INVISIBLE);
                    holder.textStatus.setTextColor(Color.parseColor("#cf3131"));


                }

                //else if(c.equals("not_delivered"))
                //{
                else {
                    (holder.textStatus).setText("pending");

                    holder.textStatus.setTextColor(Color.parseColor("#1d23d1"));
                }
                    // }


            }


            holder.CANCEL.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                ///    SQLiteDatabase db = mydb.getWritableDatabase();


                   // db.delete(sqlite.TABLE_NAME, "_id=?", new String[] {ID.get(position)});



              //      Toast.makeText(getApplicationContext(),"Item Removed from Cart",Toast.LENGTH_SHORT).show();






                    final AlertDialog.Builder alertDialog= new AlertDialog.Builder(MyordersActivity.this);
                    alertDialog.setTitle("Cancel Order ?");


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



                            // Toast.makeText(getApplicationContext(),dataToSend,Toast.LENGTH_LONG).show();



                            String type="cancelorder";
                            BackgroundWorker backgroundWorker=new BackgroundWorker(MyordersActivity.this);
                            backgroundWorker.execute(type,unam,ID.get(position));











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

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int q, u;
                    List<String> Orde = new ArrayList<String>();

                    String oiii;
                    oiii = ID.get(position);
                    String Str;
                    Str = "";
                    int c = 0;
                    //    Toast.makeText(getApplicationContext(), String.valueOf(ID1.size()), Toast.LENGTH_SHORT).show();


                    Iterator<String> itr = ID1.iterator();
                     Iterator<String> itr1 = QTY1.iterator();
                    while (itr1.hasNext()) {

                        System.out.println(itr1.next());

                    }


                 //   Iterator<String> itr2 = Orde.iterator();
                    // Iterator<String> itr1 = QTY1.iterator();
                   // Str="";
                    //while (itr2.hasNext()) {

                      //  String vv;
                        //vv=itr2.next();
                        //Str=Str + vv + "  ";

                    //}

                    //







//Toast.makeText(getApplicationContext(),Str,Toast.LENGTH_SHORT).show();

                }
            });


            return convertView;








        }

        class ViewHolder {
            //WebView icon;
            //ImageButton cart;
            //TextView text;
            TextView textPrice,textqty,textOdrid,textStatus,textName,textDate;
            Button CANCEL;
        }
    }







}
