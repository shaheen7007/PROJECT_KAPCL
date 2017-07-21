package com.example.shaheen_mac.project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import projtetst.shaheen.com.pro.R;

public class DiscriptionActivity extends AppCompatActivity {

    TextView Name,Price,Desc,Qty;
    String url,des,qt,i,u;
    ImageView ico;
    WebView webView;
    ImageButton add,viewc;
    int count;
    sqlite mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discription);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
  //      setSupportActionBar(toolbar);
        final String name,price;
        Name=(TextView)findViewById(R.id.itemname);
        Qty=(TextView)findViewById(R.id.qty);
        Desc=(TextView)findViewById(R.id.descr);
        Price=(TextView)findViewById(R.id.price);
        add=(ImageButton)findViewById(R.id.addButton) ;
        viewc=(ImageButton)findViewById(R.id.imageButton5) ;
        //ico=(ImageView)findViewById(R.id.icoid);
        webView=(WebView)findViewById(R.id.web);
        mydb=new sqlite(this);


        name= getIntent().getExtras().getString("name");
        price= getIntent().getExtras().getString("price");
        url= getIntent().getExtras().getString("imgurl");
        des= getIntent().getExtras().getString("desc");
        qt= getIntent().getExtras().getString("qty");
        i= getIntent().getExtras().getString("i");
        u= getIntent().getExtras().getString("u");
        Name.setText(name);
        Price.setText("RS. "+(Double.valueOf(price)*Double.valueOf(qt)));
        Desc.setText(des);
        Qty.setText(qt+ " "+u);
        //new BitmapWorkerTask(ico).execute(url);
        webView.setBackgroundColor(0);

        String html = "<html><body><img src=\"" + url + "\" width=\"100%\" height=\"100%\"\"/></body></html>";
        webView.loadData(html, "text/html", null);

       // webView.loadDataWithBaseURL("", "<img src='"+url+"'/>",
                //"text/html", "UTF-8", "");


        //Toast.makeText(this,name+"\n"+price+"\n"+url,Toast.LENGTH_LONG).show();



        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                //Toast.makeText(getApplicationContext(),NAME.get(position),Toast.LENGTH_LONG).show();




               // boolean inserted;
                //inserted = mydb.Insertdata(name,price,qt,i);
                boolean inserted;
                SQLiteDatabase db = mydb.getWritableDatabase();


                boolean b=mydb.verification(i);


                if(b==true) {

                    String[] uu = {sqlite.Multcol};
                    Cursor cursor1 = db.query(sqlite.TABLE_NAME, uu, sqlite.idcol + "=" +i, null, null, null, null);



                    while (cursor1.moveToNext()) {
                        int dd = cursor1.getColumnIndex(sqlite.Multcol);
                        //int ee=cursor.getColumnIndex(sqlite.QTYcol);
                        count = cursor1.getInt(dd);
                        //double qt = cursor.getDouble(ee);
                        //String qtt=String.valueOf(qt);
                    }
                    count++;
                    ContentValues contentValues=new ContentValues();
                    contentValues.put(sqlite.Multcol,count);

                    String strSQL = "UPDATE cart_table SET mult ="+count +" WHERE _id = "+i;

                    db.execSQL(strSQL);
                    Toast.makeText(DiscriptionActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();



                }
                else {
                    inserted = mydb.Insertdata(name,price,qt,i);
                    if (inserted == true) {

                        Toast.makeText(DiscriptionActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(DiscriptionActivity.this, "Failed", Toast.LENGTH_SHORT).show();


                    }
                }


            }
        });



        viewc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {




                Intent intent=new Intent(DiscriptionActivity.this,CartActivity.class);

                startActivity(intent);




            }
        });



    }


    class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private String imageUrl;

        public BitmapWorkerTask(ImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage
            // collected
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(String... params) {
            imageUrl = url;
            return LoadImage(imageUrl);
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }

        private Bitmap LoadImage(String URL) {
            Bitmap bitmap = null;
            InputStream in = null;
            try {
                in = OpenHttpConnection(URL);
                bitmap = BitmapFactory.decodeStream(in);
                in.close();
            } catch (IOException e1) {
            }
            return bitmap;
        }

        private InputStream OpenHttpConnection(String strURL)
                throws IOException {
            InputStream inputStream = null;
            URL url = new URL(strURL);
            URLConnection conn = url.openConnection();

            try {
                HttpURLConnection httpConn = (HttpURLConnection) conn;
                httpConn.setRequestMethod("GET");
                httpConn.connect();

                if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    inputStream = httpConn.getInputStream();
                }
            } catch (Exception ex) {
            }
            return inputStream;
        }
    }





}
