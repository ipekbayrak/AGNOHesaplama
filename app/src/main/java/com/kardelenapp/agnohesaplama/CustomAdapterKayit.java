package com.kardelenapp.agnohesaplama;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import io.paperdb.Paper;

public class CustomAdapterKayit extends ArrayAdapter<CustomListItemKayit> {
    Context context;
    int layoutResourceId;
    ArrayList<CustomListItemKayit> data = new ArrayList<CustomListItemKayit>();



    public CustomAdapterKayit(Context context, int layoutResourceId,ArrayList<CustomListItemKayit> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UserHolder holder = null;
        Paper.init(context);


        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new UserHolder();
            holder.textName = (TextView) row.findViewById(R.id.textviewList1);

            holder.btnKaydaGit = (Button) row.findViewById(R.id.buttonListKayit1);
            holder.btnDelete = (Button) row.findViewById(R.id.buttonListDelete);

            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }
        final CustomListItemKayit user = data.get(position);
        holder.textName.setText(user.getName());

        holder.index = user.getIndex();
        holder.mode = user.getMode();

        final UserHolder finalHolder = holder;
        holder.btnKaydaGit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Log.i("Delete Button Clicked", "**********");
                //Toast.makeText(context, "Delete button Clicked", Toast.LENGTH_LONG).show();

                //ders ekleme ekranına git

                ArrayList<CustomListItem> userArray =  Paper.book().read( "list"+String.valueOf(finalHolder.index)  );

                Intent myIntent = new Intent(context, DersEkleme.class);
                Paper.book().write("textName",finalHolder.textName.getText().toString());
                Paper.book().write("list",finalHolder.index);
                Paper.book().write("mode",finalHolder.mode);
                Paper.book().write("userArray",userArray);
                //myIntent.putExtra("list", finalHolder.textName.getText().toString()); //Optional parameters

                //veri gönderliyor mu?
                myIntent.putExtra("key", true); //Optional parameters
                context.startActivity(myIntent);
            }
        });

        holder.btnDelete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:


                                ArrayList<CustomListItemKayit> kayitlarArray = Paper.book().read("kayitlarArray");
                                //kayitlarArray.remove(user);

                                for (CustomListItemKayit kayitlar: kayitlarArray) {
                                    if(kayitlar.getIndex() == user.getIndex() )
                                    {
                                        kayitlarArray.remove(kayitlar);
                                        break;
                                    }
                                }

                                Paper.book().write("kayitlarArray",kayitlarArray);


                                 Kayitlar.removeListItem(user);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(finalHolder.textName.getText().toString()+ " silinsin mi?").setPositiveButton("Evet", dialogClickListener)
                        .setNegativeButton("Hayır", dialogClickListener).show();

            }
        });




        return row;

    }

    static class UserHolder {
        TextView textName;
        int index;

        int mode;
        Button btnKaydaGit;
        Button btnDelete;
    }
}