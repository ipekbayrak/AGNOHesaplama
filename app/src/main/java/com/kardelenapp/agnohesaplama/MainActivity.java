package com.kardelenapp.agnohesaplama;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        yeni_activity_ac(toolbar);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        Paper.init(getApplicationContext());

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getApplicationContext(), DersEkleme.class);
                //myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);

                Paper.book().write("mode",1);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getApplicationContext(), DersEkleme.class);
                //myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);

                Paper.book().write("mode",2);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getApplicationContext(), DersEkleme.class);
                //myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);

                Paper.book().write("mode",3);

            }
        });


    }


    private void yeni_activity_ac(Toolbar toolbar ){


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
                        if (position==0){
                            //Intent myIntent = new Intent(getApplicationContext(), Kayitlar.class);
                            //MainActivity.this.startActivity(myIntent);
                        }
                        else if (position==1){

                            Intent myIntent = new Intent(getApplicationContext(), Kayitlar.class);
                            MainActivity.this.startActivity(myIntent);
                            finish();
                        }
                        else if (position==3){
                            Intent myIntent = new Intent(getApplicationContext(), Hakkinda.class);
                            MainActivity.this.startActivity(myIntent);
                            finish();
                        }
                        else if (position==4){
                            System.exit(0);
                        }
                        else if (position==5){

                        }
                        else
                        {

                        }



                        return true ;
                    }
                })
                .build();

        result.setSelection(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.entry1, menu);
        return true;
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Uygulamayı sonlandırmak için tekrar basın.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}