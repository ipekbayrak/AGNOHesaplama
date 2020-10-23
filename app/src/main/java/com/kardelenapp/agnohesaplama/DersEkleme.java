package com.kardelenapp.agnohesaplama;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import io.paperdb.Paper;

/**
 * Created by mustafa on 1/21/2017.
 */


public class DersEkleme extends AppCompatActivity {
    static ListView userList;
    static CustomAdapter userAdapter;
    static ArrayList<CustomListItem> userArray = new ArrayList<CustomListItem>();
    static ArrayList<CustomListItemKayit> kayitlarArray = new ArrayList<CustomListItemKayit>();
    ArrayList<CustomListItem> userArrayArrival;
    Button button4;

    int listIndex = 0;

    //1 2 3 :)
    int mode =1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ders_ekleme);

        Paper.init(getApplicationContext());

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        button4 = (Button) findViewById(R.id.button4);

        Intent intent = getIntent();


        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey("key")) {
                if( extras.getBoolean("key"))
                {
                    listIndex = Paper.book().read("list");
                    userArray = Paper.book().read("userArray");
                    String title = Paper.book().read("textName","Düzenle");
                    getSupportActionBar().setTitle(title+ " Düzenleme");
                    mode = Paper.book().read("mode",1);

                }
            }
        }
        else
        {

            listIndex = 0;
            mode = Paper.book().read("mode",1);

        }

        userAdapter = new CustomAdapter(this, R.layout.custom_list_layout, userArray,mode);
        userList = (ListView) findViewById(R.id.listView);
        userList.setItemsCanFocus(true);
        userList.setAdapter(userAdapter);
        /////////////////////////////////////////////








        /**
         * get on item click listener
         */
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {
                Log.i("List View Clicked", "**********");
                Toast.makeText(getApplication(),"List View Clicked:" + position, Toast.LENGTH_LONG).show();
            }
        });

        //buttonlar
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userArray.size()<1)
                {
                    Toast.makeText(getApplication(),"Lütfen en az bir kayıt girin.", Toast.LENGTH_LONG).show();
                    return  ;
                }

                float kreditoplam = 0f;
                float skortoplam = 0f;
                float sonuc = 0f;

                for (CustomListItem item :  userAdapter.data)
                {
                    kreditoplam +=  listToKredi( item.getKredi());
                    skortoplam +=  listToPuan(item.getPuan()) * listToKredi(item.getKredi());
                }

                sonuc = skortoplam/kreditoplam;

                Paper.book().write("sonuc", sonuc);
                Paper.book().write("kreditoplam", kreditoplam);

                Intent myIntent = new Intent(getApplicationContext(), SonucEkran.class);
                DersEkleme.this.startActivity(myIntent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /*
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        if (actionBar != null) {
            // enabling action bar app icon and behaving it as toggle button
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setIcon(R.drawable.save);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        */





        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.entry2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            //case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                //return true;

            case R.id.action_save:

                userList.clearFocus();


                // User chose the "Favorite" action, mark the current item
                // as a favorite...

                //i 0 ise yeni kayıt açılmıştır başka bişey ise var olan kayıt editleniyordur.
                //yeni kayıt

                if(userArray.size()<1)
                {
                    Toast.makeText(getApplication(),"Lütfen en az bir kayıt girin.", Toast.LENGTH_LONG).show();
                    return true;
                }


                if(listIndex==0)
                {

                    int son=0;

                    if (Paper.book().read("kayitlarArray")==null)
                    {
                        son =0;
                    }
                    else
                    {
                        kayitlarArray = Paper.book().read("kayitlarArray");
                        if(kayitlarArray.size()==0)
                        {
                            son =0;
                        }
                        else
                        {
                            son = kayitlarArray.get((kayitlarArray.size()-1)).getIndex();
                        }

                    }


                    CustomListItemKayit yeniKayi = new CustomListItemKayit("Kayıt "+ String.valueOf(son+1),son+1,mode);

                    kayitlarArray.add(yeniKayi);

                    Paper.book().write("list"+String.valueOf(son+1), userArray);
                    Paper.book().write("kayitlarArray", kayitlarArray);

                    listIndex = son+1;


                    getSupportActionBar().setTitle(yeniKayi.getName()+ " Düzenleme");

                }
                else//var olan kaydı değiştirme
                {



                    Paper.book().write("list"+String.valueOf(listIndex), userArray);
                    //Paper.book().write("kayitlarArray", kayitlarArray);



                }


                Toast.makeText(getApplication(),"Kayıt İşlemi Yapıldı", Toast.LENGTH_LONG).show();

                return true;

            case R.id.action_addrow:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...

                //userArray.add();
                //userList.deferNotifyDataSetChanged();

                userAdapter.add(new CustomListItem("",0,0));

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public static void removeListItem(CustomListItem item)
    {
        userArray.remove(item );
        userList.setAdapter(userAdapter);
    }

    @Override
    public void onBackPressed() {

        listIndex = 0;

         userArray = new ArrayList<CustomListItem>();
         kayitlarArray = new ArrayList<CustomListItemKayit>();

        this.finish();

    }

    String[] harf = new String[]{"AA", "BA", "BB", "CB", "CC", "DC", "DD", "FD", "FF", "G"};
    String[] kredi = new String[]{"0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10", "10.5", "11", "11.5", "12", "12.5", "13", "13.5", "14", "14.5", "15"};

    public float listToKredi(int listkredi)
    {


        return Float.parseFloat(kredi[listkredi]);
    }

    public float listToPuan(int listpuan){

        if (mode  == 1)
        {
            switch (listpuan) {
                case 0 :
                    return 4;

                case 1 :
                    return 3.5f;

                case 2 :
                    return 3f;

                case 3 :
                    return 2.5f;

                case 4 :
                    return 2f;

                case 5 :
                    return 1.5f;

                case 6 :
                    return 1f;

                case 7 :
                    return 0.5f;

                case 8 :
                    return 0f;

                case 9 :
                    return 0;
            }
        }
        else  if (mode  == 2)
        {
            switch (listpuan) {
                case 0 :
                    return 4;

                case 1 :
                    return 3.75f;

                case 2 :
                    return 3.5f;

                case 3 :
                    return 3.25f;

                case 4 :
                    return 3f;

                case 5 :
                    return 2.75f;

                case 6 :
                    return 2.5f;

                case 7 :
                    return 2.25f;

                case 8 :
                    return 2.f;

                case 9 :
                    return 0;
                case 10 :
                    return 0;
                case 11 :
                    return 0;
            }
        }
        else  if (mode  == 3)
        {
            switch (listpuan) {
                case 0 :
                    return 4;

                case 1 :
                    return 4f;

                case 2 :
                    return 3.7f;

                case 3 :
                    return 3.3f;

                case 4 :
                    return 3f;

                case 5 :
                    return 2.7f;

                case 6 :
                    return 2.3f;

                case 7 :
                    return 2f;

                case 8 :
                    return 1.7f;

                case 9 :
                    return 1.3f;
                case 10 :
                    return 1f;
                case 11 :
                    return 0f;
                case 12 :
                    return 0f;
                case 13 :
                    return 0f;
            }
        }
        else
        {
            switch (listpuan) {
                case 0 :
                    return 4;

                case 1 :
                    return 3.5f;

                case 2 :
                    return 3f;

                case 3 :
                    return 2.5f;

                case 4 :
                    return 2f;

                case 5 :
                    return 1.5f;

                case 6 :
                    return 1f;

                case 7 :
                    return 0.5f;

                case 8 :
                    return 0f;

                case 9 :
                    return 0;
            }
        }



        return 0;
    }
}

