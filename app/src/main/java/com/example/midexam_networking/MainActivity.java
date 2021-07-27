package com.example.midexam_networking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    ProgressBar pb;

Recycler_Adapter adapter;
ArrayList<Attributes> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv=findViewById(R.id.rec_film);


        pb=findViewById(R.id.progressBar2);

        pb.setVisibility(View.VISIBLE);


        data=new ArrayList<>();




        MyAsyncTask myAsyncTask= new MyAsyncTask();
        myAsyncTask.execute(Json.Films_Url );

    }






        private class MyAsyncTask extends AsyncTask<String,Void,ArrayList<Attributes>> {

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected ArrayList<Attributes> doInBackground(String... urls) {


                if (urls==null || urls[0]==null) return null;

                URL url=Json.curl(urls[0]);

                String json=Json.makeHttpConnection(url);

                ArrayList<Attributes> attribut=Json.getjson(json);
                return  attribut;
            }



            @Override
            protected void onPostExecute(ArrayList<Attributes> data) {


                adapter=new Recycler_Adapter(MainActivity.this,data);

                androidx.recyclerview.widget.RecyclerView.LayoutManager lm=new GridLayoutManager(MainActivity.this,2);
                rv.setHasFixedSize(true);
                DividerItemDecoration dvi=new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.HORIZONTAL);
                rv.addItemDecoration(dvi);
                rv.setLayoutManager(lm);
                rv.setAdapter(adapter);

                pb.setVisibility(View.GONE);




            }
        }



    }
