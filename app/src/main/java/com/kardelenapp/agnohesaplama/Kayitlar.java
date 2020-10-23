package com.kardelenapp.agnohesaplama;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

import io.paperdb.Paper;

/**
 * Created by mustafa on 1/23/2017.
 */

public class Kayitlar extends AppCompatActivity {

    static ListView kayitlarList;
    static CustomAdapterKayit kayitlarAdapter;
    static ArrayList<CustomListItemKayit> kayitlarArray = new ArrayList<CustomListItemKayit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kayitlar);


        Paper.init(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarKayitlar);
        setSupportActionBar(toolbar);
        yeni_activity_ac(toolbar);


        if (null != Paper.book().read("kayitlarArray")) {
            kayitlarArray = Paper.book().read("kayitlarArray");
        } else {
            //kayitlarArray.add(new CustomListItemKayit("kayit yok",0));
        }


        kayitlarAdapter = new CustomAdapterKayit(this, R.layout.kayitlar_list_layout, kayitlarArray);
        kayitlarList = (ListView) findViewById(R.id.listViewKayit);
        kayitlarList.setItemsCanFocus(true);
        kayitlarList.setAdapter(kayitlarAdapter);

        /**
         * get on item click listener
         */
        kayitlarList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {
                Log.i("List View Clicked", "**********");
                Toast.makeText(getApplication(), "List View Clicked:" + position, Toast.LENGTH_LONG).show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.entry1, menu);
        return true;
    }

    public static void removeListItem(CustomListItemKayit item) {
        kayitlarArray.remove(item);
        kayitlarList.setAdapter(kayitlarAdapter);
    }

    private void yeni_activity_ac(Toolbar toolbar) {


        new DrawerBuilder().withActivity(this).build();
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Anasayfa");
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(1).withName("Kayıtlar");

        final SecondaryDrawerItem item4 = new SecondaryDrawerItem().withIdentifier(2).withName("Hakkında");
        final SecondaryDrawerItem item5 = new SecondaryDrawerItem().withIdentifier(2).withName("Çıkış");

//create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)

                .addDrawerItems(

                        item1.withSelectable(false),
                        item2.withSelectable(false),

                        new DividerDrawerItem().withSelectable(false),
                        item4.withSelectable(false),
                        item5.withSelectable(false)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (position == 0) {
                            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                            Kayitlar.this.startActivity(myIntent);
                            finish();
                        } else if (position == 1) {

                            //Intent myIntent = new Intent(getApplicationContext(), Kayitlar.class);
                            //Kayitlar.this.startActivity(myIntent);
                        } else if (position == 3) {
                            Intent myIntent = new Intent(getApplicationContext(), Hakkinda.class);
                            Kayitlar.this.startActivity(myIntent);
                            finish();
                        } else if (position == 4) {
                            System.exit(0);
                        } else if (position == 5) {

                        } else {

                        }


                        return true;
                    }
                })
                .build();

        result.setSelection(0);
    }


    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        Kayitlar.this.startActivity(myIntent);
        finish();

    }

}